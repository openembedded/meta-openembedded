SUMMARY = "A recursive directory listing command"
HOMEPAGE = "http://mama.indstate.edu/users/ice/tree/"
SECTION = "console/utils"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "http://mama.indstate.edu/users/ice/tree/src/${BP}.tgz"
SRC_URI[sha256sum] = "0160c535bff2b0dc6a830b9944e981e3427380f63e748da96ced7071faebabf6"

# tree's default CFLAGS for Linux
CFLAGS += "-Wall -DLINUX -D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64"

EXTRA_OEMAKE = "CC='${CC}' CFLAGS='${CFLAGS}' LDFLAGS='${LDFLAGS}'"

do_configure[noexec] = "1"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/${BPN} ${D}${bindir}/
}
