SUMMARY = "Freeware Advanced Audio Coder (MPEG2-AAC, MPEG4-AAC)"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later"
HOMEPAGE = "https://github.com/knik0/faac"
LIC_FILES_CHKSUM = "file://COPYING;md5=4bf661c1e3793e55c8d1051bc5e0ae21 \
                    file://libfaac/coder.h;beginline=5;endline=13;md5=75c08ee73fa8cdd35281fdcbe8874ff5"

LICENSE_FLAGS = "commercial"

SRC_URI = "git://github.com/knik0/faac.git;protocol=https;branch=master;tag=${BP}"
SRCREV = "6acbe23ac9318d8f731ef38e0a56365f70af4797"

inherit meson pkgconfig

EXTRA_OEMESON = "-Db_lto=false"

PACKAGES =+ "lib${BPN} lib${BPN}-dev"

FILES:${PN} = " ${bindir}/faac "
FILES:lib${BPN} = " ${libdir}/*.so.*"
FILES:lib${BPN}-dev = " \
    ${includedir} \
    ${libdir}/*.so \
    ${libdir}/pkgconfig \
"
