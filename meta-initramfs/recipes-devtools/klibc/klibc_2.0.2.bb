PR = "${INC_PR}.0"

export INST = "${D}"

do_install() {
    oe_runmake install

    # the crosscompiler is packaged by klcc-cross
    # remove klcc
    rm ${D}${base_bindir}/klcc
    # remove now empty dir
    rmdir ${D}${base_bindir}

    install -d ${D}${base_libdir}
    install -m 755 usr/klibc/klibc-*.so ${D}${base_libdir}
    (cd  ${D}${base_libdir}; ln -s klibc-*.so klibc.so)
}

PACKAGES = "libklibc libklibc-staticdev libklibc-dev"
FILES_libklibc = "${base_libdir}/klibc-*.so"
FILES_libklibc-staticdev = "${base_libdir}/klibc/lib/libc.a"
FILES_libklibc-dev = "${base_libdir}/klibc.so \
                      ${base_libdir}/klibc/lib/* \
                      ${base_libdir}/klibc/include/* \
"
require klibc.inc
