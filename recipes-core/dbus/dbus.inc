DESCRIPTION = "A message bus system for inter-process communication"
HOMEPAGE = "http://dbus.freedesktop.org"
SECTION = "base"
LICENSE = "GPLv2+ AFLv2.1"
DEPENDS = "expat glib-2.0 virtual/libintl libsm libice virtual/libx11"

INC_PR = "r19"

SRC_URI = "\
  http://dbus.freedesktop.org/releases/dbus/dbus-${PV}.tar.gz;name=dbus \
  file://cross.patch \
  file://tmpdir.patch \
  file://fix-install-daemon.patch \
  file://0001-Make-the-default-DBus-reply-timeout-configurable.patch \
  file://dbus-1.init \
"

S = "${WORKDIR}/dbus-${PV}"

inherit autotools pkgconfig update-rc.d gettext

EXTRA_OECONF_X = "--with-x"

EXTRA_OECONF = "\
  --disable-qt \
  --disable-qt3 \
  --disable-gtk \
  --disable-tests \
  --disable-checks \
  --disable-xml-docs \
  --disable-doxygen-docs \
  --disable-libaudit \
  --with-xml=expat \
  --with-dbus-default-reply-timeout=200000 \
  ${EXTRA_OECONF_X} \
"

# On mipsel, D-Bus fails to link with --gc-sections and segfaults before main()
# when built with -fPIE/-pie.
do_configure_prepend_mipsel() {
	sed -i -e 's/\-fPIE//' \
		-e 's/\-pie\ \-Wl\,\-z\,relro//' \
		-e 's/-Wl,--gc-sections//' \
		${S}/configure.in
}

do_install_append() {
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/dbus-1.init ${D}${sysconfdir}/init.d/dbus-1
        # the stock install seems to install the libtool wrapper script, so we have to copy this manually :M:
        if [ -e bus/.libs/dbus-daemon-launch-helper ]; then
                install -d  ${D}${libexecdir}/
                install -m 0755 bus/.libs/dbus-daemon-launch-helper ${D}${libexecdir}/
        fi
}

RDEPENDS_${PN}-x11 = "${PN}"
RRECOMMENDS_${PN}-lib = "${PN}"
RCONFLICTS_${PN} = "dbus-1"
RREPLACES_${PN} = "dbus-1"
RPROVIDES_${PN} = "dbus-1"

PACKAGES =+ "${PN}-lib ${PN}-x11"

FILES_${PN} = "\
${bindir}/dbus-daemon* \
${bindir}/dbus-uuidgen \
${bindir}/dbus-cleanup-sockets \
${bindir}/dbus-send \
${bindir}/dbus-monitor \
${libexecdir}/dbus* \
${sysconfdir} \
${datadir}/dbus-1/services \
${datadir}/dbus-1/system-services \
"
FILES_${PN}-lib = "${libdir}/lib*.so.*"
FILES_${PN}-x11 = "\
${bindir}/dbus-launch \
"
FILES_${PN}-dev += "${libdir}/dbus-1.0/include ${bindir}/dbus-glib-tool ${libdir}/"

pkg_postinst_${PN}() {
#!/bin/sh
# can't do adduser stuff offline
if [ "x$D" != "x" ]; then
  exit 1
fi
MESSAGEUSER=messagebus
MESSAGEHOME=/var/run/dbus
mkdir -p $MESSAGEHOME || true
grep -q ^$MESSAGEUSER: /etc/group || addgroup "$MESSAGEUSER"
chgrp "$MESSAGEUSER" "$MESSAGEHOME"
grep -q ^$MESSAGEUSER: /etc/passwd || adduser --system --home "$MESSAGEHOME" --no-create-home --disabled-password --ingroup "$MESSAGEUSER" "$MESSAGEUSER"
chown "$MESSAGEUSER"."$MESSAGEUSER" "$MESSAGEHOME"
grep -q netdev: /etc/group || addgroup netdev
chmod u+s /usr/libexec/dbus-daemon-launch-helper
}

CONFFILES_${PN} = "${sysconfdir}/dbus-1/system.conf ${sysconfdir}/dbus-1/session.conf"

INITSCRIPT_NAME = "dbus-1"
INITSCRIPT_PARAMS = "start 02 5 2 . stop 20 0 1 6 ."
