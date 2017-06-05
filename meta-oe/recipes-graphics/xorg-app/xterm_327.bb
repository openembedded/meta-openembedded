require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "xterm is the standard terminal emulator for the X Window System"
DEPENDS = "libxaw xproto xextproto libxext libxau libxinerama libxpm ncurses"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=31;md5=842e945c46c43e05c44d95003aa878e1"

SRC_URI = "http://invisible-mirror.net/archives/${BPN}/${BP}.tgz"

SRC_URI[md5sum] = "3c32e931adcad44e64e57892e75d9e02"
SRC_URI[sha256sum] = "66fb2f6c35b342148f549c276b12a3aa3fb408e27ab6360ddec513e14376150b"
PACKAGECONFIG ?= ""
PACKAGECONFIG[xft] = "--enable-freetype,--disable-freetype,libxft fontconfig freetype-native"

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

FILES_${PN} += "/usr/lib/X11"
