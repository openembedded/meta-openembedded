SUMMARY = "Lightweight and flexible command-line JSON processor"
DESCRIPTION = "jq is like sed for JSON data, you can use it to slice and \
               filter and map and transform structured data with the same \
               ease that sed, awk, grep and friends let you play with text."
HOMEPAGE = "https://stedolan.github.io/jq/"
BUGTRACKER = "https://github.com/stedolan/jq/issues"
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2814b59e00e7918c864fa3b6bbe049b4"

PV = "1.6+git${SRCPV}"
SRC_URI = "git://github.com/stedolan/jq;protocol=https;branch=master \
    file://0001-configure-Pass-_XOPEN_SOURCE-when-checking-for-strpt.patch \
    file://0002-builtin-Replace-_BSD_SOURCE-with-_DEFAULT_SOURCE.patch \
    "
SRCREV = "cff5336ec71b6fee396a95bb0e4bea365e0cd1e8"
S = "${WORKDIR}/git"

inherit autotools-brokensep

PACKAGECONFIG ?= "oniguruma"

PACKAGECONFIG[docs] = "--enable-docs,--disable-docs,ruby-native"
PACKAGECONFIG[maintainer-mode] = "--enable-maintainer-mode,--disable-maintainer-mode,flex-native bison-native"
PACKAGECONFIG[oniguruma] = "--with-oniguruma,--without-oniguruma,onig"

EXTRA_OECONF += " \
    --disable-valgrind \
"

BBCLASSEXTEND = "native"
