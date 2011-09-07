PR = "${INC_PR}.0"

export INST = "${D}"

do_install() {

        oe_runmake install

        # the crosscompiler is packaged by klcc-cross
        # remove klcc
        # remove also from FILES_libklibc-dev
        rm ${D}${base_bindir}/klcc

        # remove Linux headers .install and ..install.cmd files
        find ${D}${base_libdir}/klibc/include -name '.install' -delete
        find ${D}${base_libdir}/klibc/include -name '..install.cmd' -delete

        install -d ${D}${base_libdir}
        install -m 755 usr/klibc/klibc-*.so ${D}${base_libdir}
        (cd  ${D}${base_libdir}; ln -s klibc-*.so klibc.so)

}

PACKAGES = "libklibc libklibc-dev"
FILES_libklibc = "${base_libdir}/klibc-*.so"
FILES_libklibc-dev = "${base_libdir}/klibc.so \
                      ${base_libdir}/klibc/lib/* \
                      ${base_libdir}/klibc/include/* \
                      "
# see above
# do not package it in -dev
#                      ${base_bindir}/klcc \

require klibc.inc
require klibc-checksums_${PV}.inc
