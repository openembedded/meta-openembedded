DESCRIPTION = "FreeGLUT is a free-software/open-source alternative to the OpenGL \
               Utility Toolkit (GLUT) library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=89c0b58a3e01ce3d8254c9f59e78adfb"

SRC_URI = "https://sourceforge.net/projects/${BPN}/files/${BPN}/${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "cd5c670c1086358598a6d4a9d166949d"
SRC_URI[sha256sum] = "d4000e02102acaf259998c870e25214739d1f16f67f99cb35e4f46841399da68"

inherit cmake features_check pkgconfig

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'wayland x11', d)}"
PACKAGECONFIG[gles] = "-DFREEGLUT_GLES=ON,-DFREEGLUT_GLES=OFF,"
PACKAGECONFIG[wayland] = "-DFREEGLUT_WAYLAND=ON,-DFREEGLUT_WAYLAND=OFF,libxkbcommon"
PACKAGECONFIG[demos] = "-DFREEGLUT_BUILD_DEMOS=ON,-DFREEGLUT_BUILD_DEMOS=OFF,"
PACKAGECONFIG[x11] = ",,virtual/libx11 libice libxmu libglu libxrandr libxext"
# Do not use -fno-common, check back when upgrading to new version it might not be needed
CFLAGS += "-fcommon"

PROVIDES += "mesa-glut"

DEPENDS = "virtual/libgl libxi"

do_install:append() {
    # Remove buildpaths
    sed -i "s#${RECIPE_SYSROOT}##g" ${D}${libdir}/cmake/FreeGLUT/FreeGLUTTargets.cmake
}
