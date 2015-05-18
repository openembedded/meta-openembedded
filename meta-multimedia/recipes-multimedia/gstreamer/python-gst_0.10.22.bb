SUMMARY = "Python bindings for the GStreamer multimedia framework"
SECTION = "devel/python"
LICENSE = "LGPLv2.1"
DEPENDS = "gstreamer gst-plugins-base python-pygobject"
RDEPENDS_${PN} += "python-pygtk"
PR = "r2"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-python/gst-python-${PV}.tar.bz2 \
           file://python-path.patch"

SRC_URI[md5sum] = "937152fe896241f827689f4b53e79b22"
SRC_URI[sha256sum] = "8f26f519a5bccd770864317e098e5e307fc5ad1201eb96329634b6508b253178"
S = "${WORKDIR}/gst-python-${PV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=39ff67e932b7bdfa9b78bad67151690b"

inherit autotools distutils-base pkgconfig

EXTRA_OECONF += "--with-python-includes=${STAGING_INCDIR}/../"

FILES_${PN} += "${datadir}/gst-python"
FILES_${PN}-dev += "${datadir}/gst-python/0.10/defs"
FILES_${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/gst-0.10/gst/.debug/ ${libdir}/gstreamer-0.10/.debug/"

do_configure_prepend() {
	if [ `find ${STAGING_LIBDIR} -name libpython*.so` ]; then
		ln -sf ${STAGING_LIBDIR}/libpython*.so `find ${STAGING_LIBDIR} -name libpython*.a -exec dirname {} \;`
	fi
}
