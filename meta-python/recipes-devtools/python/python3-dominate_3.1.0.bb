SUMMARY = "Dominate is a Python library for creating and manipulating HTML documents using an elegant DOM API."
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b52f2d57d10c4f7ee67a7eb9615d5d24"

SRC_URI[sha256sum] = "926989c819db2fe91e590590a2902183685edd94829c8cfba5fd71af57c1a982"

inherit ptest-python-pytest python_setuptools_build_meta

SRC_URI = "\
    git://github.com/Knio/dominate.git;protocol=https;branch=master;tag=${PV} \
    file://0001-pyproject.toml-Include-subpackages.patch \
"

SRCREV = "33120ac155461cea04610c4dc5ad69e54ff66f68"

RDEPENDS:${PN} += "\
    python3-numbers \
    python3-threading \
    "
