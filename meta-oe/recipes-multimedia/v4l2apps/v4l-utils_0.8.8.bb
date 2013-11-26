DESCRIPTION = "v4l2 and IR applications"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=48da9957849056017dc568bbc43d8975 \
                    file://COPYING.LIB;md5=d749e86a105281d7a44c2328acebc4b0"

PR = "r2"

DEPENDS = "jpeg virtual/libx11"

inherit autotools gettext

# libv4l was absorbed into this, let OE know that
PROVIDES = "libv4l"

SRC_URI = "git://linuxtv.org/v4l-utils.git \
           file://openat.patch \
"
# 54f16ca8183dd8ae8bf4ccc07949795aff0301f5 -> v0.8.8 tag
SRCREV = "0298efdcd1153b8f719b9164548a3f0546f0cb7c"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--disable-qv4l2 --enable-shared --with-udevdir=${base_libdir}/udev" 

do_configure() {
    # autotools_do_configure fails with:
    # | configure.ac:139: error: required file 'build-aux/config.rpath' not found
    autoreconf -vfi 
    oe_runconf
}

PACKAGES =+ "rc-keymaps libv4l libv4l-dbg libv4l-dev"

FILES_rc-keymaps = "${sysconfdir}/rc* ${base_libdir}/udev/rc*"
FILES_${PN} = "${bindir} ${sbindir} ${base_libdir}/udev/rules.d/70-infrared.rules"
FILES_libv4l += "${libdir}/libv4l/* ${libdir}/*.so.*"
FILES_libv4l-dbg += "${libdir}/libv4l/.debug"
FILES_libv4l-dev += "${libdir}/*.so ${includedir}/lib* ${libdir}/pkgconfig/lib*"

