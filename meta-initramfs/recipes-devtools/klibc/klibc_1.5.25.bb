PR = "${INC_PR}.0"

export INST = "${D}"

do_compile_prepend() {

    # after kernel 3.1 the headers are moved in /generated
    if [ ! -e ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/asm/bitsperlong.h ]
    then
        ln -sf ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/generated/asm/bitsperlong.h ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/asm/bitsperlong.h
        ln -sf ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/generated/asm/errno.h ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/asm/errno.h
        ln -sf ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/generated/asm/ioctl.h ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/asm/ioctl.h
        ln -sf ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/generated/asm/poll.h ${STAGING_KERNEL_DIR}/arch/${TARGET_ARCH}/include/asm/poll.h
    fi

}

do_install() {

        oe_runmake install

        # the crosscompiler is packaged by klcc-cross
        # remove klcc
        rm ${D}${base_bindir}/klcc
        # remove now empty dir
        rmdir ${D}${base_bindir}

        # remove Linux headers .install and ..install.cmd files
        find ${D}${base_libdir}/klibc/include -name '.install' -delete
        find ${D}${base_libdir}/klibc/include -name '..install.cmd' -delete

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
