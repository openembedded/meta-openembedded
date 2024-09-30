SUMMARY = "Python driver for MongoDB <http://www.mongodb.org>"
DESCRIPTION = "\
The PyMongo distribution contains tools for interacting with MongoDB \
database from Python. The bson package is an implementation of the BSON \
format for Python. The pymongo package is a native Python driver for \
MongoDB. The gridfs package is a gridfs implementation on top of pymongo."
HOMEPAGE = "http://github.com/mongodb/mongo-python-driver"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[sha256sum] = "b7f2d34390acf60e229c30037d1473fcf69f4536cd7f48f6f78c0c931c61c505"

inherit pypi python_setuptools_build_meta python_hatchling

PACKAGES =+ "python3-bson"

FILES:python3-bson = "${PYTHON_SITEPACKAGES_DIR}/bson/*"

DEPENDS += " \
     python3-hatch-requirements-txt-native \
"

RDEPENDS:python3-bson += " \
     python3-datetime \
     python3-json \
     python3-netclient \
     python3-numbers \
     python3-threading \
"

RDEPENDS:${PN} += " \
    python3-bson \
    python3-pprint \
    python3-difflib \
    python3-logging \
"
