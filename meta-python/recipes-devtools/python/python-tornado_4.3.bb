SUMMARY  = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
DESCRIPTION = "Tornado is a Python web framework and asynchronous networking library, originally developed at FriendFeed. \
By using non-blocking network I/O, Tornado can scale to tens of thousands of open connections, making it ideal for long \
polling, WebSockets, and other applications that require a long-lived connection to each user."
HOMEPAGE = "http://www.tornadoweb.org/en/stable/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=65b6c818261e415f48f5463a232ad195"

SRCNAME = "tornado"

SRC_URI = " \
    https://pypi.python.org/packages/source/t/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "d13a99dc0b60ba69f5f8ec1235e5b232"
SRC_URI[sha256sum] = "c9c2d32593d16eedf2cec1b6a41893626a2649b40b21ca9c4cac4243bde2efbf"

inherit setuptools

RDEPENDS_${PN} += "python-compression python-numbers python-email python-subprocess \
                   python-pkgutil python-html python-json python-backports-ssl python-certifi"

RDEPENDS_${PN}-test += "${PN} python-unittest"

S = "${WORKDIR}/${SRCNAME}-${PV}"

PACKAGES =+ "\
    ${PN}-test \
"

FILES_${PN}-test = " \
    ${libdir}/${PYTHON_DIR}/site-packages/${SRCNAME}/test \
    ${libdir}/${PYTHON_DIR}/site-packages/${SRCNAME}/testing.py* \
"
