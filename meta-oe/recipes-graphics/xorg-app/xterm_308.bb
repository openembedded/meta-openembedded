require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "xterm is the standard terminal emulator for the X Window System"
DEPENDS = "libxaw xproto xextproto libxext libxau libxpm ncurses"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=31;md5=540cf18ccc16bc3c5fea40d2ab5d8d51"

SRC_URI = "http://invisible-mirror.net/archives/${BPN}/${BP}.tgz"

SRC_URI[md5sum] = "1ec76c1a79a70de4f82c24c527ef4626"
SRC_URI[sha256sum] = "c5bf7377a5bf35e34b7e3a4ab6c848fbc89e8901dd98f8b237662a72bdbfe372"

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
