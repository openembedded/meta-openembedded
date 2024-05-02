SUMMARY = "The Google API Client for Python is a client library for accessing the Plus, \
Moderator, and many other Google APIs."
HOMEPAGE = "https://github.com/googleapis/google-api-python-client"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[sha256sum] = "bbb51b0fbccdf40e536c26341e372d7800f09afebb53103bbcc94e08f14b523b"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
    python3-logging \
    python3-six \
    python3-json \
    python3-core \
    python3-netclient \
    python3-httplib2 \
    python3-uritemplate \
    python3-google-api-core \
"
