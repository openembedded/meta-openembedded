SUMMARY = "Engine.IO server"
HOMEPAGE = "https://github.com/miguelgrinberg/python-engineio/"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42d0a9e728978f0eeb759c3be91536b8"

inherit pypi setuptools3

PYPI_PACKAGE = "python-engineio"

RDEPENDS:${PN} += " \
	python3-netclient \
	python3-json \
	python3-logging \
	python3-compression \
	python3-asyncio \
"

SRC_URI[sha256sum] = "fed35eeacfa21f53f1fc05ef0cadd65a50780364da3a2be7650eb92f928fdb11"
