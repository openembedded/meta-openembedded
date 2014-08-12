SUMMARY = "C++ library for client-side URL transfers"
HOMEPAGE = "http://code.google.com/p/curlpp/"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS = "curl boost"
DEPENDS_class-native = "curl-native"

SRC_URI = "http://curlpp.googlecode.com/files/curlpp-${PV}.tar.gz \
           file://example21.cpp-remove-deprecated-code.patch \
	"

SRC_URI[md5sum] = "ccc3d30d4b3b5d2cdbed635898c29485"
SRC_URI[sha256sum] = "e3f9427b27c5bddf898d383d45c0d3d5397e2056ff935d9a5cdaef6a9a653bd5"

inherit autotools-brokensep pkgconfig binconfig

EXTRA_OECONF = "--prefix=${STAGING_DIR} \
		--with-boost=${STAGING_DIR} \
		"

do_install() {
	install -d ${D}/${includedir}/curlpp
	cp -r include/curlpp/* 					${D}${includedir}/curlpp

	install -d ${D}${includedir}/utilspp
	cp -r include/utilspp/* 				${D}${includedir}/utilspp

	install -d ${D}/${libdir}
	install	-m 664 src/curlpp/.libs/libcurlpp.a		${D}/${libdir}
	install	-m 664 src/curlpp/.libs/libcurlpp.lai		${D}/${libdir}/libcurlpp.la

	install	-m 775 src/curlpp/.libs/libcurlpp.so.0.0.2	${D}/${libdir}

	install	-m 664 src/utilspp/.libs/libutilspp.a		${D}/${libdir}
	install	-m 664 src/utilspp/.libs/libutilspp.lai		${D}/${libdir}/libutilspp.la

	install	-m 664 src/utilspp/.libs/libutilspp.so.0.0.0	${D}/${libdir}

	install -d ${D}/${libdir}/pkgconfig
	install	-m 664 curlpp.pc				${D}/${libdir}/pkgconfig

	install -d ${D}/${bindir}
	install	-m 755 curlpp-config 				${D}/${bindir}

	install -d ${D}/${includedir}/curlpp
	install -d ${D}/${includedir}/curlpp/internal
	install -d ${D}/${includedir}/utilspp
	install -d ${D}/${includedir}/utilspp/functor
	install -d ${D}/${includedir}/utilspp/singleton

	install	-m 664 include/curlpp/config*			${D}/${includedir}/curlpp
	install	-m 664 include/curlpp/*.h*			${D}/${includedir}/curlpp
	install	-m 664 include/curlpp/*.inl			${D}/${includedir}/curlpp

	install	-m 664 include/curlpp/internal/*.h*		${D}/${includedir}/curlpp/internal
	install	-m 664 include/curlpp/internal/*.inl		${D}/${includedir}/curlpp/internal

	install	-m 664 include/utilspp/*.h*			${D}/${includedir}/utilspp
	install	-m 664 include/utilspp/*.inl			${D}/${includedir}/utilspp

	install	-m 664 include/utilspp/functor/*.h*		${D}/${includedir}/utilspp/functor
	install	-m 664 include/utilspp/singleton/*.h*		${D}/${includedir}/utilspp/singleton
	install	-m 664 include/utilspp/singleton/*.inl		${D}/${includedir}/utilspp/singleton
	install -m 755 curlpp-config				${D}/${bindir}
}

pkg_postinst_${PN}() {
	ln	-sf	libcurlpp.so.0.0.2			/usr/lib/libcurlpp.so.0
	ln	-sf	libcurlpp.so.0.0.2			/usr/lib/libcurlpp.so
	ln	-sf	libutilspp.so.0.0.0			/usr/lib/libutilspp.so.0
	ln	-sf	libutilspp.so.0.0.0			/usr/lib/libutilspp.so
}

PACKAGES =+ "libcurlpp libcurlpp-dev libcurlpp-staticdev"

FILES_lib${BPN} = "${libdir}/lib*.so.* \
		"

FILES_lib${BPN}-dev = "${includedir} \
                      ${libdir}/lib*.la \
                      ${libdir}/pkgconfig \
                      ${bindir}/*-config"

FILES_lib${BPN}-staticdev = "${libdir}/lib*.a"

BBCLASSEXTEND = "native nativesdk"
