SUMMARY = "A from-scratch implementation of pstack using DWARF debugging \
and unwind information. Works for C/C++, Go, Rust, and Python."

HOMEPAGE = "https://github.com/peadar/pstack"
SECTION = "devel/pstack"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea061f8731d5e6a5761dfad951ef5f5f"

DEPENDS = "tcl virtual/libx11 libxt zip-native"

SRC_URI = "git://github.com/peadar/pstack;branch=master;tag=v${PV};protocol=https"
SRCREV = "a310df637d74917a1d3570c540bf3aef899d7e63"

S = "${WORKDIR}/${BPN}${PV}"

PACKAGES =+ "${PN}-lib"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

# isn't getting picked up by shlibs code
RDEPENDS:${PN} += "tk-lib"
RDEPENDS:${PN}:class-native = ""

BBCLASSEXTEND = "native nativesdk"

inherit binconfig

SYSROOT_DIRS += "${bindir_crossscripts}"

# Fix some paths that might be used by Tcl extensions
BINCONFIG_GLOB = "*Config.sh"
