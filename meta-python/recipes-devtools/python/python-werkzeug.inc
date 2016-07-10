SUMMARY = "The Swiss Army knife of Python web development"
DESCRIPTION = "\
Werkzeug started as simple collection of various utilities for WSGI \
applications and has become one of the most advanced WSGI utility modules. \
It includes a powerful debugger, full featured request and response objects, \
HTTP utilities to handle entity tags, cache control headers, HTTP dates, \
cookie handling, file uploads, a powerful URL routing system and a bunch \
of community contributed addon modules."
HOMEPAGE = "http://werkzeug.pocoo.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a68f5361a2b2ca9fdf26b38aaecb6faa"

SRC_URI[md5sum] = "780967186f9157e88f2bfbfa6f07a893"
SRC_URI[sha256sum] = "cc64dafbacc716cdd42503cf6c44cb5a35576443d82f29f6829e5c49264aeeee"

PYPI_PACKAGE = "Werkzeug"

RDEPENDS_${PN} += " \
    python-difflib \
    python-email \
    python-html \
    python-json \
    python-netserver \
    python-numbers \
    python-pkgutil \
    python-subprocess \
    python-threading \
    python-unixadmin \
    python-zlib \
"
