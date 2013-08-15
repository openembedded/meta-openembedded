DESCRIPTION = "Simple DirectMedia Layer image library."
SECTION = "libs"

LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://COPYING;md5=613734b7586e1580ef944961c6d62227"

DEPENDS = "tiff zlib libpng jpeg virtual/libsdl"

SRC_URI = "http://www.libsdl.org/projects/SDL_image/release/SDL_image-${PV}.tar.gz"
SRC_URI[md5sum] = "a0f9098ebe5400f0bdc9b62e60797ecb"
SRC_URI[sha256sum] = "0b90722984561004de84847744d566809dbb9daf732a9e503b91a1b5a84e5699"

S = "${WORKDIR}/SDL_image-${PV}"

inherit autotools

export SDL_CONFIG = "${STAGING_BINDIR_CROSS}/sdl-config"

# Disable the run-time loading of the libs and bring back the soname dependencies.
EXTRA_OECONF += "--disable-jpg-shared --disable-png-shared -disable-tif-shared"

do_configure_prepend() {
    # Removing this file fixes a libtool version mismatch.
    rm -f acinclude/libtool.m4
    rm -f acinclude/sdl.m4
    rm -f acinclude/pkg.m4
    rm -f acinclude/lt~obsolete.m4
    rm -f acinclude/ltoptions.m4
    rm -f acinclude/ltsugar.m4
    rm -f acinclude/ltversion.m4
}
