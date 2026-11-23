SUMMARY = "OpenCV 4 compatibility library"
DESCRIPTION = "OpenCV 4.x libraries for applications that do not support OpenCV 5 yet. \
They install side by side with opencv: headers in ${includedir}/opencv4, link \
libraries in ${libdir}/opencv4, CMake files in ${libdir}/cmake/opencv4 and \
opencv4.pc. No bindings, apps or samples are built."
HOMEPAGE = "https://opencv.org/"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

CVE_PRODUCT = "opencv:opencv"

ARM_INSTRUCTION_SET:armv4 = "arm"
ARM_INSTRUCTION_SET:armv5 = "arm"

DEPENDS = "bzip2 zlib glib-2.0 libwebp"

SRCREV_opencv = "0654a42e19215ef25b1d367d822f3c630447e7c7"
SRCREV_contrib = "a8e9acd62cabd30419dba83007f2ac0d07de5e2c"
SRCREV_boostdesc = "34e4206aef44d50e6bbcd0ab06354b52e7466d26"
SRCREV_vgg = "fccf7cd6a4b12079f73bbfb21745f9babcd4eb1d"
SRCREV_face = "8afa57abc8229d611c4937165d20e2a2d9fc5a12"
SRCREV_wechat-qrcode = "a8b69ccc738421293254aec5ddb38bd523503252"
SRCREV_fastcv = "9e8d42b6d7e769548d70b2e5674e263b056de8b4"
# kleidicv must match version of OpenCV
SRCREV_kleidicv = "b1dbf474b8685b97a64d03e828b359fbdc050d1f"

SRCREV_FORMAT = "opencv_contrib_boostdesc_vgg_face_wechat-qrcode_fastcv_kleidicv"
SRC_URI = "git://github.com/opencv/opencv.git;name=opencv;branch=4.x;protocol=https;tag=${PV} \
           git://github.com/opencv/opencv_contrib.git;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/contrib;name=contrib;branch=4.x;protocol=https;tag=${PV} \
           git://github.com/opencv/opencv_3rdparty.git;branch=contrib_xfeatures2d_boostdesc_20161012;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/boostdesc;name=boostdesc;protocol=https \
           git://github.com/opencv/opencv_3rdparty.git;branch=contrib_xfeatures2d_vgg_20160317;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/vgg;name=vgg;protocol=https \
           git://github.com/opencv/opencv_3rdparty.git;branch=contrib_face_alignment_20170818;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/face;name=face;protocol=https \
           git://github.com/WeChatCV/opencv_3rdparty.git;branch=wechat_qrcode;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/wechat_qrcode;name=wechat-qrcode;protocol=https \
           git://github.com/opencv/opencv_3rdparty.git;branch=fastcv/4.x_20250715;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/fastcv;name=fastcv;protocol=https \
           file://0003-To-fix-errors-as-following.patch \
           file://0001-Dont-use-isystem.patch \
           file://download.patch \
           file://0001-Make-ts-module-external.patch \
           file://0008-Do-not-embed-build-directory-in-binaries.patch \
           file://29533.patch \
           "
SRC_URI:append:riscv64 = " file://0001-Use-Os-to-compile-tinyxml2.cpp.patch;patchdir=contrib"
SRC_URI:append:aarch64 = " git://gitlab.arm.com/kleidi/kleidicv;branch=main;tag=26.03;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/3rdparty/kleidicv;name=kleidicv;protocol=https"

UPSTREAM_CHECK_GITTAGREGEX = "^(?P<pver>4(\.\d+)+)$"

# OpenCV wants to download more files during configure.  We download these in
# do_fetch and construct a source cache in the format it expects
OPENCV_DLDIR = "${WORKDIR}/downloads"

