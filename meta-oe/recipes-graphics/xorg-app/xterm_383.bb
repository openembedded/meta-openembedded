require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "xterm is the standard terminal emulator for the X Window System"
DEPENDS = "libxaw xorgproto libxext libxau libxinerama libxpm ncurses desktop-file-utils-native"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=31;md5=9c96124b492c0c02356850c243aaeca2"

SRC_URI = "http://invisible-mirror.net/archives/${BPN}/${BP}.tgz \
           file://0001-Add-configure-time-check-for-setsid.patch \
          "

SRC_URI[sha256sum] = "a06613bcda508c2a1bff6d4230895da74a798799a2e39a23bac82089d7b9a998"

PACKAGECONFIG ?= ""
PACKAGECONFIG[xft] = "--enable-freetype,--disable-freetype,libxft fontconfig freetype-native"

# Let xterm install .desktop files
inherit mime-xdg

EXTRA_OECONF = " --x-includes=${STAGING_INCDIR} \
                 --x-libraries=${STAGING_LIBDIR} \
                 FREETYPE_CONFIG=${STAGING_BINDIR_CROSS}/freetype-config \
                 --disable-imake \
                 --disable-rpath-hack \
                 --disable-setuid \
                 --with-app-defaults=${datadir}/X11/app-defaults \
                 "

B = "${S}"

CFLAGS += "-D_GNU_SOURCE"

do_configure() {
    gnu-configize --force
    sed -e "s%/usr/contrib/X11R6%${STAGING_LIBDIR}%g" -i configure
    oe_runconf
}

do_install:append() {
    oe_runmake install-desktop DESTDIR="${D}" DESKTOP_FLAGS="--dir=${D}${DESKTOPDIR}"
}

RPROVIDES:${PN} = "virtual-x-terminal-emulator"

# busybox can supply resize too
inherit update-alternatives

ALTERNATIVE:${PN} = "resize x-terminal-emulator"
ALTERNATIVE_TARGET[x-terminal-emulator] = "${bindir}/xterm"
# rxvt-unicode defaults to priority 10. Let's be one point lower to let it override xterm.
ALTERNATIVE_PRIORITY[x-terminal-emulator] = "9"
