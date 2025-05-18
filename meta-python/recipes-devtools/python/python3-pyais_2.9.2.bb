SUMMARY = "AIS message decoding"
HOMEPAGE = "https://github.com/M0r13n/pyais"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=79d9e278b76e3e5b3358cd70b328173c"

SRC_URI = "git://github.com/M0r13n/pyais;protocol=https;branch=master"

PV .= "+git"
SRCREV = "9ce53ae21f19e8dc6e7b841dd0ccc246898730ea"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta ptest-python-pytest

PYPI_PACKAGE = "pyais"

do_install_ptest:append() {
	install -d ${D}${PTEST_PATH}/examples/
	install -Dm 0644 ${S}/examples/*.py ${D}${PTEST_PATH}/examples/
	install -Dm 0644 ${S}/examples/*.nmea ${D}${PTEST_PATH}/examples/
	install -Dm 0644 ${S}/examples/*.ais ${D}${PTEST_PATH}/examples/
}

RDEPENDS:${PN} = "python3-attrs python3-bitarray"
RDEPENDS:${PN}-ptest += "\
						 python3-coverage \
						 python3-mypy \
						 python3-pytest-cov \
						 "
