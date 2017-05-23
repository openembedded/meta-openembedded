SUMMARY = "Intel RSTe with Linux OS SSI API Library"

DESCRIPTION = "Intel Rapid Storage Technology enterprise with Linux OS* Standard Storage Interface API Library. \
The library allows user to manage storage devices including creating and managing Raid arrays on systems with Intel chipset."

HOMEPAGE = "http://irstessi.sourceforge.net/"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=0413ff365e0bd733c4869a6797551c6f"

DEPENDS += "sg3-utils"

SRC_URI = "http://sourceforge.net/projects/irstessi/files/${BPN}.${PV}.tgz"
SRC_URI[md5sum] = "02f16d7cbd30d28034093212906591f5"
SRC_URI[sha256sum] = "e10d283b0f211afb8ebd0bde87c097769613d30a052cdf164753e35e803264c7"

S ="${WORKDIR}/${BPN}.${PV}"

inherit autotools-brokensep

do_configure_prepend(){
    ./autogen.sh
}

RDEPENDS_${PN} += "mdadm"
