DESCRIPTION = "a recursive directory listing command"
HOMEPAGE = "http://mama.indstate.edu/users/ice/tree/"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "ftp://mama.indstate.edu/linux/${BPN}/${BP}.tgz"
SRC_URI[md5sum] = "abe3e03e469c542d8e157cdd93f4d8a6"
SRC_URI[sha256sum] = "6957c20e82561ac4231638996e74f4cfa4e6faabc5a2f511f0b4e3940e8f7b12"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/${BPN} ${D}${bindir}/${BPN}
}
