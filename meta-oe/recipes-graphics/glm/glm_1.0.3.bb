SUMMARY = "OpenGL Mathematics Library"
DESCRIPTION = "OpenGL Mathematics (GLM) is a header only C++ \
mathematics library for graphics software based on the OpenGL \
Shading Language (GLSL) specifications."
HOMEPAGE = "https://glm.g-truc.net"
BUGTRACKER = "https://github.com/g-truc/glm/issues"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://copying.txt;md5=462e4b97f73ef12f8171c3c546ce4e8d"

SRC_URI = " \
    git://github.com/g-truc/glm;branch=1.0;protocol=https;tag=${PV} \
    file://glmConfig.cmake.in \
    file://glmConfigVersion.cmake.in \
    file://glm.pc.in \
    file://glmTargets.cmake \
"
SRCREV = "8d1fd52e5ab5590e2c81768ace50c72bae28f2ed"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+(\.\d+)+)"


inherit cmake

EXTRA_OECMAKE = "-DCMAKE_POLICY_VERSION_MINIMUM=3.5"

do_install() {
    install -d ${D}${includedir} ${D}${docdir}/glm ${D}${libdir}/pkgconfig ${D}${libdir}/cmake/glm
    cp -R --no-dereference --preserve=mode,links ${S}/glm ${D}${includedir}
    cp -R --no-dereference --preserve=mode,links ${S}/doc ${D}${docdir}/glm
    rm ${D}${includedir}/glm/CMakeLists.txt
    sed "s/@VERSION@/${PV}/" ${UNPACKDIR}/glmConfigVersion.cmake.in > ${D}${libdir}/cmake/glm/glmConfigVersion.cmake
    sed "s/@VERSION@/${PV}/" ${UNPACKDIR}/glmConfig.cmake.in > ${D}${libdir}/cmake/glm/glmConfig.cmake
    sed "s/@VERSION@/${PV}/" ${UNPACKDIR}/glm.pc.in > ${D}${libdir}/pkgconfig/glm.pc
    install -Dm644 ${UNPACKDIR}/glmTargets.cmake ${D}${libdir}/cmake/glm/glmTargets.cmake

}

RDEPENDS:${PN}-dev = ""

BBCLASSEXTEND = "native nativesdk"
