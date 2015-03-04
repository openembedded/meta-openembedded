SUMMARY = "Wireless Central Regulatory Domain Agent"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"

LICENSE = "copyleft-next-0.3.0 & ISC"
LIC_FILES_CHKSUM = "file://copyleft-next-0.3.0;md5=8743a2c359037d4d329a31e79eabeffe \
                    file://${WORKDIR}/wireless-regdb-2014.11.18/LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

DEPENDS = "python-m2crypto-native python-native libgcrypt libnl"

SRC_URI = "https://www.kernel.org/pub/software/network/crda/${BP}.tar.xz;name=crda \
           https://www.kernel.org/pub/software/network/wireless-regdb/wireless-regdb-2014.11.18.tar.xz;name=bin \
           file://crda-Fix-the-linking-order-to-avoid-compilation-erro.patch \
           file://crda-Add-DESTDIR-support-in-install-libreg-rules-in-.patch \
           file://do-not-run-ldconfig-if-destdir-is-set.patch \
           file://fix-linking-of-libraries-used-by-reglib.patch \
"
SRC_URI[crda.md5sum] = "66b1b0417c1ad19f0009a5c0c0c1aebc"
SRC_URI[crda.sha256sum] = "2f85da7ab0170b140d6ed62596c8f268d4a7dedecf84cac7182ada979742ff59"

SRC_URI[bin.md5sum] = "d750c402c5510add7380edcb1d9b75b2"
SRC_URI[bin.sha256sum] = "eab6b50f30748a8b0065ba38cf3df05aac161a5861ae0a6c3cfd01d38a71c9dd"

inherit python-dir pythonnative
# Recursive make problem
EXTRA_OEMAKE = "MAKEFLAGS= DESTDIR=${D} LIBDIR=${libdir}/crda LDLIBREG='-Wl,-rpath,${libdir}/crda -lreg'"

do_compile() {
    oe_runmake all_noverify
}

do_install() {
    oe_runmake SBINDIR=${sbindir}/ install

    install -d ${D}${libdir}/crda/

    install -m 0644 ${WORKDIR}/wireless-regdb-2014.11.18/regulatory.bin ${D}${libdir}/crda/regulatory.bin
}


RDEPENDS_${PN} = "udev"
FILES_${PN} += "${libdir}crda/regulatory.bin \
                ${base_libdir}/udev/rules.d/85-regulatory.rules \
"
