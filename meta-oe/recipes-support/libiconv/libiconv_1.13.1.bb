require libiconv.inc
# GPLv3 for iconv program and documentation, LGPLv2 for libiconv and libcharset _libraries_ and their header files
# would be nice to split to separate PACKAGES
LICENSE = "GPLv3 LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674"

BBCLASSEXTEND = "native nativesdk"

PROVIDES = "virtual/libiconv"
PR = "r3"

SRC_URI += "file://autoconf.patch"

do_configure_append () {
        # Fix stupid libtool... handling. 
        # rpath handling can't be disabled and the Makefile's can't be regenerated..
        # (GNU sed required)
        sed -i s/^hardcode_libdir_flag_spec/#hardcode_libdir_flag_spec/ ${S}/*-libtool
}

LEAD_SONAME = "libiconv.so"
SRC_URI[md5sum] = "7ab33ebd26687c744a37264a330bbe9a"
SRC_URI[sha256sum] = "55a36168306089009d054ccdd9d013041bfc3ab26be7033d107821f1c4949a49"

