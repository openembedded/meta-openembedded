SUMMARY = "Python datetimes made easy"
DESCRIPTION = "Pendulum is a Python package to ease datetimes manipulation. \
It provides classes that are drop-in replacements for the native ones (they \
inherit from them). \
Special care has been taken to ensure timezones are handled correctly, and \
are based on the underlying tzinfo implementation. For example, all \
comparisons are done in UTC or in the timezone of the datetime being used. \
The default timezone, except when using the now(), method will always be UTC. \
"
HOMEPAGE = "https://pendulum.eustace.io"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=869e1c364438f234f09673c4039ed114"

DEPENDS = "python3-maturin-native"

SRCREV = "e06dacedc4cdea90fa1286b3965d180ab94cf251"
PYPI_SRC_URI = "git://github.com/python-pendulum/pendulum;protocol=https;branch=master;tag=${PV};destsuffix=pendulum-${PV}"

SRC_URI += "file://0001-rust-Cargo.toml-inhibit-strip.patch"

require ${BPN}-crates.inc

inherit pypi python_maturin cargo-update-recipe-crates ptest-python-pytest

do_install_ptest:append() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
    # dateutil 4 tzfile objects carry a zoneinfo-style "key", so pendulum now
    # keeps the "Europe/Paris" name instead of the "+02:00" offset the test expects
    sed -i -e "/--automake/ s|\$| --deselect tests/test_main.py::test_instance_with_aware_datetime_any_tzinfo|" \
        ${D}${PTEST_PATH}/run-ptest
}

RDEPENDS:${PN} += "python3-dateutil python3-tzdata"

RDEPENDS:${PN}-ptest += "\
    python3-dateutil-zoneinfo \
    python3-time-machine \
    "
