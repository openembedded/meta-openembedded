SUMMARY = "Python interface to Pocketsphinx libraries."
DESCRIPTION = "Pythonsphinx is an Open source large vocabulary, speaker-independent continuous speech recognition engines."
HOMEPAGE = "https://github.com/cmusphinx/pocketsphinx"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause OR BSD-3-Clause OR MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c4d720cdc9c6953091f65e8b01524bb4"

SRC_URI += "file://0001-cython-Use-cythyon3-in-cmake-check.patch"
SRC_URI[sha256sum] = "675778b309a22dfc9b7d37f7621976bba491d2a5f8c59696bd77fd6d07271355"

inherit cmake pkgconfig pypi python3native cython

DEPENDS += "swig-native python3-scikit-build-native gstreamer1.0 gstreamer1.0-plugins-base"

EXTRA_OECMAKE += "-DBUILD_GSTREAMER=ON"

PACKAGES += "${PN}-models ${MLPREFIX}gstreamer1.0-pocketsphinx"

FILES:${MLPREFIX}gstreamer1.0-pocketsphinx = "${libdir}/gstreamer-1.0/"
FILES:${PN}-models = "${datadir}/pocketsphinx/model"
