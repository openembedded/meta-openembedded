DESCRIPTION = "Madplay is a command-line MPEG audio decoder and player"
SECTION = "console/multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "ftp://ftp.mars.org/pub/mpeg/${PN}-0.15.2b.tar.gz "

SRC_URI[md5sum] = "6814b47ceaa99880c754c5195aa1aac1"
SRC_URI[sha256sum] = "5a79c7516ff7560dffc6a14399a389432bc619c905b13d3b73da22fa65acede0"

DEPENDS += "libmad libid3tag"
inherit autotools gettext

do_configure_prepend() {

        touch ${S}/NEWS
        touch ${S}/AUTHORS
        touch ${S}/ChangeLog
}

ARM_INSTRUCTION_SET = "arm"

FILES_${PN} = "${bindir}/*"

#  plese add the below line in your conf/local.conf when you have got any LICENCE errors of libmad
# LICENSE_FLAGS_WHITELIST += "commercial"
