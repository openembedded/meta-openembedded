DESCRIPTION = "High performance data logging and graphing system for time series data."
HOMEPAGE = "http://oss.oetiker.ch/rrdtool/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=44fee82a1d2ed0676cf35478283e0aa0"

DEPENDS = "libpng zlib cairo glib-2.0 libxml2"

SRC_URI = "http://oss.oetiker.ch/rrdtool/pub/rrdtool-${PV}.tar.gz \
           file://0001-rrdtool-eradicate-tcl-support.patch \
          "
SRC_URI[md5sum] = "ffe369d8921b4dfdeaaf43812100c38f"
SRC_URI[sha256sum] = "956aaf431c955ba88dd7d98920ade3a8c4bad04adb1f9431377950a813a7af11"

inherit autotools gettext

EXTRA_AUTORECONF = "-I m4"

EXTRA_OECONF = " \
	--enable-shared \
	--enable-local-libpng \
	--enable-local-zlib \
	 --disable-libwrap \
	--program-prefix='' \
	rd_cv_ieee_works=yes \
	--disable-perl \
	--disable-python \
	--disable-ruby \
	--disable-lua \
	--disable-rpath \
"


