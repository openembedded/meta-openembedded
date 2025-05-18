SUMMARY = "A collection of framework independent HTTP protocol utils."
HOMEPAGE = "https://github.com/MagicStack/httptools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0a2d82955bf3facdf04cb882655e840e"

inherit pypi setuptools3 ptest

SRC_URI[sha256sum] = "4e93eee4add6493b59a5c514da98c939b244fce4a0d8879cd3f466562f4b7d5c"

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
        python3-pytest \
        python3-unittest-automake-output \
"

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}
