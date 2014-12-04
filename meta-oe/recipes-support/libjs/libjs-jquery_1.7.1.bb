SUMMARY = "JavaScript library for dynamic web applications"
LICENSE = "GPLv2+ & MIT"
LIC_FILES_CHKSUM = "file://usr/share/doc/libjs-jquery/copyright;md5=2b490904c50a58472452b6e9e1c81203"

SRC_URI = "http://kr.archive.ubuntu.com/ubuntu/pool/main/j/jquery/${BPN}_${PV}-1ubuntu1_all.deb;subdir=${BP}"
SRC_URI[md5sum] = "1ac8a9e4dfe18de22e65baec3dd53f8b"
SRC_URI[sha256sum] = "0551e20c88035d80c00b552707573d62ee89e8e5a204d8b427a6020b065e2542"

JQUERYDIR = "${datadir}/javascript/jquery"
JQUERYDOCDIR = "${docdir}/libjs-jquery"

do_install() {
    install -d -m 0755 ${D}${JQUERYDIR}
    install -m 0644 ${S}${JQUERYDIR}/jquery.js ${D}${JQUERYDIR}/
    install -m 0644 ${S}${JQUERYDIR}/jquery.min.js ${D}${JQUERYDIR}/

    ln -sf jquery.min.js ${D}${JQUERYDIR}/jquery.lite.js
    ln -sf jquery.min.js ${D}${JQUERYDIR}/jquery.pack.js

    install -d -m 0644 ${D}${JQUERYDOCDIR}
    install -m 0644 ${S}${JQUERYDOCDIR}/copyright ${D}${JQUERYDOCDIR}/
}

FILES_${PN} = "/usr/share/javascript/jquery"
