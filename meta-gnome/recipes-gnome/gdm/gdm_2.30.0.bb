DESCRIPTION = "Graphical login manager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PR = "r3"

DEPENDS = "xinput gnome-panel tcp-wrappers libcanberra libxklavier grep consolekit libpam gnome-doc-utils gtk+ libglade libgnomecanvas librsvg libxml2 libart-lgpl xrdb"

inherit gnome update-rc.d

SRC_URI += " \
            file://cross-xdetection.diff \
            file://%gconf-tree.xml \
            file://gdm \
            file://gdm.conf \
            file://gdm-pam \
            file://Default \
           "

SRC_URI[archive.md5sum] = "0da84637abbcbf1666529d6192a81e6b"
SRC_URI[archive.sha256sum] = "ac2c367766b8fa20ebeb41033931fc4d91482ad3d377823f1c7ad84eee12dc15"

EXTRA_OECONF = " --enable-authentication-scheme=shadow \
                 --enable-debug=yes \
                 --with-console-kit \
                 --disable-scrollkeeper "

do_install_prepend() {
	mkdir -p ${D}/var/lib/gdm/.gconf.mandatory
	cp ${WORKDIR}/%gconf-tree.xml ${D}/var/lib/gdm/.gconf.mandatory/
}

do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/gdm ${D}/${sysconfdir}/init.d/

	install -d ${D}/${sysconfdir}/gdm
	install -m 0644 ${WORKDIR}/gdm.conf ${D}/${sysconfdir}/gdm/

	install -d ${D}/${sysconfdir}/pam.d
	install -m 0755 ${WORKDIR}/gdm-pam       ${D}/${sysconfdir}/pam.d/gdm

	install -d ${D}/${sysconfdir}/gdm/Init
	install -m 0755 ${WORKDIR}/Default ${D}/${sysconfdir}/gdm/Init
}

FILES_${PN} += "${datadir}/icon* \
		${datadir}/xsession* \
               "

RDEPENDS_${PN} += "grep dbus-x11 shadow"
# "libpam-base-files"
CONFFILES_${PN} += "${sysconfdir}/gdm/gdm.conf ${sysconfdir}/init.d/gdm"
RRECOMMENDS_${PN} += "metacity gnome-session polkit-gnome consolekit"

INITSCRIPT_NAME = "gdm"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 20 0 1 6 ."

pkg_postinst_${PN} () {
    # can't do this offline
    if [ "x$D" != "x" ]; then
        exit 1
    fi
    grep "^gdm:" /etc/group > /dev/null || addgroup gdm
    grep "^gdm:" /etc/passwd > /dev/null || adduser --disabled-password --system --home /var/lib/gdm gdm --ingroup gdm -g gdm

if [ -d /var/lib/gdm ]; then
  chown -R gdm:gdm /var/lib/gdm
  chmod 0750 /var/lib/gdm
fi

# Register up as default dm
mkdir -p ${sysconfdir}/X11/
echo "${bindir}/gdm" > ${sysconfdir}/X11/default-display-manager

}

pkg_postrm_${PN} () {
    deluser gdm || true
    delgroup gdm || true
	sed -i /gdm/d ${sysconfdir}/X11/default-display-manager || true
}




