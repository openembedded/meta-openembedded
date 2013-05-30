require recipes-graphics/xorg-app/xorg-app-common.inc
DESCRIPTION = "xterm is the standard terminal emulator for the X Window System."
DEPENDS = "libxaw xproto xextproto libxext libxau libxpm ncurses"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=33;md5=3aaef904421576f75ce2ee32af88c375"

SRC_URI = "ftp://invisible-island.net/xterm/${PN}-${PV}.tgz"
SRC_URI[md5sum] = "f9dc37486d5f1e550b6dc2e26a8a0439"
SRC_URI[sha256sum] = "64e9c41137eac4277d0cfe04312b12dc5d11224f49fc999ff7f6b9597bf55b7b"

EXTRA_OECONF = " --x-includes=${STAGING_INCDIR} \
                 --x-libraries=${STAGING_LIBDIR} \
                 FREETYPE_CONFIG=${STAGING_BINDIR_CROSS}/freetype-config \
                 --disable-imake \
                 --disable-setuid"

do_configure() {
    gnu-configize --force
    sed -e "s%/usr/contrib/X11R6%${STAGING_LIBDIR}%g" -i configure
    oe_runconf
}

FILES_${PN} += " /usr/lib/X11"
