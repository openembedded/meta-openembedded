SUMMARY = "Simple DirectMedia Layer truetype font library"
SECTION = "libs"
DEPENDS = "virtual/libsdl2 freetype"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=cdd16c6436d6b8fcbdd22a54d68c6dd3"

SRC_URI = " \
    http://www.libsdl.org/projects/SDL_ttf/release/SDL2_ttf-${PV}.tar.gz \
    file://use.pkg-config.for.freetype2.patch \
"

S = "${WORKDIR}/SDL2_ttf-${PV}"
#EXTRA_OECONF += "SDL_CONFIG=${STAGING_BINDIR_CROSS}/sdl-config "

inherit autotools

TARGET_CC_ARCH += "${LDFLAGS}"

do_configure_prepend() {
    touch ${S}/NEWS ${S}/README ${S}/AUTHORS ${S}/ChangeLog

    # Removing these files fixes a libtool version mismatch.
    MACROS="libtool.m4 lt~obsolete.m4 ltoptions.m4 ltsugar.m4 ltversion.m4"

    for i in ${MACROS}; do
        rm -f ${S}/acinclude/$i
    done
}

SRC_URI[md5sum] = "79787216b56cb4707f39d538f2225e00"
SRC_URI[sha256sum] = "8728605443ea1cca5cad501dc34dc0cb15135d1e575551da6d151d213d356f6e"
