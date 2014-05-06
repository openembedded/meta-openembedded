SUMMARY = "Opencv : The Open Computer Vision Library"
HOMEPAGE = "http://opencv.willowgarage.com/wiki/"
SECTION = "libs"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://include/opencv2/opencv.hpp;endline=41;md5=6d690d8488a6fca7a2c192932466bb14"

ARM_INSTRUCTION_SET = "arm"

DEPENDS = "python-numpy libtool swig swig-native python bzip2 zlib glib-2.0"

SRCREV = "df8e28283f09825cca0c2902160b7abebcfe1b64"
SRC_URI = "git://github.com/Itseez/opencv.git;branch=2.4 \
           file://opencv-fix-pkgconfig-generation.patch \
"

PV = "2.4.9+git${SRCPV}"

S = "${WORKDIR}/git"

# Do an out-of-tree build
OECMAKE_SOURCEPATH = "${S}"
OECMAKE_BUILDPATH = "${WORKDIR}/build-${TARGET_ARCH}"

EXTRA_OECMAKE = "-DPYTHON_NUMPY_INCLUDE_DIR:PATH=${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages/numpy/core/include \
                 -DBUILD_PYTHON_SUPPORT=ON \
                 -DWITH_GSTREAMER=OFF \
                 -DCMAKE_SKIP_RPATH=ON \
                 ${@bb.utils.contains("TARGET_CC_ARCH", "-msse3", "-DENABLE_SSE=1 -DENABLE_SSE2=1 -DENABLE_SSE3=1 -DENABLE_SSSE3=1", "", d)} \
                 ${@base_conditional("libdir", "/usr/lib64", "-DLIB_SUFFIX=64", "", d)} \
                 ${@base_conditional("libdir", "/usr/lib32", "-DLIB_SUFFIX=32", "", d)} \
"

PACKAGECONFIG ??= "eigen jpeg libav png tiff v4l \
                   ${@bb.utils.contains("DISTRO_FEATURES", "x11", "gtk", "", d)}"
PACKAGECONFIG[eigen] = "-DWITH_EIGEN=ON,-DWITH_EIGEN=OFF,libeigen,"
PACKAGECONFIG[gtk] = "-DWITH_GTK=ON,-DWITH_GTK=OFF,gtk+,"
PACKAGECONFIG[jpeg] = "-DWITH_JPEG=ON,-DWITH_JPEG=OFF,jpeg,"
PACKAGECONFIG[libav] = "-DWITH_FFMPEG=ON,-DWITH_FFMPEG=OFF,libav,"
PACKAGECONFIG[png] = "-DWITH_PNG=ON,-DWITH_PNG=OFF,libpng,"
PACKAGECONFIG[tiff] = "-DWITH_TIFF=ON,-DWITH_TIFF=OFF,tiff,"
PACKAGECONFIG[v4l] = "-DWITH_V4L=ON,-DWITH_V4L=OFF,v4l-utils,"
PACKAGECONFIG[jasper] = "-DBUILD_JASPER=ON,-DBUILD_JASPER=OFF,jasper"

inherit distutils-base pkgconfig cmake

export BUILD_SYS
export HOST_SYS
export PYTHON_CSPEC="-I${STAGING_INCDIR}/${PYTHON_DIR}"
export PYTHON="${STAGING_BINDIR_NATIVE}/python"

TARGET_CC_ARCH += "-I${S}/include "

PACKAGES += "${PN}-apps python-opencv"

python populate_packages_prepend () {
    cv_libdir = d.expand('${libdir}')
    cv_libdir_dbg = d.expand('${libdir}/.debug')
    do_split_packages(d, cv_libdir, '^lib(.*)\.so$', 'lib%s-dev', 'OpenCV %s development package', extra_depends='${PN}-dev', allow_links=True)
    do_split_packages(d, cv_libdir, '^lib(.*)\.la$', 'lib%s-dev', 'OpenCV %s development package', extra_depends='${PN}-dev')
    do_split_packages(d, cv_libdir, '^lib(.*)\.a$', 'lib%s-dev', 'OpenCV %s development package', extra_depends='${PN}-dev')
    do_split_packages(d, cv_libdir, '^lib(.*)\.so\.*', 'lib%s', 'OpenCV %s library', extra_depends='', allow_links=True)

    pn = d.getVar('PN', 1)
    metapkg =  pn + '-dev'
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    blacklist = [ metapkg ]
    metapkg_rdepends = [ ] 
    packages = d.getVar('PACKAGES', 1).split()
    for pkg in packages[1:]:
        if not pkg in blacklist and not pkg in metapkg_rdepends and pkg.endswith('-dev'):
            metapkg_rdepends.append(pkg)
    d.setVar('RRECOMMENDS_' + metapkg, ' '.join(metapkg_rdepends))
}

PACKAGES_DYNAMIC += "^libopencv-.*"

FILES_${PN} = ""
FILES_${PN}-apps = "${bindir}/* ${datadir}/OpenCV"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_${PN}-dev = "${includedir} ${libdir}/pkgconfig"
FILES_${PN}-doc = "${datadir}/OpenCV/doc"

ALLOW_EMPTY_${PN} = "1"

INSANE_SKIP_python-opencv = "True"
SUMMARY_python-opencv = "Python bindings to opencv"
FILES_python-opencv = "${PYTHON_SITEPACKAGES_DIR}/*"
RDEPENDS_python-opencv = "python-core python-numpy"

do_install_append() {
    cp ${S}/include/opencv/*.h ${D}${includedir}/opencv/
    sed -i '/blobtrack/d' ${D}${includedir}/opencv/cvaux.h

    # Move Python files into correct library folder (for multilib build)
    if [ "$libdir" != "/usr/lib" ]; then
        mv ${D}/usr/lib/* ${D}/${libdir}/
        rm -rf ${D}/usr/lib
    fi
}
