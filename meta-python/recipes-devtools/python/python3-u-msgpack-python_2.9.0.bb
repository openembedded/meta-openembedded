DESCRIPTION = "A portable, lightweight MessagePack serializer and deserializer written in pure Python."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ace2615eae557604711137d1c751d524"

SRC_URI[sha256sum] = "3f632940c2db108d71210795463e249b47e89a08eec0d54a5cdc1a202505ede9"

inherit pypi setuptools3 ptest-python-pytest


do_install_ptest:append() {
       cp -f ${S}/test_umsgpack.py ${D}${PTEST_PATH}/
}

RDEPENDS:${PN} += " \
    python3-datetime \
"

BBCLASSEXTEND = "native nativesdk"
