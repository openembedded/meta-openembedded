DESCRIPTION = "a unit test framework for C"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "devel"

PR = "r2"

SRC_URI = "${SOURCEFORGE_MIRROR}/check/check-${PV}.tar.gz \
           file://configure_fix.patch \
           file://check-m4-am-path-check-use-quadrigraphs-in-macro-names-to-unbreak-autoconf.patch \
          "
S = "${WORKDIR}/check-${PV}"

inherit autotools pkgconfig

EXTRA_OECONF += "--enable-plain-docdir"

SRC_URI[md5sum] = "30143c7974b547a12a7da47809a90951"
SRC_URI[sha256sum] = "961b3c66869018d02226bbbc394e79362cd898962ce810bce8417b3c497f7ad6"
