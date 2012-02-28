DESCRIPTION = "Simple DirectMedia Layer image library."
SECTION = "libs"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=27818cd7fd83877a8e3ef82b82798ef4"

PR = "r3"

DEPENDS = "tiff zlib libpng jpeg virtual/libsdl"

SRC_URI = "http://www.libsdl.org/projects/SDL_image/release/SDL_image-${PV}.tar.gz"
SRC_URI[md5sum] = "6c06584b31559e2b59f2b982d0d1f628"
SRC_URI[sha256sum] = "75e05d1e95f6277b44797157d9e25a908ba8d08a393216ffb019b0d74de11876"

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

