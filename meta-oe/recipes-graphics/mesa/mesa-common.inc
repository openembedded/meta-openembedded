SECTION = "x11"

DESCRIPTION = "An open source implementation of the OpenGL spec"
HOMEPAGE = "http://mesa3d.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/license.html;md5=7a3373c039b6b925c427755a4f779c1d"

INC_PR = "r12"
PE = "2"

PROTO_DEPS = "xf86driproto glproto"
LIB_DEPS = "virtual/libx11 libxext libxxf86vm libxdamage libxfixes"

DEPENDS = "makedepend-native ${PROTO_DEPS}  ${LIB_DEPS}"

SRC_URI = "ftp://ftp.freedesktop.org/pub/mesa/${PV}/MesaLib-${PV}.tar.bz2;name=archive \
          "
S = "${WORKDIR}/Mesa-${PV}"

PROVIDES = "virtual/libgl"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-glu \
                --disable-glw \
                --disable-glut \
	       "

inherit glx-use-tls

PACKAGES =+ "libegl libegl-dev libegl-dbg libglu libglu-dev libosmesa libosmesa-dev libgl libgl-dev"
FILES_libegl = "${libdir}/libEGL.so.* ${libdir}/egl/*.so"
FILES_libgl = "${libdir}/libGL.so.*"
FILES_libglu = "${libdir}/libGLU.so.*"
FILES_libosmesa = "${libdir}/libOSMesa.so.*"

FILES_libegl-dev = "${libdir}/libEGL.* ${includedir}/EGL"
FILES_libgl-dev = "${libdir}/libGL.* ${includedir}/GL"
FILES_libglu-dev = "${libdir}/libGLU.* ${includedir}/GL/glu*.h"
FILES_libosmesa-dev = "${libdir}/libOSMesa.* ${includedir}/osmesa.h"

FILES_${PN}-dbg += "${libdir}/dri/.debug/*"
FILES_libegl-dbg += "${libdir}/egl/.debug/*"

NATIVE_INSTALL_WORKS = "1"
do_install_append () {
    install -d ${D}/${includedir}/GL
    cp -pPr ${S}/include/GL/internal* ${D}/${includedir}/GL
}
