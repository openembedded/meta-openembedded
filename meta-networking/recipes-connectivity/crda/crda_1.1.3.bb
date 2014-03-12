SUMMARY = "Wireless Central Regulatory Domain Agent"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"


DEPENDS = "python-m2crypto-native python-native libgcrypt libnl"

SRC_URI = "http://wireless.kernel.org/download/crda/${BP}.tar.bz2;name=crda \
           http://wireless.kernel.org/download/wireless-regdb/regulatory.bins/2013.01.11-regulatory.bin;name=bin \
"
SRC_URI[crda.md5sum] = "29579185e06a75675507527243d28e5c"
SRC_URI[crda.sha256sum] = "aa8a7fe92f0765986c421a5b6768a185375ac210393df0605ee132f6754825f0"
SRC_URI[bin.md5sum] = "e0c8a5ca63fb8bf803213f9a0c90b50b"
SRC_URI[bin.sha256sum] = "b1ee0b20c123c612dfdb6851ab42c01666f66fb583e0e590942f19bb54cf84be"

inherit python-dir pythonnative
# Recursive make problem
EXTRA_OEMAKE = "MAKEFLAGS= DESTDIR=${D}"

do_compile() {
    oe_runmake all_noverify
}

do_install() {
    oe_runmake SBINDIR=${sbindir}/ install

    install -d ${D}${libdir}/crda/

    install -m 0644 ${WORKDIR}/2013.01.11-regulatory.bin ${D}${libdir}/crda/regulatory.bin
}


RDEPENDS_${PN} = "udev"
FILES_${PN} += "${libdir}crda/regulatory.bin \
                ${base_libdir}/udev/rules.d/85-regulatory.rules \
"
