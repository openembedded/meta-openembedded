SUMMARY = "Python library implementing ASN.1 types."
HOMEPAGE = "http://pyasn1.sourceforge.net/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=815ac3f9ca693006349094b358ab3155"


SRC_URI[sha256sum] = "fb81622d8f3509f0026b0683fe90fea27be7284d3826a5f2edf97f69151ab0fc"
SRC_URI[md5sum] = "311fff5b2a1303a8cdc334c8867a06f4"

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-codecs \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-math \
    ${PYTHON_PN}-shell \
"

BBCLASSEXTEND = "native nativesdk"
