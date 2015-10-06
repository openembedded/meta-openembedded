# Copyright (C) 2010-2012 O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license

DESCRIPTION = "FreeRDP RDP client"
HOMEPAGE = "http://www.freerdp.com"
DEPENDS = "virtual/libx11 openssl libxcursor libxv cups alsa-lib pulseaudio pcsc-lite libxkbfile libxinerama"
SECTION = "x11/network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DWITH_PULSEAUDIO=ON \
                  -DWITH_PCSC=ON \
                  -DWITH_CUPS=ON \
                  -DWITH_FFMPEG=OFF \
                  -DWITH_CUNIT=OFF \
                  -DWITH_X11=ON \
                  -DWITH_XKBFILE=ON \
                  -DWITH_XINERAMA=ON"

PACKAGES =+ "libfreerdp"

LEAD_SONAME = "libfreerdp.so"
FILES_libfreerdp = "${libdir}/lib*${SOLIBS}"

PACKAGES_DYNAMIC += "^libfreerdp-plugin-.*"

python populate_packages_prepend () {
    freerdp_root = d.expand('${libdir}/freerdp')

    do_split_packages(d, freerdp_root, '^(audin_.*)\.so$',
        output_pattern='libfreerdp-plugin-%s',
        description='FreeRDP plugin %s',
        prepend=True, extra_depends='libfreerdp-plugin-audin')

    do_split_packages(d, freerdp_root, '^(rdpsnd_.*)\.so$',
        output_pattern='libfreerdp-plugin-%s',
        description='FreeRDP plugin %s',
        prepend=True, extra_depends='libfreerdp-plugin-rdpsnd')

    do_split_packages(d, freerdp_root, '^(tsmf_.*)\.so$',
        output_pattern='libfreerdp-plugin-%s',
        description='FreeRDP plugin %s',
        prepend=True, extra_depends='libfreerdp-plugin-tsmf')

    do_split_packages(d, freerdp_root, '^([^-]*)\.so$',
        output_pattern='libfreerdp-plugin-%s',
        description='FreeRDP plugin %s',
        prepend=True, extra_depends='')
}
