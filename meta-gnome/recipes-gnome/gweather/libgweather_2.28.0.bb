LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SECTION = "x11/gnome/libs"
DEPENDS = "libgnome libsoup-2.4 gtk+ gnome-keyring"

inherit gnome

SRC_URI[archive.md5sum] = "9f4457144d32d4004e0e045240ab51dc"
SRC_URI[archive.sha256sum] = "65fd8e4d7b617de7aac5a5d7d9bebde63ead828bcdc3e8f73f038ce03e669654"

EXTRA_OEMAKE = "ORBIT_IDL=${STAGING_BINDIR_NATIVE}/orbit-idl-2"

do_configure_prepend() {
        sed -i -e 's:help::'    ${S}/Makefile.am
	sed -i -e 's: doc : :g' ${S}/Makefile.am
}

FILES_${PN} += "${datadir}/gnome* \
                ${datadir}/icons"

PACKAGES =+ "${PN}-locationdata"
FILES_${PN}-locationdata = "${datadir}/libgweather/Locations*"


