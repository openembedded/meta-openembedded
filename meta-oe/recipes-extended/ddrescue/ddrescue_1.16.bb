SUMMARY = "Data recovery tool"
DESCRIPTION = "GNU ddrescue is a data recovery tool. It copies data \
    from one file or block device (hard disc, cdrom, etc) to another, \
    trying hard to rescue data in case of read errors."
HOMEPAGE = "http://www.gnu.org/software/ddrescue/ddrescue.html"
SECTION = "console"
LICENSE = "GPLv3+"

LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
                    file://main_common.cc;beginline=5;endline=16;md5=b5a59150a33658cc1ffc31b1a4ffb9f2"

SRC_URI = "${GNU_MIRROR}/${BPN}/${BP}.tar.gz"
SRC_URI[md5sum] = "57b67407e882c6418531d48a2f20d16b"
SRC_URI[sha256sum] = "76b3f2e5fb0306d24f2632c3e168cccb73dc0a348e3a7089cd9230748ff23de6"

inherit autotools

EXTRA_OECONF = "'CXX=${CXX}' 'CPPFLAGS=${CPPFLAGS}' 'CXXFLAGS=${CXXFLAGS}' 'LDFLAGS=${LDFLAGS}'"
