SUMMARY = "A coroutine-based Python networking library"
DESCRIPTION = "gevent is a coroutine-based Python networking library that uses greenlet to provide \
a high-level synchronous API on top of the libevent event loop."
HOMEPAGE = "http://www.gevent.org"
LICENSE = "MIT & Python-2.0 & BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4de99aac27b470c29c6c309e0c279b65 \
                    file://NOTICE;md5=18108df3583462cafd457f024b9b09b5 \
                    file://deps/libev/LICENSE;md5=d6ad416afd040c90698edcdf1cbee347 \
                    "
DEPENDS += "${PYTHON_PN}-greenlet libev c-ares"

RDEPENDS:${PN} = "${PYTHON_PN}-greenlet \
		  ${PYTHON_PN}-mime \
		  ${PYTHON_PN}-pprint \
		 "

SRC_URI[sha256sum] = "43e93e1a4738c922a2416baf33f0afb0a20b22d3dba886720bc037cd02a98575"

inherit pypi setuptools3

# Don't embed libraries, link to the system instead
export GEVENTSETUP_EMBED = "0"

# Delete the embedded copies of libraries so we can't accidentally link to them
do_configure:append() {
	rm -rf ${S}/deps
}
