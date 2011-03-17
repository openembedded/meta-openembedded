HOMEPAGE = "http://www.packagekit.org/"
DEPENDS = "eggdbus libpam expat dbus-glib intltool-native"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=155db86cdbafa7532b41f390409283eb"

PR = "r2"

SRC_URI = "http://hal.freedesktop.org/releases/polkit-${PV}.tar.gz;name=polkit \
          "

SRC_URI[polkit.md5sum] = "e0a06da501b04ed3bab986a9df5b5aa2"
SRC_URI[polkit.sha256sum] = "3426ca917210b2a5525732559368c18f983a4c39a6a55c5dddba26071bd8054a"

EXTRA_OECONF = "--with-authfw=pam \
                --with-os-type=${DISTRO} \
                --disable-man-pages \
                --disable-gtk-doc \
                --enable-introspection=no \
"

S = "${WORKDIR}/polkit-${PV}"

inherit autotools pkgconfig

do_install_append () {
	install -d ${D}${localstatedir}/run/PolicyKit
	sed -i -e s:system:common:g ${D}${sysconfdir}/pam.d/*
}

FILES_${PN} += " ${datadir}/dbus-1 \
                 ${datadir}/PolicyKit ${datadir}/polkit-1\
                 ${localstatedir}/run/PolicyKit \
                 ${libdir}/polkit-1/extensions/*.so \
"

FILES_${PN}-dev += " ${libdir}/polkit-1/extensions/*a"

pkg_postinst_${PN} () {
    # can't do this offline
    if [ "x$D" != "x" ]; then
        exit 1
    fi
    grep "^polkituser:" /etc/group > /dev/null || addgroup polkituser
    grep "^polkituser:" /etc/passwd > /dev/null || adduser --disabled-password --system --home /var/run/polkit polkituser --ingroup polkituser -g polkituser 

	# Fix owners
	for i in /etc/polkit-1/localauthority /var/lib/polkit-1 ; do
		mkdir -p $i
		chown root $i
		chmod 700 $i
	done

	for i in /usr/libexec/polkit-agent-helper-1 /usr/bin/pkexec ; do
		chown root $i
		chmod 4755 $i
	done

	DBUSPID=`pidof dbus-daemon`
    if [ "x$DBUSPID" != "x" ]; then
        /etc/init.d/dbus-1 force-reload
    fi
}

pkg_postrm_${PN} () {
    deluser polkituser || true
    delgroup polkituser || true
}
