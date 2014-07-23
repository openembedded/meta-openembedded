require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "xterm is the standard terminal emulator for the X Window System"
DEPENDS = "libxaw xproto xextproto libxext libxau libxpm ncurses"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=31;md5=540cf18ccc16bc3c5fea40d2ab5d8d51"

SRC_URI = "ftp://invisible-island.net/xterm/${BP}.tgz"

SRC_URI[md5sum] = "48f6d49b2b6b6933d501d767cbed9254"
SRC_URI[sha256sum] = "8af29987bda9b77205ebf6233aaa5c347e5f2407310b62ac9ba92a658257f058"

EXTRA_OECONF = " --x-includes=${STAGING_INCDIR} \
                 --x-libraries=${STAGING_LIBDIR} \
                 FREETYPE_CONFIG=${STAGING_BINDIR_CROSS}/freetype-config \
                 --disable-imake \
                 --disable-rpath-hack \
                 --disable-setuid"

B = "${S}"

do_configure() {
    gnu-configize --force
    sed -e "s%/usr/contrib/X11R6%${STAGING_LIBDIR}%g" -i configure
    oe_runconf
}

FILES_${PN} += " /usr/lib/X11"

PACKAGECONFIG ?= ""
PACKAGECONFIG[xft] = "--enable-freetype,--disable-freetype,libxft fontconfig"
