SUMMARY = "httplib2 caching for requests"
HOMEPAGE = "https://pypi.org/project/CacheControl/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=12;endline=12;md5=62d6ee40749ec0b76d8a1200a1562369"

# On PyPi, this is "CacheControl", rather than "cachecontrol", so we need to
# override PYPI_PACKAGE so fetch succeeds.
PYPI_PACKAGE = "CacheControl"

SRC_URI[sha256sum] = "b5d0c98f7ee9f02e2d5a85af98b9a76c0a2c73c972c142291f823a0ed2eb7a78"

inherit pypi setuptools3

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
