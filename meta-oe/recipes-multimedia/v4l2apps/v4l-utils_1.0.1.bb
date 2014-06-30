SUMMARY = "v4l2 and IR applications"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=48da9957849056017dc568bbc43d8975 \
                    file://COPYING.libv4l;md5=d749e86a105281d7a44c2328acebc4b0"

DEPENDS = "jpeg \
           ${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11', '', d)}"

inherit autotools gettext pkgconfig

# libv4l was absorbed into this, let OE know that
PROVIDES = "libv4l"

SRC_URI = "http://linuxtv.org/downloads/v4l-utils/v4l-utils-${PV}.tar.bz2"
SRC_URI[md5sum] = "b06ea8b15e27cff352b4536e835448bc"
SRC_URI[sha256sum] = "fc0132e82a18dfad9619be3abf50c206e2f449b1c14440cde36f25874fdce7df"

EXTRA_OECONF = "--disable-qv4l2 --enable-shared --with-udevdir=${base_libdir}/udev"

PACKAGES =+ "rc-keymaps libv4l libv4l-dbg libv4l-dev"

FILES_rc-keymaps = "${sysconfdir}/rc* ${base_libdir}/udev/rc*"
FILES_${PN} = "${bindir} ${sbindir} ${base_libdir}/udev/rules.d/70-infrared.rules"
FILES_libv4l += "${libdir}/libv4l*${SOLIBS} ${libdir}/libv4l/*.so ${libdir}/libv4l/plugins/*.so \
                 ${libdir}/libv4l/*-decomp"
FILES_libv4l-dbg += "${libdir}/libv4l/.debug ${libdir}/libv4l/plugins/.debug"
FILES_libv4l-dev += "${libdir}/v4l*${SOLIBSDEV} ${libdir}/libv4l/*.la ${libdir}/libv4l/plugins/*.la"
