SUMMARY  = "Safe C Library"

LICENSE  = "safec"
LIC_FILES_CHKSUM = "file://COPYING;md5=6d0eb7dfc57806a006fcbc4e389cf164"
SECTION = "lib"

inherit autotools pkgconfig

S = "${WORKDIR}/git"
SRCREV = "62e6b2df55084316d027165d286e46beb40513dc"
SRC_URI = "git://github.com/rurban/safeclib.git"

CPPFLAGS_append_libc-musl = " -D_GNU_SOURCE"

COMPATIBLE_HOST = '(x86_64|i.86|powerpc|powerpc64|arm).*-linux'

RDEPENDS_${PN} = "perl"
