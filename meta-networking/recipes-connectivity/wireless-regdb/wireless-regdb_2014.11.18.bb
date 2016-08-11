SUMMARY = "Wireless Central Regulatory Domain Database"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"
SECTION = "net"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

SRC_URI = "https://www.kernel.org/pub/software/network/${BPN}/${BP}.tar.xz"
SRC_URI[md5sum] = "d750c402c5510add7380edcb1d9b75b2"
SRC_URI[sha256sum] = "eab6b50f30748a8b0065ba38cf3df05aac161a5861ae0a6c3cfd01d38a71c9dd"

inherit bin_package

do_install() {
    install -d -m0755 ${D}${libdir}/crda
    install -m 0644 regulatory.bin ${D}${libdir}/crda/regulatory.bin
}

RSUGGESTS_${PN} = "crda"
