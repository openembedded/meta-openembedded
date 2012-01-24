DESCRIPTION = "Libav is a complete, cross-platform solution to record, convert and stream audio and video."
HOMEPAGE = "http://libav.org/"
SECTION = "libs"
LICENSE = "GPLv2+"

# Provides ffmpeg compat, see http://libav.org/about.html
PROVIDES = "ffmpeg"

ARM_INSTRUCTION_SET = "arm"

# faac faad2 lame schroedinger libgsm
DEPENDS = "virtual/libsdl zlib libogg libvorbis libvpx libtheora ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'liba52', d)}"

INC_PR = "r4"

inherit autotools pkgconfig

#        --enable-libgsm \
#        --enable-libmp3lame \
#        --enable-libschroedinger \
#        --enable-libvpx \

B = "${S}/build.${HOST_SYS}.${TARGET_SYS}"

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

EXTRA_FFCONF_armv7a = "--cpu=cortex-a8"
EXTRA_FFCONF ?= ""

EXTRA_OECONF = " \
        --enable-shared \
        --enable-pthreads \
        --enable-gpl \
        --enable-postproc \
        --enable-avfilter \
        \
        --cross-prefix=${TARGET_PREFIX} \
        --prefix=${prefix} \
        \
        --enable-ffserver \
        --enable-ffplay \
        --enable-x11grab \
        --enable-libtheora  \
        --enable-libvorbis \
        --arch=${TARGET_ARCH} \
        --target-os="linux" \
        --enable-cross-compile \
        --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}" \
        --extra-ldflags="${TARGET_LDFLAGS}" \
        --sysroot="${STAGING_DIR_TARGET}" \
        --enable-hardcoded-tables \
        ${EXTRA_FFCONF} \
"

#| yasm not found, use --disable-yasm for a crippled build
EXTRA_OECONF_append_x86-64 = " --disable-yasm"
EXTRA_OECONF_append_x86 = " --disable-yasm"

do_configure() {
	# We don't have TARGET_PREFIX-pkgconfig
	sed -i '/pkg_config_default="${cross_prefix}${pkg_config_default}"/d' ${S}/configure
	mkdir -p ${B}
	cd ${B}
	${S}/configure ${EXTRA_OECONF}
	sed -i -e s:Os:O4:g ${B}/config.h
}

do_install_append() {
	install -m 0644 ${S}/libavfilter/*.h ${D}${includedir}/libavfilter/
}

FFMPEG_LIBS = "libavcodec libavdevice libavformat \
               libavutil libpostproc libswscale libavfilter"

PACKAGES += "${PN}-vhook-dbg ${PN}-vhook"

RSUGGESTS_${PN} = "mplayer"
FILES_${PN} = "${bindir}"
FILES_${PN}-dev = "${includedir}/${PN}"

FILES_${PN}-vhook = "${libdir}/vhook"
FILES_${PN}-vhook-dbg += "${libdir}/vhook/.debug"


PACKAGES += "ffmpeg-x264-presets \
             libavcodec  libavcodec-dev  libavcodec-dbg \
             libavdevice libavdevice-dev libavdevice-dbg \
             libavformat libavformat-dev libavformat-dbg \
             libavutil   libavutil-dev   libavutil-dbg \
             libpostproc libpostproc-dev libpostproc-dbg \
             libswscale  libswscale-dev  libswscale-dbg \
             libavfilter libavfilter-dev libavfilter-dbg \
             libavcore   libavcore-dev   libavcore-dbg \
            "

FILES_ffmpeg-x264-presets = "${datadir}/*.ffpreset"

LEAD_SONAME = "libavcodec.so"

FILES_${PN}-dev = "${includedir}"
FILES_libavcodec = "${libdir}/libavcodec*.so.*"
FILES_libavcodec-dev = "${libdir}/libavcodec*.so ${libdir}/pkgconfig/libavcodec.pc ${libdir}/libavcodec*.a"
FILES_libavcodec-dbg += "${libdir}/.debug/libavcodec*"

FILES_libavdevice = "${libdir}/libavdevice*.so.*"
FILES_libavdevice-dev = "${libdir}/libavdevice*.so ${libdir}/pkgconfig/libavdevice.pc ${libdir}/libavdevice*.a"
FILES_libavdevice-dbg += "${libdir}/.debug/libavdevice*"

FILES_libavformat = "${libdir}/libavformat*.so.*"
FILES_libavformat-dev = "${libdir}/libavformat*.so ${libdir}/pkgconfig/libavformat.pc ${libdir}/libavformat*.a"
FILES_libavformat-dbg += "${libdir}/.debug/libavformat*"

FILES_libavutil = "${libdir}/libavutil*.so.*"
FILES_libavutil-dev = "${libdir}/libavutil*.so ${libdir}/pkgconfig/libavutil.pc ${libdir}/libavutil*.a"
FILES_libavutil-dbg += "${libdir}/.debug/libavutil*"

FILES_libpostproc = "${libdir}/libpostproc*.so.*"
FILES_libpostproc-dev = "${libdir}/libpostproc*.so  ${libdir}/pkgconfig/libpostproc.pc ${libdir}/libpostproc*.a ${includedir}/postproc"
FILES_libpostproc-dbg += "${libdir}/.debug/libpostproc*"

FILES_libswscale = "${libdir}/libswscale*.so.*"
FILES_libswscale-dev = "${libdir}/libswscale*.so ${libdir}/pkgconfig/libswscale.pc ${libdir}/libswscale*.a"
FILES_libswscale-dbg += "${libdir}/.debug/libswscale*"

FILES_libavfilter = "${libdir}/libavfilter*.so.*"
FILES_libavfilter-dev = "${libdir}/libavfilter*.so ${libdir}/pkgconfig/libavfilter.pc ${libdir}/libavfilter*.a"
FILES_libavfilter-dbg += "${libdir}/.debug/libavfilter*"

FILES_libavcore = "${libdir}/libavcore*.so.*"
FILES_libavcore-dev = "${libdir}/libavcore*.so ${libdir}/pkgconfig/libavcore.pc ${libdir}/libavcore*.a"
FILES_libavcore-dbg += "${libdir}/.debug/libavcore*"
