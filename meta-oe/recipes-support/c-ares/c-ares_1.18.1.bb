# Copyright (c) 2012-2014 LG Electronics, Inc.
SUMMARY = "c-ares is a C library that resolves names asynchronously."
HOMEPAGE = "http://daniel.haxx.se/projects/c-ares/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=fb997454c8d62aa6a47f07a8cd48b006"

SRC_URI = "git://github.com/c-ares/c-ares.git;branch=main;protocol=https \
           file://CVE-2022-4904.patch \
           file://CVE-2023-31130.patch \
           file://CVE-2023-31147.patch \
           file://CVE-2023-32067.patch \
           file://CVE-2024-25629.patch \
          "
SRCREV = "2aa086f822aad5017a6f2061ef656f237a62d0ed"

UPSTREAM_CHECK_GITTAGREGEX = "cares-(?P<pver>\d+_(\d_?)+)"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "${bindir}"

BBCLASSEXTEND = "native nativesdk"

# this vulneribility applies only when cross-compiling using autotools
# yocto cross-compiles via cmake which is also listed as official workaround
CVE_CHECK_WHITELIST += "CVE-2023-31124"
