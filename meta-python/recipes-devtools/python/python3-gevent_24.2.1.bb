SUMMARY = "A coroutine-based Python networking library"
DESCRIPTION = "gevent is a coroutine-based Python networking library that uses greenlet to provide \
a high-level synchronous API on top of the libevent event loop."
HOMEPAGE = "http://www.gevent.org"
LICENSE = "MIT & Python-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4de99aac27b470c29c6c309e0c279b65"
DEPENDS += "python3-greenlet libev libuv c-ares python3-cython-native"

RDEPENDS:${PN} = "python3-greenlet \
		  python3-mime \
		  python3-pprint \
		  python3-zopeevent \
		  python3-zopeinterface \
		 "

SRC_URI += "file://0001-_setuputils.py-Do-not-add-sys_inc_dir.patch"

SRC_URI[sha256sum] = "432fc76f680acf7cf188c2ee0f5d3ab73b63c1f03114c7cd8a34cebbe5aa2056"

inherit pypi setuptools3

# Don't embed libraries, link to the system provided libs instead
export GEVENTSETUP_EMBED_CARES = "0"
export GEVENTSETUP_EMBED_LIBEV = "0"
export GEVENTSETUP_EMBED_LIBUV = "0"

do_configure:append() {
	# Delete the embedded copies of libraries so we can't accidentally link to them
	rm -rf ${S}/deps

	# Delete the generated cython files, as they are all out of date with python 3.11
	rm -rf ${S}/src/gevent/*.c
}

do_compile:append() {
        sed -i -e 's#${WORKDIR}##g' ${S}/src/gevent/*.c ${S}/src/gevent/libev/*.c ${S}/src/gevent/resolver/*.c
}
