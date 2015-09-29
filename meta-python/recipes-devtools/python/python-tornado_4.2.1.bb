SUMMARY  = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
DESCRIPTION = "Tornado is a Python web framework and asynchronous networking library, originally developed at FriendFeed. \
By using non-blocking network I/O, Tornado can scale to tens of thousands of open connections, making it ideal for long \
polling, WebSockets, and other applications that require a long-lived connection to each user."
HOMEPAGE = "http://www.tornadoweb.org/en/stable/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=c7dc99d243c4004087229f84f6b46828"

SRCNAME = "tornado"

SRC_URI = " \
    https://pypi.python.org/packages/source/t/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "d523204389cfb70121bb69709f551b20"
SRC_URI[sha256sum] = "a16fcdc4f76b184cb82f4f9eaeeacef6113b524b26a2cb331222e4a7fa6f2969"

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
