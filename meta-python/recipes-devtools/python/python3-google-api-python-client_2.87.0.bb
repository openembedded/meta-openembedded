SUMMARY = "The Google API Client for Python is a client library for accessing the Plus, \
Moderator, and many other Google APIs."
HOMEPAGE = "https://github.com/googleapis/google-api-python-client"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[sha256sum] = "bbea5869877c822d12d318943833d988497b3a18b9ca2386967118074db676f3"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-core \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-httplib2 \
    ${PYTHON_PN}-uritemplate \
    ${PYTHON_PN}-google-api-core \
"
