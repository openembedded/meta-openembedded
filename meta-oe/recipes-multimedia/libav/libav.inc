DESCRIPTION = "Libav is a complete, cross-platform solution to record, convert and stream audio and video."
HOMEPAGE = "http://libav.org/"
SECTION = "libs"
LICENSE = "GPLv2+"

# Provides ffmpeg compat, see http://libav.org/about.html
PROVIDES = "ffmpeg"

ARM_INSTRUCTION_SET = "arm"

# faac faad2 lame schroedinger libgsm
DEPENDS = "virtual/libsdl zlib libogg libvorbis libvpx libtheora ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'liba52', d)}"

INC_PR = "r6"

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
        --enable-avserver \
        --enable-avplay \
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

PACKAGES += "${PN}-vhook-dbg ${PN}-vhook ffmpeg-x264-presets"

RSUGGESTS_${PN} = "mplayer"
FILES_${PN} = "${bindir}"
FILES_${PN}-dev = "${includedir}/${PN}"

FILES_${PN}-vhook = "${libdir}/vhook"
FILES_${PN}-vhook-dbg += "${libdir}/vhook/.debug"

FILES_ffmpeg-x264-presets = "${datadir}/*.avpreset"

LEAD_SONAME = "libavcodec.so"

FILES_${PN}-dev = "${includedir}"

python populate_packages_prepend() {
    av_libdir = bb.data.expand('${libdir}', d)
    av_pkgconfig = bb.data.expand('${libdir}/pkgconfig', d)

    # Runtime package
    do_split_packages(d, av_libdir, '^lib(.*)\.so\..*',
                      output_pattern='lib%s',
                      description='libav %s library',
                      prepend=True,
                      allow_links=True)

    # Development packages (-dev, -staticdev)
    do_split_packages(d, av_libdir, '^lib(.*)\.so$',
                      output_pattern='lib%s-dev',
                      description='libav %s development package',
                      extra_depends='${PN}-dev',
                      prepend=True,
                      allow_links=True)
    do_split_packages(d, av_pkgconfig, '^lib(.*)\.pc$',
                      output_pattern='lib%s-dev',
                      description='libav %s development package',
                      extra_depends='${PN}-dev',
                      prepend=True)
    do_split_packages(d, av_libdir, '^lib(.*)\.a$',
                      output_pattern='lib%s-staticdev',
                      description='libav %s development package - static library',
                      extra_depends='${PN}-dev',
                      prepend=True,
                      allow_links=True)
}

PACKAGES_DYNAMIC = "lib*"
