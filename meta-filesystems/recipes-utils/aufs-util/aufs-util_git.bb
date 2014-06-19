SUMMARY = "Tools for managing AUFS mounts"
SECTION = "base"
HOMEPAGE = "http://aufs.sourceforge.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

DEPENDS = "aufs-util-native"
DEPENDS_class-native = ""

SRCREV = "f29056fe396d56fc2a06a96312feabaebbe14c59"
SRC_URI = "git://git.code.sf.net/p/aufs/aufs-util;protocol=git;branch=aufs3.9 \
           file://aufs-util-don-t-strip-executables.patch \
           file://aufs-util-add-tool-concept-to-Makefile-for-cross-com.patch \
           file://aufs_type.h \
"

PV = "3.9+git${SRCPV}"

S = "${WORKDIR}/git"

do_configure_append () {
    install -d ${S}/include/linux/
    cp ${WORKDIR}/aufs_type.h ${S}/include/linux/
}

do_configure_append_class-target () {
    for i in ver c2sh c2tmac; do
        cp ${STAGING_BINDIR_NATIVE}/aufs-util-${PV}/$i ./
    done
}

do_compile () {
    oe_runmake CPPFLAGS="-I${S}/include -I${S}/libau"
}

do_compile_class-native () {
    oe_runmake tools CPPFLAGS="-I${S}/include -I${S}/libau"
}

do_install () {
    oe_runmake 'DESTDIR=${D}' install_sbin install_ubin install_etc
}

do_install_class-native () {
    install -d ${D}${bindir}/aufs-util-${PV}
    for i in ver c2sh c2tmac; do
        install -m 755 $i ${D}${bindir}/aufs-util-${PV}/$i
    done
}

RRECOMMENDS_${PN} += "kernel-module-aufs"

BBCLASSEXTEND = "native"
