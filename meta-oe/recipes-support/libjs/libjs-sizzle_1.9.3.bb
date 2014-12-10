SUMMARY = "Pure-JavaScript CSS selector engine"
LICENSE = "GPLv2+ & MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://usr/share/doc/libjs-sizzle/copyright;md5=9b35efb1635ff8f06d1984376b06ee5a"

SRC_URI = "http://kr.archive.ubuntu.com/ubuntu/pool/universe/s/sizzle/${BPN}_${PV}-1_all.deb;subdir=${BP}"
SRC_URI[md5sum] = "748b8805e21caed658f6765f7c491d46"
SRC_URI[sha256sum] = "2a6468763c40a30f6f7d0df8906cd17aaebd6edaa5478aeaffd7b6b5fb8abd35"

SIZZLEDIR = "${datadir}/javascript/sizzle"
SIZZLEDOCDIR = "${docdir}/libjs-sizzle"

do_install() {
    install -d -m 0755 ${D}${SIZZLEDIR}
    install -m 0644 ${S}${SIZZLEDIR}/sizzle.js ${D}${SIZZLEDIR}/
    install -m 0644 ${S}${SIZZLEDIR}/sizzle.min.js ${D}${SIZZLEDIR}/
    install -d -m 0755 ${D}${SIZZLEDOCDIR}
    install -m 0644 ${S}${SIZZLEDOCDIR}/* ${D}${SIZZLEDOCDIR}/
}

FILES_${PN} = "/usr/share/javascript/sizzle/"
