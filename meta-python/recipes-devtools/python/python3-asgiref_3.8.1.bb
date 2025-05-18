DESCRIPTION = "ASGI is a standard for Python asynchronous web apps and servers to communicate with each other, and positioned as an asynchronous successor to WSGI."
HOMEPAGE = "https://pypi.org/project/asgiref/"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f09eb47206614a4954c51db8a94840fa"

SRC_URI += "file://run-ptest \
	    "

SRC_URI[sha256sum] = "c343bd80a0bec947a9860adb4c432ffa7db769836c64238fc34bdc3fec84d590"

export BUILD_SYS
export HOST_SYS

inherit pypi ptest setuptools3

RDEPENDS:${PN}-ptest += " \
    python3-asyncio \
    python3-io \
    python3-multiprocessing \
    python3-pytest \
    python3-unittest-automake-output \
"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

BBCLASSEXTEND = "native nativesdk"
