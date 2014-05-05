SUMMARY = "C/C++ Configuration File Library"
DESCRIPTION = "Library for manipulating structured configuration files"
HOMEPAGE = "http://www.hyperrealm.com/libconfig/"
SECTION = "libs"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=fad9b3332be894bab9bc501572864b29"

SRC_URI = "http://www.hyperrealm.com/${BPN}/${BP}.tar.gz"

inherit autotools-brokensep pkgconfig

SRC_URI[md5sum] = "b6ee0ce2b3ef844bad7cac2803a90634"
SRC_URI[sha256sum] = "09c8979252e60193e2969e9b0e1cd597f7820087867989b2f0939ad164473041"
