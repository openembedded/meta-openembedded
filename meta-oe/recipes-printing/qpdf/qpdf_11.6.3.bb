DESCRIPTION = "PDF transformation/inspection software"
HOMEPAGE = "http://qpdf.sourceforge.net"
LICENSE = "Artistic-2.0"
SECTION = "libs"
DEPENDS = "libpcre zlib libjpeg-turbo openssl gnutls"

SRC_URI = "${SOURCEFORGE_MIRROR}/qpdf/qpdf-${PV}.tar.gz"
SRC_URI[sha256sum] = "c394b1b0cff4cd9d13b0f5e16bdf3cf54da424dc434f9d40264b7fe67acd90bc"

LIC_FILES_CHKSUM = "file://Artistic-2.0;md5=7806296b9fae874361e6fb10072b7ee3"

inherit cmake gettext

# disable random file detection for cross-compile
EXTRA_OECONF = "--without-random \
                --disable-static \
                --disable-check-autofiles \
                "

EXTRA_OECMAKE = '-DRANDOM_DEVICE="/dev/random"'

LDFLAGS:append:mipsarch = " -latomic"
LDFLAGS:append:riscv32 = " -latomic"

S="${WORKDIR}/${BPN}-${PV}"

do_install:append() {
    # Change the fully defined path on the target
    sed -i -e 's|${STAGING_LIBDIR}|${libdir}|g' ${D}${libdir}/cmake/${BPN}/libqpdfTargets.cmake
}

# avoid Makefile returning error on 'make clean' before configure was run
CLEANBROKEN = "1"

DEBIAN_NOAUTONAME:libqpdf = "1"

PACKAGES =+ "libqpdf"
FILES:libqpdf = "${libdir}/libqpdf.so.*"

RDEPENDS:${PN} = "libqpdf"
