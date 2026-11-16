SUMMARY = "httplib2 caching for requests"
HOMEPAGE = "https://pypi.org/project/CacheControl/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=6572692148079ebbbd800be4b9f36c6d"

SRC_URI += "file://0001-pyproject.toml-Relax-the-uv_build-version-bound.patch"
SRC_URI[sha256sum] = "e6220afafa4c22a47dd0badb319f84475d79108100d04e26e8542ef7d3ab05a1"

inherit pypi python_uv_build

RDEPENDS:${PN} += "\
    python3-crypt \
    python3-datetime \
    python3-email \
    python3-filelock \
    python3-json \
    python3-logging \
    python3-msgpack \
    python3-netclient \
    python3-pickle \
    python3-requests \
    python3-urllib3 \
    python3-mmap \
"

BBCLASSEXTEND = "native nativesdk"
