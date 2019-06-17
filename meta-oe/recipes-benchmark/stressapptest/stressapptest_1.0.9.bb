SUMMARY = "Stressful Application Test"
DESCRIPTION = "Stressful Application Test (or stressapptest, its unix name) \
 is a memory interface test. It tries to maximize randomized traffic to memory \
 from processor and I/O, with the intent of creating a realistic high load \
 situation in order to test the existing hardware devices in a computer. \
"
HOMEPAGE = "https://github.com/stressapptest/stressapptest"
SECTION = "benchmark"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=55ea9d559f985fb4834317d8ed6b9e58"

SRC_URI = "https://github.com/${PN}/${PN}/archive/v${PV}.tar.gz \
           file://libcplusplus-compat.patch \
           file://read_sysfs_for_cachesize.patch \
          "

SRC_URI[md5sum] = "d3a526c174c049dd7a1068dc74a62be2"
SRC_URI[sha256sum] = "2ba470587ad4f6ae92057d427c3a2a2756e5f10bd25cd91e62eaef55a40b30a1"

S = "${WORKDIR}/${PN}-${PV}"

inherit autotools
