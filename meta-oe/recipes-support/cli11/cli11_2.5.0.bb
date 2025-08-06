SUMMARY = "C++11 command line parser"
DESCRIPTION = "A command line parser for C++11 and beyond that provides a rich feature set with a simple and intuitive interface."
HOMEPAGE = "https://github.com/CLIUtils/CLI11"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8bdde6bda8508bef68a39f3e0d7e939"

DEPENDS = "catch2"

SRC_URI = "gitsm://github.com/CLIUtils/CLI11;branch=main;protocol=https"
SRCREV = "4160d259d961cd393fd8d67590a8c7d210207348"

inherit cmake

# cli11 is a header only C++ library, so the main package will be empty.
RDEPENDS:${PN}-dev = ""

BBCLASSEXTEND = "native nativesdk"
