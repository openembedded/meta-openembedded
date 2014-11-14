SUMMARY  = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
DESCRIPTION = "Tornado is a Python web framework and asynchronous networking library, originally developed at FriendFeed. \
By using non-blocking network I/O, Tornado can scale to tens of thousands of open connections, making it ideal for long \
polling, WebSockets, and other applications that require a long-lived connection to each user."
HOMEPAGE = "http://www.tornadoweb.org/en/stable/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=5208df23820f490f691712a654be256d"

SRCNAME = "tornado"

SRC_URI = " \
    https://pypi.python.org/packages/source/t/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "985c0e704b765c33a6193d49d1935588"
SRC_URI[sha256sum] = "900c5124ebdb6598ca8e8a0c5888f41a5f14117952d5515258e3d20222b21bfa"

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
