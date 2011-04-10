SUMMARY = "XCB: The X protocol C binding library"
DESCRIPTION = "The X protocol C-language Binding (XCB) is a replacement \
for Xlib featuring a small footprint, latency hiding, direct access to \
the protocol, improved threading support, and extensibility."
HOMEPAGE = "http://xcb.freedesktop.org"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=XCB"


BBCLASSEXTEND = "native nativesdk"

SECTION = "x11/libs"
LICENSE = "MIT-X"

PARALLEL_MAKE = ""
DEPENDS = "xcb-proto xproto libxau libxslt-native xcb-proto-native"
# DEPENDS += "xsltproc-native gperf-native"

SRC_URI = "http://xcb.freedesktop.org/dist/libxcb-${PV}.tar.bz2"
#           file://xcbincludedir.patch;patch=1"

PACKAGES =+ "libxcb-composite libxcb-damage libxcb-dpms libxcb-glx \
             libxcb-randr libxcb-record libxcb-render libxcb-res \
             libxcb-screensaver libxcb-shape libxcb-shm libxcb-sync \
             libxcb-xevie libxcb-xf86dri libxcb-xfixes libxcb-xlib \
             libxcb-xprint libxcb-xtest libxcb-xv libxcb-xvmc \
             libxcb-dri2"

FILES_${PN} = "${libdir}/libxcb.so.*"
#FILES_${PN}-dev = "${libdir}/libxcb.* ${libdir}/pkgconfig/*.pc ${includedir}/xcb/*.h"
FILES_libxcb-dri2 = "${libdir}/libxcb-dri2.so.*"
FILES_libxcb-composite = "${libdir}/libxcb-composite.so.*"
FILES_libxcb-damage = "${libdir}/libxcb-damage.so.*"
FILES_libxcb-dpms = "${libdir}/libxcb-dpms.so.*"
FILES_libxcb-glx = "${libdir}/libxcb-glx.so.*"
FILES_libxcb-randr = "${libdir}/libxcb-randr.so.*"
FILES_libxcb-record = "${libdir}/libxcb-record.so.*"
FILES_libxcb-render = "${libdir}/libxcb-render.so.*"
FILES_libxcb-res = "${libdir}/libxcb-res.so.*"
FILES_libxcb-screensaver = "${libdir}/libxcb-screensaver.so.*"
FILES_libxcb-shape = "${libdir}/libxcb-shape.so.*"
FILES_libxcb-shm = "${libdir}/libxcb-shm.so.*"
FILES_libxcb-sync = "${libdir}/libxcb-sync.so.*"
FILES_libxcb-xevie = "${libdir}/libxcb-xevie.so.*"
FILES_libxcb-xf86dri = "${libdir}/libxcb-xf86dri.so.*"
FILES_libxcb-xfixes = "${libdir}/libxcb-xfixes.so.*"
FILES_libxcb-xinerama = "${libdir}/libxcb-xinerama.so.*"
FILES_libxcb-xlib = "${libdir}/libxcb-xlib.so.*"
FILES_libxcb-xprint = "${libdir}/libxcb-xprint.so.*"
FILES_libxcb-xtest = "${libdir}/libxcb-xtest.so.*"
FILES_libxcb-xv = "${libdir}/libxcb-xv.so.*"
FILES_libxcb-xvmc = "${libdir}/libxcb-xvmc.so.*"

inherit autotools pkgconfig

# XCBPROTO_XCBINCLUDEDIR is setup based on `pkg-config --variable=xcbincludedir xcb-proto`
# but the xcbincludedir variable doesn't point into the staging area, so it needs some
# tweeking here:
do_configure_prepend () {
    sed -i "s|XCBPROTO_XCBINCLUDEDIR=|XCBPROTO_XCBINCLUDEDIR=${STAGING_DIR_HOST}/|" \
        ${S}/configure.ac
    sed -i "s|XCBPROTO_XCBPYTHONDIR=|XCBPROTO_XCBPYTHONDIR=${STAGING_DIR_HOST}/|" \
        ${S}/configure.ac
}
