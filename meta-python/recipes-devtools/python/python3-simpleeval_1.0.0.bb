SUMMARY = "A simple, safe single expression evaluator library"
HOMEPAGE = "https://pypi.org/project/simpleeval/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE;md5=83843c8f0f3beb18af2f282faecbdebe"

SRC_URI[sha256sum] = "f3d259deeb751d34c63e56747bab384efad63a2dbdb4f130281c42279788ac3c"

inherit pypi python_setuptools_build_meta ptest

BBCLASSEXTEND = "native nativesdk"

SRC_URI += "file://run-ptest"

RDEPENDS:${PN} += " \
	python3-math \
"

RDEPENDS:${PN}-ptest += " \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
	cp -f ${S}/test_simpleeval.py ${D}${PTEST_PATH}/
}
