SUMMARY = "Wireless Central Regulatory Domain Database"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"
SECTION = "net"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

SRC_URI = "https://www.kernel.org/pub/software/network/${BPN}/${BP}.tar.xz"
SRC_URI[md5sum] = "b5eb2d0cc23f5e495a59405e34ce437f"
SRC_URI[sha256sum] = "3a4e6f7006599bc5764f86e1e86422710da13ad80e0242147b61c6855ebc915f"

inherit bin_package allarch

do_install() {
    install -d -m0755 ${D}${nonarch_libdir}/crda
    install -d -m0755 ${D}${sysconfdir}/wireless-regdb/pubkeys
    install -m 0644 regulatory.bin ${D}${nonarch_libdir}/crda/regulatory.bin
    install -m 0644 sforshee.key.pub.pem ${D}${sysconfdir}/wireless-regdb/pubkeys/sforshee.key.pub.pem

    install -m 0644 -D regulatory.db ${D}${nonarch_base_libdir}/firmware/regulatory.db
    install -m 0644 regulatory.db.p7s ${D}${nonarch_base_libdir}/firmware/regulatory.db.p7s
}

# Install static regulatory DB in /lib/firmware for kernel to load.
# This requires Linux kernel >= v4.15.
# For kernel <= v4.14, inherit the kernel_wireless_regdb.bbclass in kernel's recipe.
PACKAGES =+ "${PN}-static"
RCONFLICTS_${PN} = "${PN}-static"

FILES_${PN}-static = " \
    ${nonarch_base_libdir}/firmware/regulatory.db \
    ${nonarch_base_libdir}/firmware/regulatory.db.p7s \
"

# Native users might want to use the source of regulatory DB.
# This is for example used by Linux kernel <= v4.14 and kernel_wireless_regdb.bbclass.
do_install_append_class-native() {
    install -m 0644 -D db.txt ${D}${libdir}/crda/db.txt
}

RSUGGESTS_${PN} = "crda"

BBCLASSEXTEND = "native"
