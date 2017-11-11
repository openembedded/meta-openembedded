SUMMARY = "A partition editor to graphically manage disk partitions "
HOMEPAGE = "http://gparted.org/index.php"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools pkgconfig gtk-icon-cache

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.gz \
    file://org.yoctoproject.pkexec.run-gparted.policy \
    file://gparted_polkit \
"
SRC_URI[md5sum] = "09b12d3831e91b26ec8726b00eecd43d"
SRC_URI[sha256sum] = "d31193effe058bdaeb2a4cd4b514ec753191179a0bc3abefda68a2b7975ecf4a"

DEPENDS = "glib-2.0 glib-2.0-native gtkmm parted gnome-doc-utils intltool-native"

do_install_append() {
    # Add a script which checks if polkit is installed.
    # If yes: a policy is requested from polkit / otherwise start as usual
    install ${WORKDIR}/gparted_polkit ${D}${sbindir}
    sed -i 's:%sbindir%:${sbindir}:g' ${D}${sbindir}/gparted_polkit
    # relink menu entry to use our script
    sed -i 's:${sbindir}/gparted:${sbindir}/gparted_polkit:g' ${D}${datadir}/applications/gparted.desktop

    install -d ${D}${datadir}/polkit-1/actions
    install ${WORKDIR}/org.yoctoproject.pkexec.run-gparted.policy ${D}${datadir}/polkit-1/actions/org.yoctoproject.pkexec.run-gparted.policy
}

EXTRA_OECONF = "--disable-scrollkeeper --disable-doc"

FILES_${PN} += " \
    ${datadir}/appdata \
    ${datadir}/icons \
    ${datadir}/polkit-1 \
"

RDEPENDS_${PN} = "dosfstools mtools e2fsprogs"
