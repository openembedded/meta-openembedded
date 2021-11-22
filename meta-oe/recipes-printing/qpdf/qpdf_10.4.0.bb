DESCRIPTION = "PDF transformation/inspection software"
HOMEPAGE = "http://qpdf.sourceforge.net"
LICENSE = "Artistic-2.0"
SECTION = "libs"
DEPENDS = "libpcre zlib libjpeg-turbo"

SRC_URI = "${SOURCEFORGE_MIRROR}/qpdf/qpdf-${PV}.tar.gz"

LIC_FILES_CHKSUM = "file://Artistic-2.0;md5=7806296b9fae874361e6fb10072b7ee3"
SRC_URI[sha256sum] = "9ac6e691cc3f35a9fe44632e3fba727e1b6ef21181c0a883287abf5cf97ae222"

inherit autotools-brokensep gettext

# disable random file detection for cross-compile
EXTRA_OECONF = "--without-random \
                --disable-static \
                --disable-check-autofiles \
                "

EXTRA_OEMAKE:class-target = "LIBTOOL=${HOST_SYS}-libtool"

LDFLAGS:append:mipsarch = " -latomic"
LDFLAGS:append:riscv32 = " -latomic"

S="${WORKDIR}/${BPN}-${PV}"

# avoid Makefile returning error on 'make clean' before configure was run
CLEANBROKEN = "1"

DEBIAN_NOAUTONAME:libqpdf = "1"

PACKAGES =+ "libqpdf"
FILES:libqpdf = "${libdir}/libqpdf.so.*"

RDEPENDS:${PN} = "libqpdf"
