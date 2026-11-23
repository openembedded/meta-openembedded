SUMMARY = "Library for interfacing with IIO devices"
HOMEPAGE = "https://wiki.analog.com/resources/tools-software/linux-software/libiio"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later AND MIT AND GPL-2.0-or-later AND BSD-3-Clause"
LICENSE:${PN} = "LGPL-2.1-or-later AND MIT"
LICENSE:${PN}-iiod = "LGPL-2.1-or-later AND MIT AND BSD-3-Clause"
LICENSE:${PN}-tests = "GPL-2.0-or-later"
LICENSE:${PN}-python3 = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=7c13b3376cea0ce68d2d2da0a1b3a72c \
                    file://COPYING_GPL.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING_MIT.txt;md5=6adb420b1f4ed98678908c60d934265a \
"

SRCREV = "9a929664fd3effa500430626803ac59ecf2f4ed3"

SRC_URI = "git://github.com/analogdevicesinc/libiio.git;protocol=https;branch=main;tag=v${PV}"
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

PEP517_SOURCE_PATH = "${S}/bindings/python"

DEPENDS = " \
    flex-native bison-native libaio zstd \
"

inherit python_setuptools_build_meta cmake systemd pkgconfig update-rc.d

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    -DCPP_BINDINGS=ON \
    -DFLEX_TARGET_ARG_COMPILE_FLAGS='--noline' \
    -DBISON_TARGET_ARG_COMPILE_FLAGS='--no-lines' \
    -DUDEV_RULES_INSTALL_DIR=${nonarch_base_libdir}/udev/rules.d \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DWITH_SYSTEMD=ON -DSYSTEMD_UNIT_INSTALL_DIR=${systemd_system_unitdir}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '-DWITH_SYSVINIT=on', '', d)} \
"

PACKAGECONFIG ??= " \
    usb_backend network_backend serial_backend xml_backend hwmon \
    ${@bb.utils.contains('DISTRO_FEATURES', 'zeroconf', 'dnssd', '', d)} \
"

# network_backend, serial_backend and usb_backend depend on xml_backend, so don't switch it off
XML_BACKEND_DISABLE = "${@bb.utils.contains_any('PACKAGECONFIG', 'network_backend serial_backend usb_backend', '', '-DWITH_XML_BACKEND=off', d)}"

PACKAGECONFIG[usb_backend] = "-DWITH_USB_BACKEND=ON -DWITH_XML_BACKEND=ON,-DWITH_USB_BACKEND=OFF,libusb1 libxml2"
PACKAGECONFIG[network_backend] = "-DWITH_NETWORK_BACKEND=ON -DWITH_XML_BACKEND=ON,-DWITH_NETWORK_BACKEND=OFF,libxml2"
PACKAGECONFIG[serial_backend] = "-DWITH_SERIAL_BACKEND=ON -DWITH_XML_BACKEND=ON,-DWITH_SERIAL_BACKEND=off,libserialport libxml2"
PACKAGECONFIG[xml_backend] = "-DWITH_XML_BACKEND=ON,${XML_BACKEND_DISABLE},libxml2"
PACKAGECONFIG[dnssd] = "-DHAVE_DNS_SD=ON,-DHAVE_DNS_SD=off,avahi"
PACKAGECONFIG[libiio-python3] = ""
PACKAGECONFIG[hwmon] = "-DWITH_HWMON=ON,-DWITH_HWMON=OFF"

PACKAGES =+ "${PN}-iiod ${PN}-tests ${PN}-python3"

# Inheriting python_setuptools_build_meta incorrectly adds the dependency on python3-core
# to ${PN} instead of to ${PN}-python3 where it belongs.
RDEPENDS:${PN}:remove = "python3-core"
RDEPENDS:${PN}-python3 = "${PN} python3-core python3-ctypes python3-stringold"

FILES:${PN}-iiod = " \
    ${sysconfdir}/init.d/iiod \
    ${sbindir}/iiod \
    ${systemd_system_unitdir}/iiod.service \
"
FILES:${PN}-tests = "${bindir}"
FILES:${PN}-python3 = "${PYTHON_SITEPACKAGES_DIR}"

SYSTEMD_PACKAGES = "${PN}-iiod"
SYSTEMD_SERVICE:${PN}-iiod = "iiod.service"
INITSCRIPT_PACKAGES = "${PN}-iiod"
INITSCRIPT_NAME:${PN}-iiod = "${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'iiod', '', d)}"

# Explicitly define do_configure, do_compile and do_install because both cmake and python_pep517 have
# EXPORT_FUNCTIONS do_configure do_compile do_install
do_configure() {
    cmake_do_configure
}

do_compile() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'libiio-python3', 'true', 'false', d)}; then
        python_pep517_do_compile
    fi
    cmake_do_compile
}

do_install() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'libiio-python3', 'true', 'false', d)}; then
        python_pep517_do_install
    fi
    cmake_do_install
}
