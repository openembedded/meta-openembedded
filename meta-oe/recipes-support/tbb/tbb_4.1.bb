DESCRIPTION = "Parallelism library for C++ - runtime files \
    TBB is a library that helps you leverage multi-core processor \
    performance without having to be a threading expert. It represents a \
    higher-level, task-based parallelism that abstracts platform details \
    and threading mechanism for performance and scalability."
HOMEPAGE = "http://threadingbuildingblocks.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=2c7f2caf277a3933e3acdf7f89d54cc1"
PRDATE = "20130116"
PR = "r${PRDATE}"

SRC_URI = "http://threadingbuildingblocks.org/sites/default/files/software_releases/source/tbb41_${PRDATE}oss_src.tgz \
           file://cross-compile.patch \
           file://tbb.pc"

S = "${WORKDIR}/tbb41_${PRDATE}oss/"

SRC_URI[md5sum] = "3809790e1001a1b32d59c9fee590ee85"
SRC_URI[sha256sum] = "4ae2c10899e3b6ef2f686013ec5901fc658444ca90178efaca6014b0665c34b6"

do_compile() {
    oe_runmake compiler=gcc arch=${HOST_ARCH} runtime=cc4
}

do_install() {
    install -d ${D}${includedir} ${D}${libdir}/pkgconfig
    rm ${S}/include/tbb/index.html -f
    cp -a ${S}/include/tbb ${D}${includedir}
    install -m 0755 ${B}/build/linux_*_release/lib*.so* ${D}${libdir}
    install -m 0644 ${WORKDIR}/tbb.pc ${D}${libdir}/pkgconfig
}
