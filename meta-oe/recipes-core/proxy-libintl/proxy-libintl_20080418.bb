DESCRIPTION = "Proxy libintl"
HOMEPAGE = "http://ftp.gnome.org/pub/GNOME/binaries/win32/dependencies/"
SECTION = "libs"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://src/proxy-libintl/COPYING.LIB.txt;md5=bc400bc21422f9a92e76ec2c5167ca2e"

PR = "r7"
PROVIDES = "virtual/libintl"

SRC_URI = " \
    http://ftp.gnome.org/pub/GNOME/binaries/win32/dependencies/${PN}-${PV}.zip \
    file://stub-only.patch \
    file://create-as-shared-lib.patch \
    file://soname.patch \
"

S = "${WORKDIR}"
FILES_${PN}-dev = "${includedir}/libintl.h ${libdir}/libintl.a"
FILES_${PN} = "${libdir}/libintl.so"

CFLAGS_append = " -fPIC -Wall -I ../../include ${@['-DSTUB_ONLY', ''][bb.data.getVar('USE_NLS', d, 1) != 'no']}"
TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
    cd ${WORKDIR}/src/proxy-libintl
    oe_runmake
}

do_install() {
    install -d ${D}/${includedir}
    install -m 0644 ${WORKDIR}/include/libintl.h ${D}/${includedir}

    install -d ${D}/${libdir}
    oe_libinstall -a -so -C lib libintl ${D}/${libdir}
}


SRC_URI[md5sum] = "63047fcbe5ff853db0d04981dd9f1888"
SRC_URI[sha256sum] = "b8519dfd77939b23ca599a724b536f48340f2c795552aa2a600155230571141c"
