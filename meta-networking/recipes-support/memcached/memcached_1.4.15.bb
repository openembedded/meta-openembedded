DESCRIPTION = "Free & open source, high-performance, distributed memory object \
caching system, generic in nature, but intended for use in speeding up dynamic \
web applications by alleviating database load."
HOMEPAGE = "http://memcached.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=7e5ded7363d335e1bb18013ca08046ff"

SRC_URI = "http://memcached.googlecode.com/files/memcached-${PV}.tar.gz"

SRC_URI[md5sum] = "36ea966f5a29655be1746bf4949f7f69"
SRC_URI[sha256sum] = "169721ab7a7531add6ae9f6b14b6b5641725fe0b1f0bdf5c3a4327725901e2b4"

DEPENDS = "libevent"

inherit autotools
