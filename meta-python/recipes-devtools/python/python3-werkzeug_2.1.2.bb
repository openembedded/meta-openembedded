SUMMARY = "The Swiss Army knife of Python web development"
DESCRIPTION = "\
Werkzeug started as simple collection of various utilities for WSGI \
applications and has become one of the most advanced WSGI utility modules. \
It includes a powerful debugger, full featured request and response objects, \
HTTP utilities to handle entity tags, cache control headers, HTTP dates, \
cookie handling, file uploads, a powerful URL routing system and a bunch \
of community contributed addon modules."
HOMEPAGE = "http://werkzeug.pocoo.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=5dc88300786f1c214c1e9827a5229462"

PYPI_PACKAGE = "Werkzeug"

SRC_URI += "file://CVE-2023-25577.patch \
            file://CVE-2023-23934.patch \
            file://CVE-2024-34069-0001.patch \
            file://CVE-2024-34069-0002.patch \
            file://CVE-2024-49767.patch"

SRC_URI[sha256sum] = "1ce08e8093ed67d638d63879fd1ba3735817f7a80de3674d293f5984f25fb6e6"

inherit pypi setuptools3

CLEANBROKEN = "1"

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-difflib \
    ${PYTHON_PN}-email \
    ${PYTHON_PN}-html \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-pkgutil \
    ${PYTHON_PN}-pprint \
    ${PYTHON_PN}-simplejson \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-unixadmin \
    ${PYTHON_PN}-misc \
    ${PYTHON_PN}-profile \
"

# Windows-only vulnerabilities
CVE_CHECK_IGNORE = "CVE-2024-49766 CVE-2025-66221 CVE-2026-21860 CVE-2026-27199"
