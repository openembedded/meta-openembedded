DESCRIPTION = "Opencv : The Open Computer Vision Library"
HOMEPAGE = "http://opencv.willowgarage.com/wiki/"
SECTION = "libs"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://include/opencv2/opencv.hpp;endline=41;md5=6d690d8488a6fca7a2c192932466bb14"

ARM_INSTRUCTION_SET = "arm"

DEPENDS = "python-numpy v4l-utils libav gtk+ libtool swig swig-native python jpeg bzip2 zlib libpng tiff glib-2.0"

SRC_URI = "svn://code.opencv.org/svn/opencv/branches/2.4;module=opencv;protocol=http \
           file://0001-Fix-CMakeLists.txt-numpy-detection.patch \
          "

SRCREV = "8988"

PV = "2.4.2"
PR = "r1"

S = "${WORKDIR}/opencv"

EXTRA_OECMAKE = "-DPYTHON_NUMPY_INCLUDE_DIRS=${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages/numpy/core/include \
                 -DBUILD_PYTHON_SUPPORT=ON \
                 -DWITH_FFMPEG=ON \
                 -DWITH_GSTREAMER=OFF \
                 -DWITH_V4L=ON \
                 -DWITH_GTK=ON \
                "

inherit distutils-base pkgconfig cmake

export BUILD_SYS
export HOST_SYS
export PYTHON_CSPEC="-I${STAGING_INCDIR}/${PYTHON_DIR}"
export PYTHON="${STAGING_BINDIR_NATIVE}/python"

TARGET_CC_ARCH += "-I${S}/include "

PACKAGES += "${PN}-apps python-opencv"

python populate_packages_prepend () {
    cv_libdir = bb.data.expand('${libdir}', d)
    cv_libdir_dbg = bb.data.expand('${libdir}/.debug', d)
    do_split_packages(d, cv_libdir, '^lib(.*)\.so$', 'lib%s-dev', 'OpenCV %s development package', extra_depends='${PN}-dev', allow_links=True)
    do_split_packages(d, cv_libdir, '^lib(.*)\.la$', 'lib%s-dev', 'OpenCV %s development package', extra_depends='${PN}-dev')
    do_split_packages(d, cv_libdir, '^lib(.*)\.a$', 'lib%s-dev', 'OpenCV %s development package', extra_depends='${PN}-dev')
    do_split_packages(d, cv_libdir, '^lib(.*)\.so\.*', 'lib%s', 'OpenCV %s library', extra_depends='', allow_links=True)

    pn = bb.data.getVar('PN', d, 1)
    metapkg =  pn + '-dev'
    bb.data.setVar('ALLOW_EMPTY_' + metapkg, "1", d)
    blacklist = [ metapkg ]
    metapkg_rdepends = [ ] 
    packages = bb.data.getVar('PACKAGES', d, 1).split()
    for pkg in packages[1:]:
        if not pkg in blacklist and not pkg in metapkg_rdepends and pkg.endswith('-dev'):
            metapkg_rdepends.append(pkg)
    bb.data.setVar('RRECOMMENDS_' + metapkg, ' '.join(metapkg_rdepends), d)
}

FILES_${PN} = ""
FILES_${PN}-apps = "${bindir}/* ${datadir}/OpenCV"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_${PN}-dev = "${includedir} ${libdir}/pkgconfig"
FILES_${PN}-doc = "${datadir}/OpenCV/doc"

ALLOW_EMPTY_${PN} = "1"

INSANE_SKIP_python-opencv = "True"
DESCRIPTION_python-opencv = "Python bindings to opencv"
FILES_python-opencv = "${PYTHON_SITEPACKAGES_DIR}/*"
RDEPENDS_python-opencv = "python-core python-numpy"

do_install_append() {
	cp ${S}/include/opencv/*.h ${D}${includedir}/opencv/
	sed -i '/blobtrack/d' ${D}${includedir}/opencv/cvaux.h
}