do_unpack_extra() {

    md5() {
        # Return the MD5 of $1
        echo $(md5sum $1 | cut -d' ' -f1)
    }
    cache() {
        TAG=$1
        shift
        mkdir --parents ${OPENCV_DLDIR}/$TAG
        for F in $*; do
            DEST=${OPENCV_DLDIR}/$TAG/$(md5 $F)-$(basename $F)
            test -e $DEST || ln -s $F $DEST
        done
    }
    cache xfeatures2d/boostdesc ${S}/boostdesc/*.i
    cache xfeatures2d/vgg ${S}/vgg/*.i
    cache data ${S}/face/*.dat
    cache wechat_qrcode ${S}/wechat_qrcode/*.caffemodel
    cache wechat_qrcode ${S}/wechat_qrcode/*.prototxt
    cache fastcv ${S}/fastcv/fastcv/*.tgz
}
addtask unpack_extra after do_unpack before do_patch

OPENCV4_RELLIBDIR = "${@os.path.relpath(d.getVar('libdir'), d.getVar('prefix') + '/')}"

# Link libraries go to ${libdir}/opencv4, CMake files keep their versioned directory
EXTRA_OECMAKE = "-DOPENCV_EXTRA_MODULES_PATH=${S}/contrib/modules \
    -DOPENCV_LIB_INSTALL_PATH=${OPENCV4_RELLIBDIR}/opencv4 \
    -DOPENCV_3P_LIB_INSTALL_PATH=${OPENCV4_RELLIBDIR}/opencv4/3rdparty \
    -DOPENCV_CONFIG_INSTALL_PATH=${OPENCV4_RELLIBDIR}/cmake/opencv4 \
    -DWITH_1394=OFF \
    -DENABLE_PRECOMPILED_HEADERS=OFF \
    -DCMAKE_SKIP_RPATH=ON \
    -DWITH_IPP=OFF \
    -DOPENCV_GENERATE_PKGCONFIG=ON \
    -DOPENCV_DOWNLOAD_PATH=${OPENCV_DLDIR} \
    -DOPENCV_ALLOW_DOWNLOADS=OFF \
    -DBUILD_opencv_apps=OFF \
    -DBUILD_EXAMPLES=OFF \
    -DBUILD_TESTS=OFF \
    -DBUILD_PERF_TESTS=OFF \
    -DBUILD_JAVA=OFF \
    -DBUILD_opencv_python2=OFF \
    -DBUILD_opencv_python3=OFF \
    -DBUILD_opencv_freetype=OFF \
    -DBUILD_opencv_text=OFF \
    -DWITH_ADE=OFF \
    -DWITH_GTK=OFF \
    -DWITH_GPHOTO2=OFF \
    -DWITH_JASPER=OFF \
    ${@bb.utils.contains("TARGET_CC_ARCH", "-msse3", "-DCPU_DISPATCH=SSE,SSE2,SSE3,SSSE3", "", d)} \
    ${@bb.utils.contains("TARGET_CC_ARCH", "-msse4.1", "-DCPU_DISPATCH=SSE,SSE2,SSE3,SSSE3,SSE41", "", d)} \
    ${@bb.utils.contains("TARGET_CC_ARCH", "-msse4.2", "-DCPU_DISPATCH=SSE,SSE2,SSE3,SSSE3,SSE41,SSE42", "", d)} \
"

LDFLAGS:append:mips = " -Wl,--no-as-needed -latomic -Wl,--as-needed"
LDFLAGS:append:riscv32 = " -Wl,--no-as-needed -latomic -Wl,--as-needed"

EXTRA_OECMAKE:append:x86 = " -DX86=ON"
EXTRA_OECMAKE:append:aarch64 = " -DKLEIDICV_SOURCE_PATH=${S}/3rdparty/kleidicv"

PACKAGECONFIG ??= "eigen jpeg png tiff v4l libv4l tbb"
PACKAGECONFIG:append:class-target = " gstreamer \
    ${@bb.utils.contains_any("LICENSE_FLAGS_ACCEPTED", "commercial_ffmpeg commercial", "libav", "", d)}"

# TBB does not build for powerpc so disable that package config
PACKAGECONFIG:remove:powerpc = "tbb"
# tbb now needs getcontect/setcontext which is not there for all arches on musl
PACKAGECONFIG:remove:libc-musl:riscv64 = "tbb"
PACKAGECONFIG:remove:libc-musl:riscv32 = "tbb"

PACKAGECONFIG[dnn] = "-DBUILD_opencv_dnn=ON -DPROTOBUF_UPDATE_FILES=ON -DBUILD_PROTOBUF=OFF -DCMAKE_CXX_STANDARD=17,-DBUILD_opencv_dnn=OFF,protobuf protobuf-native,"
PACKAGECONFIG[eigen] = "-DWITH_EIGEN=ON,-DWITH_EIGEN=OFF,libeigen gflags glog,"
PACKAGECONFIG[fastcv] = "-DWITH_FASTCV=ON,-DWITH_FASTCV=OFF,"
PACKAGECONFIG[gstreamer] = "-DWITH_GSTREAMER=ON,-DWITH_GSTREAMER=OFF,gstreamer1.0 gstreamer1.0-plugins-base,"
PACKAGECONFIG[jpeg] = "-DWITH_JPEG=ON,-DWITH_JPEG=OFF,jpeg,"
PACKAGECONFIG[libav] = "-DWITH_FFMPEG=ON,-DWITH_FFMPEG=OFF,ffmpeg,"
PACKAGECONFIG[libv4l] = "-DWITH_LIBV4L=ON,-DWITH_LIBV4L=OFF,v4l-utils,"
PACKAGECONFIG[opencl] = "-DWITH_OPENCL=ON,-DWITH_OPENCL=OFF,opencl-headers virtual/libopencl1,"
PACKAGECONFIG[png] = "-DWITH_PNG=ON,-DWITH_PNG=OFF,libpng,"
PACKAGECONFIG[tbb] = "-DWITH_TBB=ON,-DWITH_TBB=OFF,tbb,"
PACKAGECONFIG[tiff] = "-DWITH_TIFF=ON,-DWITH_TIFF=OFF,tiff,"
PACKAGECONFIG[v4l] = "-DWITH_V4L=ON,-DWITH_V4L=OFF,v4l-utils,"

inherit pkgconfig cmake python3native

TARGET_CC_ARCH += "-I${S}/include "

do_compile:prepend() {
    # remove the build host info to improve reproducibility
    if [ -f ${WORKDIR}/build/modules/core/version_string.inc ]; then
        sed -i "s#${WORKDIR}#/workdir#g" ${WORKDIR}/build/modules/core/version_string.inc
    fi
}

do_install:append() {
    # Runtime libraries go to ${libdir} for the loader; the link symlinks stay in opencv4/
    for lib in ${D}${libdir}/opencv4/libopencv_*.so.${PV}; do
        name=$(basename $lib)
        base=${name%.so.${PV}}
        mv $lib ${D}${libdir}/
        for so in ${D}${libdir}/opencv4/$base.so.*; do
            mv $so ${D}${libdir}/
            ln -sf ../$(basename $so) $so
        done
        ln -sf ../$name $lib
    done

    install -d ${D}${libdir}/pkgconfig
    mv ${D}${libdir}/opencv4/pkgconfig/opencv4.pc ${D}${libdir}/pkgconfig/
    rmdir ${D}${libdir}/opencv4/pkgconfig

    rm -f ${D}${bindir}/setup_vars_opencv4.sh
    [ ! -d ${D}${bindir} ] || rmdir --ignore-fail-on-non-empty ${D}${bindir}

    # Generated dispatch sources include the originals by absolute path
    find ${B}/modules -name "*.cpp" | xargs -r grep -l "${S}" | while read -r fn; do
        sed -i -e 's,${S},/usr/src/debug/${PN}/${PV},g' "$fn"
    done
}

do_install:append:class-target() {
    # remove build host path to improve reproducibility
    if [ -f ${D}${libdir}/cmake/opencv4/OpenCVModules.cmake ]; then
        sed -e 's@${STAGING_DIR_HOST}@@g' \
            -i ${D}${libdir}/cmake/opencv4/OpenCVModules.cmake
    fi
}

python populate_packages:prepend () {
    cv_libdir = d.expand('${libdir}')
    do_split_packages(d, cv_libdir, r'^libopencv_(.*)\.so\..*', 'libopencv4-%s', 'OpenCV 4 %s library', extra_depends='', allow_links=True)

    pn = d.getVar('PN')
    d.setVar('ALLOW_EMPTY:' + pn, "1")
    metapkg_rdepends = []
    for pkg in d.getVar('PACKAGES').split():
        if pkg.startswith('libopencv4-') or pkg == pn + '-data':
            metapkg_rdepends.append(pkg)
    d.setVar('RDEPENDS:' + pn, ' '.join(metapkg_rdepends))

    if bb.utils.contains('PACKAGECONFIG', 'fastcv', True, False, d):
        for pkg in ["libopencv4-core", "libopencv4-fastcv", "libopencv4-imgproc"]:
            d.appendVar('RRECOMMENDS:' + pkg, " qcom-fastcv-binaries")
}

PACKAGES =+ "${PN}-data"
PACKAGES_DYNAMIC += "^libopencv4-.*"

FILES:${PN} = ""
FILES:${PN}-data = "${datadir}/opencv4 ${datadir}/licenses/opencv4"
FILES:${PN}-staticdev += "${libdir}/opencv4/3rdparty/*.a"
FILES:${PN}-dev += " \
    ${libdir}/opencv4 \
    ${libdir}/cmake/opencv4 \
"

# Keep the package names apart from those of opencv
AUTO_LIBNAME_PKGS = ""
