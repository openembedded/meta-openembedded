SUMMARY = "Pyzmq provides Zero message queue access for the Python language"
HOMEPAGE = "http://zeromq.org/bindings:python"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=11c65680f637c3df7f58bbc8d133e96e"
DEPENDS = "zeromq"

SRC_URI = "https://pypi.python.org/packages/source/p/pyzmq/pyzmq-${PV}.tar.gz \
           file://club-rpath-out.patch \
"
SRC_URI[md5sum] = "8d3351a8ca2ca2a272a3f96bcb963e41"
SRC_URI[sha256sum] = "5d6e045634456cf5496f50871fd3e3a5ede2b90433284dbfe985bb67c97f77bd"

S = "${WORKDIR}/pyzmq-${PV}"

inherit setuptools pkgconfig

RDEPENDS_${PN} += "python-multiprocessing"

FILES_${PN}-dbg =+ "${PYTHON_SITEPACKAGES_DIR}/zmq/backend/cython/.debug"

do_compile_prepend() {
    echo [global] > ${S}/setup.cfg
    echo zmq_prefix = ${STAGING_DIR_HOST} >> ${S}/setup.cfg
    echo have_sys_un_h = True >> ${S}/setup.cfg
    echo skip_check_zmq = True >> ${S}/setup.cfg
    echo libzmq_extension = False >> ${S}/setup.cfg
    echo no_libzmq_extension = True >> ${S}/setup.cfg
}
