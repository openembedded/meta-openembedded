DESCRIPTION = "FreeGLUT is a free-software/open-source alternative to the OpenGL \
               Utility Toolkit (GLUT) library"
HOMEPAGE = "https://freeglut.sourceforge.net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=89c0b58a3e01ce3d8254c9f59e78adfb"

SRC_URI = "\
    https://github.com/${BPN}/${BPN}/releases/download/v${PV}/${BPN}-${PV}.tar.gz \
    file://0001-egl-fix-fgPlatformDestroyContext-prototype-for-C23.patch \
"

SRC_URI[sha256sum] = "9c3d4d6516fbfa0280edc93c77698fb7303e443c1aaaf37d269e3288a6c3ea52"

inherit cmake features_check pkgconfig

# depends on virtual/libgl and libxi
REQUIRED_DISTRO_FEATURES = "opengl x11"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'wayland x11', d)}"
PACKAGECONFIG[gles] = "-DFREEGLUT_GLES=ON,-DFREEGLUT_GLES=OFF,"
PACKAGECONFIG[wayland] = "-DFREEGLUT_WAYLAND=ON,-DFREEGLUT_WAYLAND=OFF,libxkbcommon"
PACKAGECONFIG[demos] = "-DFREEGLUT_BUILD_DEMOS=ON,-DFREEGLUT_BUILD_DEMOS=OFF,"
PACKAGECONFIG[x11] = ",,virtual/libx11 libice libxmu libglu libxrandr libxext"
# Add -Wno-implicit-function-declaration since it might be otherwise treated at
# error by clang16+ and this is not really a problem
CFLAGS += "-Wno-implicit-function-declaration"

PROVIDES += "mesa-glut"

DEPENDS += "virtual/libgl libxi"

UPSTREAM_CHECK_REGEX = "releases/tag/v(?P<pver>\d+(\.\d+)+)"
UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"

do_install:append() {
    # Remove buildpaths
    sed -i "s#${RECIPE_SYSROOT}##g" ${D}${libdir}/cmake/FreeGLUT/FreeGLUTTargets.cmake
}
