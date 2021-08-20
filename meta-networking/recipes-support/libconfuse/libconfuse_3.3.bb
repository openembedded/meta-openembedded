DESCRIPTION = "libConfuse is a configuration file parser library"
LICENSE = "CUSTOM"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42fa47330d4051cd219f7d99d023de3a"

DEPENDS = "gettext-native"

SRCREV = "a42aebf13db33afd575da6e63f55163d371f776d"
SRC_URI = "git://github.com/libconfuse/libconfuse.git;branch=master"

inherit autotools pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

do_configure_prepend(){
    cd ${S}
    ${S}/autogen.sh
}
