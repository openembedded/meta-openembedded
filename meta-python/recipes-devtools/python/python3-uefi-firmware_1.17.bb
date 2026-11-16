SUMMARY = "Various data structures and parsing tools for UEFI firmware."
DESCRIPTION = "This package contains Python UEFI firmware parser tool. The \
UEFI firmware parser is a simple module and set of scripts for parsing, \
extracting, and recreating UEFI firmware volumes. This includes parsing \
modules for BIOS, OptionROM, Intel ME and other formats too."
HOMEPAGE = "https://github.com/theopolis/uefi-firmware-parser"
LICENSE = "MIT AND BSD-2-Clause AND BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7befe9c63bf87929cff5a8b0c5a62bfb"

SRC_URI = "git://github.com/theopolis/uefi-firmware-parser;protocol=https;branch=main;tag=v${PV}"

SRCREV = "4942c4865da22000f4bb484830e26019ec061498"

DEPENDS += "python3-setuptools-scm-native"

inherit python_setuptools_build_meta

# The tarball is built from a git tag, so let setuptools_scm use ${PV}
# instead of deriving a version from the checkout.
export SETUPTOOLS_SCM_PRETEND_VERSION = "${PV}"

RDEPENDS:${PN} += " \
    python3-ctypes \
    python3-logging \
"
