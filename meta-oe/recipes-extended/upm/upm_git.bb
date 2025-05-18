SUMMARY = "Sensor/Actuator repository for Mraa"
HOMEPAGE = "https://github.com/intel-iot-devkit/upm"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66493d54e65bfc12c7983ff2e884f37f"

DEPENDS = "libjpeg-turbo mraa python3-setuptools-native"

SRCREV = "5cf20df96c6b35c19d5b871ba4e319e96b4df72d"
PV = "2.0.0+git"

SRC_URI = "git://github.com/eclipse/${BPN}.git;protocol=https;branch=master \
           file://0001-CMakeLists.txt-Use-SWIG_SUPPORT_FILES-to-find-the-li.patch \
           file://0001-Use-stdint-types.patch \
           file://0001-initialize-local-variables-before-use.patch \
           file://0001-cmake-Disable-Wno-misleading-indentation-with-clang-.patch \
           file://0001-cmake-Disable-using-Wno-maybe-uninitialized.patch \
           file://0001-include-missing-cstdint.patch \
           "

SRC_URI:append:toolchain-clang:x86 = " file://0001-nmea_gps-Link-with-latomic.patch "

S = "${WORKDIR}/git"

# Depends on mraa which only supports x86 and ARM for now
COMPATIBLE_HOST = "(x86_64.*|i.86.*|aarch64.*|arm.*)-linux"

inherit setuptools3-base cmake pkgconfig

EXTRA_OECMAKE += "-UPYTHON_EXECUTABLE -DWERROR=off"

# override this in local.conf to get needed bindings.
# BINDINGS:pn-upm="python"
# will result in only the python bindings being built/packaged.
# Note: 'nodejs' is disabled by default because the bindings
# generation currently fails with nodejs (>v7.x).
BINDINGS ??= "python"

# nodejs isn't available for armv4/armv5 architectures
BINDINGS:armv4 ??= "python"
BINDINGS:armv5 ??= "python"

PACKAGECONFIG ??= "${@bb.utils.contains('PACKAGES', 'node-${PN}', 'nodejs', '', d)} \
 ${@bb.utils.contains('PACKAGES', 'python3-${PN}', 'python', '', d)}"

PACKAGECONFIG[python] = "-DBUILDSWIGPYTHON=ON -DPYTHON_LIBRARY=${STAGING_LIBDIR}/lib${PYTHON_DIR}${PYTHON_ABI}.so -DPYTHON_INCLUDE_DIR=${STAGING_INCDIR}/${PYTHON_DIR}${PYTHON_ABI}, -DBUILDSWIGPYTHON=OFF, swig-native python3,"
PACKAGECONFIG[nodejs] = "-DBUILDSWIGNODE=ON, -DBUILDSWIGNODE=OFF, swig-native nodejs-native,"

do_configure:prepend() {
    sed -i s:\"lib/${_packages_path}:\"${baselib}/${_packages_path}:g ${S}/cmake/modules/OpenCVDetectPython.cmake
}

FILES:python3-${PN} = "${PYTHON_SITEPACKAGES_DIR}"
RDEPENDS:python3-${PN} += "python3"

FILES:node-${PN} = "${prefix}/lib/node_modules/"
RDEPENDS:node-${PN} += "nodejs"

### Include desired language bindings ###
PACKAGES =+ "${@bb.utils.contains('BINDINGS', 'nodejs', 'node-${PN}', '', d)}"
PACKAGES =+ "${@bb.utils.contains('BINDINGS', 'python', 'python3-${PN}', '', d)}"
