require recipes-graphics/xorg-app/xorg-app-common.inc
DESCRIPTION = "xterm is the standard terminal emulator for the X Window System."
DEPENDS = "libxaw xproto xextproto libxext libxau libxpm ncurses"
PR = "r6"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=33;md5=4791b08be94de2e6315ddd158659997d"

SRC_URI = "ftp://invisible-island.net/xterm/${PN}-${PV}.tgz"
SRC_URI[md5sum] = "bb53ddd4933736a50e6391cef3472c12"
SRC_URI[sha256sum] = "41945aabb96e827c3ca1fdbb3c2fab3a65f4a84d4da0ceb06b030e6a028cf739"

EXTRA_OECONF = " --x-includes=${STAGING_INCDIR} \
                 --x-libraries=${STAGING_LIBDIR} \
                 FREETYPE_CONFIG=${STAGING_BINDIR_CROSS}/freetype-config \
                 --disable-imake \
                 --disable-setuid"

do_configure() {
        sed -e "s%/usr/contrib/X11R6%${STAGING_LIBDIR}%g" -i configure
        oe_runconf
}

FILES_${PN} += " /usr/lib/X11"
