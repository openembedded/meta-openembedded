require recipes-graphics/xorg-app/xorg-app-common.inc
DESCRIPTION = "xterm is the standard terminal emulator for the X Window System."
DEPENDS = "libxaw xproto xextproto libxext libxau libxpm ncurses"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=33;md5=71d7532e485b8d325906bd751617e2a4"

SRC_URI = "ftp://invisible-island.net/xterm/${PN}-${PV}.tgz"
SRC_URI[md5sum] = "c0de5c2f7b3a50244c5cdd53dcab1de5"
SRC_URI[sha256sum] = "6cd38606e4179a874220f33dfd41063f93cd1c717aa26c742beb7adea3c1471e"

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
