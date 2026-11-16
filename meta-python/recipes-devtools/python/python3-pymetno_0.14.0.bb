SUMMARY = "Python library to talk to the met.no api"
HOMEPAGE = "https://github.com/Danielhiversen/pyMetno"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d503272f52c35147ec960cb56a03bf4"

SRC_URI = "git://github.com/Danielhiversen/pyMetno.git;protocol=https;branch=master"
SRCREV = "205f93adf2f29fe0354bc4fca432e3d3ee67f1c2"

inherit setuptools3

RDEPENDS:${PN} = "\
	python3-aiohttp (>=3.6.1) \
	python3-async-timeout (>=3.0.1) \
	python3-pytz (>=2019.3) \
"
