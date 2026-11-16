SUMMARY = "APIFlask is a lightweight Python web API framework based on Flask and marshmallow-code projects."
HOMEPAGE = "https://github.com/apiflask/apiflask"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5f89d1b0dec37448d4f4163dc3c40e64"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "cf1cc66e69b0c129d7540cc275e87b558802d30f3e23290295191fa442feada3"

RDEPENDS:${PN} += "\
    python3-apispec \
    python3-flask \
    python3-flask-httpauth \
    python3-flask-marshmallow \
    python3-webargs \
    "
