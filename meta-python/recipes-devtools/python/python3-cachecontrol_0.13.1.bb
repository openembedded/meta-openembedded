SUMMARY = "httplib2 caching for requests"
HOMEPAGE = "https://pypi.org/project/CacheControl/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=12;endline=12;md5=e2fd6ddcf506e08972d5ba4b93c0022e"

SRC_URI[sha256sum] = "f012366b79d2243a6118309ce73151bf52a38d4a5dac8ea57f09bd29087e506b"

inherit pypi python_poetry_core

UPSTREAM_CHECK_URI = "https://pypi.python.org/pypi/CacheControl/"
UPSTREAM_CHECK_REGEX = "/CacheControl/(?P<pver>(\d+[\.\-_]*)+)"

RDEPENDS:${PN} += "\
    python3-crypt \
    python3-datetime \
    python3-email \
    python3-lockfile \
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
