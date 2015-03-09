SUMMARY = "SIP is a C++/Python Wrapper Generator"
AUTHOR = "Phil Thompson"
HOMEPAGE = "http://www.riverbankcomputing.co.uk/sip"
SECTION = "devel"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE-GPL2;md5=e91355d8a6f8bd8f7c699d62863c7303"
DEPENDS_class-target = "qt4-x11-free python"

# riverbankcomputing is upstream, but keeps only latest version, sf usually have few older
#SRC_URI = "http://www.riverbankcomputing.com/static/Downloads/sip4/sip-${PV}.tar.gz"
SRC_URI = "${SOURCEFORGE_MIRROR}/project/pyqt/sip/sip-${PV}/sip-${PV}.tar.gz"
SRC_URI[md5sum] = "a9840670a064dbf8f63a8f653776fec9"
SRC_URI[sha256sum] = "ceda443fc5e129e67a067e2cd7b73ff037f8b10b50e407baa2b1d9f2199d57f5"

BBCLASSEXTEND = "native"

inherit qmake2 python-dir pythonnative
PACKAGES += "python-sip"


EXTRA_QMAKEVARS_POST += "CONFIG=console"

export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

do_configure_prepend_class-target() {
    echo "py_platform = linux" > sip.cfg
    echo "py_inc_dir = %(sysroot)/${includedir}/python%(py_major).%(py_minor)" >> sip.cfg
    echo "sip_bin_dir = ${D}/${bindir}" >> sip.cfg
    echo "sip_inc_dir = ${D}/${includedir}" >> sip.cfg
    echo "sip_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> sip.cfg
    echo "sip_sip_dir = ${D}/${datadir}/sip" >> sip.cfg
    python configure.py --use-qmake --configuration sip.cfg --sysroot ${STAGING_DIR_HOST}
}
do_configure_prepend_class-native() {
    echo "py_platform = linux" > sip.cfg
    echo "py_inc_dir = ${includedir}/python%(py_major).%(py_minor)" >> sip.cfg
    echo "sip_bin_dir = ${D}/${bindir}" >> sip.cfg
    echo "sip_inc_dir = ${D}/${includedir}" >> sip.cfg
    echo "sip_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> sip.cfg
    echo "sip_sip_dir = ${D}/${datadir}/sip" >> sip.cfg
    python configure.py --use-qmake --configuration sip.cfg --sysroot ${STAGING_DIR_NATIVE}
}
do_install() {
    oe_runmake install
}

FILES_python-${PN} = "${libdir}/${PYTHON_DIR}/site-packages/"
FILES_${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/.debug"

