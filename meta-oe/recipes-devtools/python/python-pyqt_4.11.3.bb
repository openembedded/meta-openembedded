SUMMARY = "Python Qt4 Bindings"
AUTHOR = "Phil Thomson @ riverbank.co.uk"
HOMEPAGE = "http://riverbankcomputing.co.uk"
SECTION = "devel/python"
LICENSE = "GPLv2 & GPLv3 & GPL_EXCEPTION"
LIC_FILES_CHKSUM = "\
    file://GPL_EXCEPTION.TXT;md5=b73b0be471db679533dc94781c14af58 \
    file://GPL_EXCEPTION_ADDENDUM.TXT;md5=c1e04ec2aa0911061005a801abf81e40 \
    file://OPENSOURCE-NOTICE.TXT;md5=6ad9123620cc04a22c394753ad4767d7 \
    file://LICENSE.GPL2;md5=577ff65f6653562af318bfc3944b1f20 \
    file://LICENSE.GPL3;md5=feee51612c3c1191a1d5f41156fa2c75 \
"
DEPENDS = "sip sip-native qt4-x11-free python"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/pyqt/PyQt-x11-gpl-${PV}.tar.gz \
"
SRC_URI[md5sum] = "997c3e443165a89a559e0d96b061bf70"
SRC_URI[sha256sum] = "853780dcdbe2e6ba785d703d059b096e1fc49369d3e8d41a060be874b8745686"

S = "${WORKDIR}/PyQt-x11-gpl-${PV}"

PARALLEL_MAKE = ""

inherit qmake2 pythonnative python-dir

DISABLED_FEATURES = "PyQt_Desktop_OpenGL PyQt_Accessibility PyQt_SessionManager"

DISABLED_FEATURES_append_arm = " PyQt_qreal_double"

do_configure() {
    echo "py_platform = linux" > pyqt.cfg
    echo "py_inc_dir = %(sysroot)/$includedir/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "py_pylib_dir = %(sysroot)/${libdir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "py_pylib_lib = python%(py_major).%(py_minor)mu" >> pyqt.cfg
    echo "pyqt_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> pyqt.cfg
    echo "pyqt_bin_dir = ${D}/${bindir}" >> pyqt.cfg
    echo "pyqt_sip_dir = ${D}/${datadir}/sip/PyQt4" >> pyqt.cfg
    echo "pyuic_interpreter = ${D}/${bindir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_disabled_features = ${DISABLED_FEATURES}" >> pyqt.cfg
    echo "qt_shared = True" >> pyqt.cfg
    echo "[Qt 4.8]" >> pyqt.cfg
    echo "pyqt_modules = QtCore QtGui QtDeclarative QtNetwork QtSvg QtWebKit" >> pyqt.cfg
    echo yes | python configure-ng.py --verbose --qmake  ${STAGING_BINDIR_NATIVE}/qmake2 --configuration pyqt.cfg --sysroot ${STAGING_DIR_HOST}
}
do_install() {
     oe_runmake install
}

RDEPENDS_${PN} = "python-core python-sip"

FILES_${PN} += "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/sip/PyQt4/"
FILES_${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/*/.debug/"

