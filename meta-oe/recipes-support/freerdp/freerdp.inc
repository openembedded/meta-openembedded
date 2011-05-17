# Copyright (C) 2010, 2011 O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license

DESCRIPTION = "FreeRDP RDP client"
HOMEPAGE = "http://www.freerdp.com"
DEPENDS = "virtual/libx11 openssl libxcursor libxv cups alsa-lib pulseaudio pcsc-lite"
SECTION = "x11/network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

INC_PR = "r1"

inherit pkgconfig autotools

EXTRA_OECONF += "--with-smartcard --with-xvideo"

do_install_append() {
	rm ${D}${libdir}/freerdp/*.a ${D}${libdir}/freerdp/*.la
}

PACKAGES =+ "libfreerdp"

LEAD_SONAME = "libfreerdp.so"
FILES_libfreerdp = "${libdir}/lib*${SOLIBS}"

PACKAGES_DYNAMIC = "libfreerdp-plugin-*"

python populate_packages_prepend () {
        freerdp_root = bb.data.expand('${libdir}/freerdp', d)

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
