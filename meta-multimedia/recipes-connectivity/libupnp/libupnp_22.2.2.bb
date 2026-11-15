SUMMARY = "Portable SDK for UPnP* Devices"
DESCRIPTION = "The Portable SDK for UPnP Devices is an SDK for development of \
UPnP device and control point applications. It consists of the core UPnP \
protocols along with a UPnP-specific eXtensible Markup Language (XML) parser \
supporting the Document Object Model (DOM) Level 2 API and an optional, \
integrated mini web server for serving UPnP related documents."
HOMEPAGE = "http://pupnp.sourceforge.net/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"

SRCREV = "fac0494691434c35243131f66a0b27ea0c29f700"
SRC_URI = "git://github.com/pupnp/pupnp.git;protocol=https;branch=main;tag=release-${PV} \
           file://run-ptest \
          "

inherit cmake pkgconfig ptest python3native

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DUPNP_BUILD_STATIC=OFF \
    -DUPNP_BUILD_SAMPLES=OFF \
    -DUPNP_ENABLE_TESTING=OFF \
    -DUPNP_ENABLE_TESTING_INTEGRATION=OFF \
    -DUPNP_MINISERVER_REUSEADDR=ON \
    -DDOWNLOAD_AND_BUILD_DEPS=OFF \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[openssl] = "-DUPNP_ENABLE_OPEN_SSL=ON,-DUPNP_ENABLE_OPEN_SSL=OFF,openssl"

DEPENDS += "${@bb.utils.contains('PTEST_ENABLED', '1', 'googletest python3-pytest-native', '', d)}"

# Most tests use internal symbols and only link against the static
# libraries, which share a CMake export set with the shared ones, so the
# tests get a separate build tree and the installed library stays
# shared-only.
PTEST_B = "${B}/ptest"

do_configure_ptest() {
    mkdir -p ${PTEST_B}
    cd ${PTEST_B}
    cmake ${OECMAKE_GENERATOR_ARGS} ${OECMAKE_SOURCEPATH} ${OECMAKE_ARGS} \
        ${EXTRA_OECMAKE} -Wno-dev \
        -DUPNP_BUILD_STATIC=ON \
        -DUPNP_ENABLE_TESTING=ON \
        -DPython3_EXECUTABLE=${PYTHON}
}

do_compile_ptest() {
    cmake --build ${PTEST_B} -- ${EXTRA_OECMAKE_BUILD}
}

do_install_ptest() {
    cd ${PTEST_B}
    find . -name CTestTestfile.cmake | while read f; do
        install -D -m 0644 $f ${D}${PTEST_PATH}/$f
        sed -i -e 's# _BACKTRACE_TRIPLES "[^"]*"##' \
            -e 's#${PTEST_B}#${PTEST_PATH}#g' -e 's#${S}#${PTEST_PATH}#g' \
            -e 's#${PYTHON}#${bindir}/python3#g' ${D}${PTEST_PATH}/$f
    done
    find . -path '*/CMakeFiles' -prune -o -type f -perm -u+x ! -name '*.so*' -print | while read f; do
        install -D -m 0755 $f ${D}${PTEST_PATH}/$f
    done

    install -d ${D}${PTEST_PATH}/ixml/test ${D}${PTEST_PATH}/tests ${D}${PTEST_PATH}/upnp/inc
    cp -r ${S}/ixml/test/testdata ${D}${PTEST_PATH}/ixml/test/
    install -m 0644 ${S}/tests/*.py ${D}${PTEST_PATH}/tests/
    install -m 0755 ${S}/upnp/test/check_symbol_visibility.sh ${D}${PTEST_PATH}/upnp/test/
    # The Python and symbol visibility tests look for the library in the
    # build tree; point them at the installed one.
    install -m 0644 ${PTEST_B}/upnp/inc/upnpconfig.h ${D}${PTEST_PATH}/upnp/inc/
    ln -sf ${libdir}/libupnp.so.${PV} ${D}${PTEST_PATH}/upnp/libupnp.so
    ln -sf ${libdir}/libupnp.so.${PV} ${D}${PTEST_PATH}/upnp/libupnp.so.${PV}
}

RDEPENDS:${PN}-ptest += "cmake python3-core python3-ctypes python3-fcntl python3-io python3-pytest"
