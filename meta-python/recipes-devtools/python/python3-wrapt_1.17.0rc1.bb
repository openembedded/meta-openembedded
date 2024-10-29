SUMMARY = "A Python module for decorators, wrappers and monkey patching."
HOMEPAGE = "http://wrapt.readthedocs.org/"
LICENSE = "BSD-2-Clause"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc34cbad60bc961452eb7ade801d25f7"

SRC_URI[sha256sum] = "5f370f952971e7d17c7d1ead40e49f32345a7f7a5373571ef44d800d06b1899d"

inherit setuptools3 ptest

SRCREV = "75f291f8bf1fe6535b13b0b3dfedca012ca6c8a5"
SRC_URI += "git://github.com/GrahamDumpleton/wrapt;protocol=https;branch=develop \
	file://run-ptest \
"

S = "${WORKDIR}/git"

# python3-misc for 'this' module
RDEPENDS:${PN}-ptest += " \
	python3-misc \
	python3-pytest \
	python3-unittest-automake-output \
"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN}:class-target += "\
    python3-stringold \
    python3-threading \
"

BBCLASSEXTEND = "native"
