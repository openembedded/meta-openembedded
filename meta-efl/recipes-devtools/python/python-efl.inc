DESCRIPTION = "${PN} bindings"

LICENSE = "LGPLv3.0"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=e6a600fd5e1d9cbde2d983680233ad02"
# there is also GPLv3.0 in COPYING;md5=d32239bcb673463ab874e80d47fae504 but no file seems to use that

AUTHOR = "Gustavo Sverzut Barbieri <barbieri@gmail.com>"
# NOTE: Due to a bug in distutils, even if we don't use pyrex but cython,
# we need to build pyrex otherwise cython doesn't get called to build
# the extension modules.
DEPENDS = "python-cython-native python-pyrex-native python-numeric eina"
RDEPENDS_${PN} += "python-lang"

PROVIDES = "python-ecore python-eldbus python-edje python-elementary python-emotion python-evas"
RPROVIDES_${PN} += "python-ecore python-eldbus python-edje python-elementary python-emotion python-evas"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS

inherit e-base distutils pkgconfig

SRCVER = "${PV}"

SRC_URI = "\
    ${E_RELEASES}/bindings/python/${SRCNAME}-${SRCVER}.tar.gz \
"
S = "${WORKDIR}/${SRCNAME}-${SRCVER}"

FILES_${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/*.egg/*/*/.debug"

PACKAGES += "${PN}-examples"
FILES_${PN}-examples = "${datadir}/${PN}/examples"
