require libiconv.inc
LICENSE = "GPLv3 LGPLv2"
BBCLASSEXTEND = "native nativesdk"

PROVIDES = "virtual/libiconv"
PR = "r1"

#gettext.class cant be inherit here so use this hack
DEPENDS = "${@['','gettext-native'][bb.data.getVar('USE_NLS', d, 1) == 'yes']}"

EXTRA_OECONF +=  "${@['--disable-nls','--enable-nls'][bb.data.getVar('USE_NLS', d, 1) == 'yes']}"

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

