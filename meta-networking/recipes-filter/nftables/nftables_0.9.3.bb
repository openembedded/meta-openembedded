SUMMARY = "Netfilter Tables userspace utillites"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d1a78fdd879a263a5e0b42d1fc565e79"

DEPENDS = "libmnl libnftnl readline gmp bison-native"

UPSTREAM_CHECK_URI = "https://www.netfilter.org/projects/nftables/files/"

SRC_URI = "http://www.netfilter.org/projects/nftables/files/${BP}.tar.bz2 \
           file://0001-update-python3-nftables-reference.patch"
SRC_URI[md5sum] = "9913b2b46864394d41916b74638e0875"
SRC_URI[sha256sum] = "956b915ce2a7aeaff123e49006be7a0690a0964e96c062703181a36e2e5edb78"

inherit autotools manpages pkgconfig

PACKAGECONFIG ?= "python"
PACKAGECONFIG[manpages] = ", --disable-man-doc, asciidoc-native"
PACKAGECONFIG[python] = "--with-python-bin=${PYTHON}, --with-python-bin="", python3"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'python', 'python3native', '', d)}

ASNEEDED = ""

RRECOMMENDS_${PN} += "kernel-module-nf-tables"

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${libdir_native}/${PYTHON_DIR}"
RDEPENDS_${PN}-python = "python3-core python3-json"
