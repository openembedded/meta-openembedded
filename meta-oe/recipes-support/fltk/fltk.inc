SUMMARY = "FLTK is a cross-platform C++ GUI toolkit"
HOMEPAGE = "https://www.fltk.org"
SECTION = "libs"
LICENSE = "LGPL-2.0-only AND LicenseRef-FLTK"
LIC_FILES_CHKSUM = "file://COPYING;md5=353c9fd4163eef6500f7e057aed0b083"

SRC_URI = "https://github.com/fltk/fltk/releases/download/release-${PV}/${BP}-source.tar.bz2"

PV = "1.4.5"
SRC_URI[sha256sum] = "b5a52489b7ffae196db2076adb4c1b18170db0d047f5e93539a382603461564b"

UPSTREAM_CHECK_URI = "https://www.fltk.org/software.php"
UPSTREAM_CHECK_REGEX = "fltk-(?P<pver>\d+(\.\d+)+)-source\.tar"

inherit cmake pkgconfig

EXTRA_OECMAKE += " \
    -DFLTK_BUILD_TEST=OFF \
    -DFLTK_BUILD_EXAMPLES=OFF \
    -DFLTK_BUILD_HTML_DOCS=OFF \
    -DFLTK_BUILD_PDF_DOCS=OFF \
"

TARGET_CC_ARCH += "${LDFLAGS} -DXFT_MAJOR=2"
