DESCRIPTION = "A file format designed for highly efficient deltas while maintaining good compression"
AUTHOR = "Jonathan Dieter"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cd6e590282010ce90a94ef25dd31410f"

SRC_URI = "git://github.com/zchunk/zchunk.git;protocol=https"

SRCREV = "45720028e69f09d1bec3c6a2af802ae2b06feb6e"
S = "${WORKDIR}/git"

DEPENDS = "\
    curl \
    zstd \
    "

DEPENDS_append_libc-musl = " argp-standalone"
LDFLAGS_append_libc-musl = " -largp"

inherit meson

BBCLASSEXTEND = "native nativesdk"
