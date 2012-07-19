DESCRIPTION = "a unit test framework for C"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "devel"

SRC_URI = "${SOURCEFORGE_MIRROR}/check/check-${PV}.tar.gz \
           file://fix.automake-1.12.x.patch \
          "

inherit autotools pkgconfig

EXTRA_OECONF += "--enable-plain-docdir"

SRC_URI[md5sum] = "5d75e9a6027cde79d2c339ef261e7470"
SRC_URI[sha256sum] = "82acf956bb07584ffea2bf04d5989fe939c3e74ecf93133a21037fd0f7996a7f"
