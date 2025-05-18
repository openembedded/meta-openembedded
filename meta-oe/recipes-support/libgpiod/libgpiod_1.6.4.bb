require libgpiod.inc

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=2caced0b25dfefd4c601d92bd15116de"

SRC_URI[sha256sum] = "7b146e12f28fbca3df7557f176eb778c5ccf952ca464698dba8a61b2e1e3f9b5"

inherit python3native

PACKAGECONFIG[tests] = "--enable-tests,--disable-tests,kmod udev glib-2.0 catch2"
PACKAGECONFIG[python3] = "--enable-bindings-python,--disable-bindings-python,python3"

# Always build tools - they don't have any additional
# requirements over the library.
EXTRA_OECONF = "--enable-tools"

PACKAGES =+ "${PN}-python"
FILES:${PN}-tools += "${bindir}/gpiofind"
FILES:${PN}-ptest += " \
    ${bindir}/gpiod_py_test.py \
    ${libdir}/libgpiomockup.so.* \
"
FILES:${PN}-python = "${PYTHON_SITEPACKAGES_DIR}/*.so"
FILES:${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/*.a"

RRECOMMENDS:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'python3', '${PN}-python', '', d)}"
RRECOMMENDS:${PN}-ptest += " \
    kernel-module-gpio-mockup \
    ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'python3-unittest', '', d)} \
"
RDEPENDS:${PN}-ptest += "python3-packaging"

do_install_ptest:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'true', 'false', d)}; then
        install -m 0755 ${S}/bindings/python/tests/gpiod_py_test.py ${D}${PTEST_PATH}/tests/
    fi
}
