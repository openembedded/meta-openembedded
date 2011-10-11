DESCRIPTION = "Simple DirectMedia Layer mixer library."
SECTION = "libs"
DEPENDS = "virtual/libsdl flac libmikmod libvorbis  ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'libmad', d)}"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=27818cd7fd83877a8e3ef82b82798ef4"

SRC_URI = "http://www.libsdl.org/projects/SDL_mixer/release/SDL_mixer-${PV}.tar.gz \
           file://fix-flac-madness.diff \
           file://configure.patch \
          "
SRC_URI[md5sum] = "65ada3d997fe85109191a5fb083f248c"
SRC_URI[sha256sum] = "86145ac39cac6d2c6169c226f937648dca5e89dcd828751763dd174fa9af9cf9"

S = "${WORKDIR}/SDL_mixer-${PV}"

inherit autotools

EXTRA_AUTORECONF += "--include=acinclude"
EXTRA_OECONF = "--disable-music-mp3 --enable-music-ogg --enable-music-ogg-tremor ${@base_conditional('ENTERPRISE_DISTRO', '1', '', '--enable-music-mp3-mad-gpl', d)} LIBS=-L${STAGING_LIBDIR}"

do_configure () {
	# Remove old libtool macros.
	MACROS="libtool.m4 lt~obsolete.m4 ltoptions.m4 ltsugar.m4 ltversion.m4"
	for i in ${MACROS}; do
		rm -f acinclude/$i
	done
	cp build-scripts/* . || true
	rm -rf build-scripts/
	export SYSROOT=$PKG_CONFIG_SYSROOT_DIR

	autotools_do_configure

	rm config.log
	for i in $(find -name "Makefile") ; do
		sed -i -e 's:-L/usr/lib:-L${STAGING_LIBDIR}:g' $i
	done
}

