SUMMARY = "PyZMQ: Python bindings for ZMQ"
DESCRIPTION = "This package contains Python bindings for ZeroMQ. ZMQ is a lightweight and fast messaging implementation."
HOMEPAGE = "http://zeromq.org/bindings:python"
LICENSE = "BSD-3-Clause & LGPL-3.0-only"
LIC_FILES_CHKSUM = "\
    file://COPYING.BSD;md5=11c65680f637c3df7f58bbc8d133e96e \
    file://COPYING.LESSER;md5=12c592fa0bcfff3fb0977b066e9cb69e \
"

DEPENDS = "python3-packaging-native zeromq"

SRC_URI:append = " \
    file://club-rpath-out.patch \
    file://run-ptest \
"
SRC_URI[sha256sum] = "2b381aa867ece7d0a82f30a0c7f3d4387b7cf2e0697e33efaa5bed6c5784abcd"

inherit pypi pkgconfig python_setuptools_build_meta ptest

PACKAGES =+ "\
    ${PN}-test \
"

FILES:${PN}-test += "\
    ${libdir}/${PYTHON_DIR}/site-packages/*/tests \
"

RDEPENDS:${PN} += "\
        ${PYTHON_PN}-json \
        ${PYTHON_PN}-multiprocessing \
"

RDEPENDS:${PN}-ptest += "\
        ${PN}-test \
"

do_compile:prepend() {
    echo [global] > ${S}/setup.cfg
    echo zmq_prefix = ${STAGING_DIR_HOST} >> ${S}/setup.cfg
    echo have_sys_un_h = True >> ${S}/setup.cfg
    echo skip_check_zmq = True >> ${S}/setup.cfg
    echo libzmq_extension = False >> ${S}/setup.cfg
    echo no_libzmq_extension = True >> ${S}/setup.cfg
}

do_install_ptest() {
        install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/zmq/tests/* ${D}${PTEST_PATH}/tests/
}
