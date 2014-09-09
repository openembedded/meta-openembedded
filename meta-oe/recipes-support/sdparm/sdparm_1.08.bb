SUMMARY = "fetch and change SCSI mode pages"
DESCRIPTION = "The sdparm utility accesses and optionally modifies \
SCSI devices' mode page and inquiry data."
HOMEPAGE = "http://sg.danny.cz/sg/sdparm.html"
SECTION = "console/utils"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecab6c36b7ba82c675581dd0afde36f7 \
                    file://lib/BSD_LICENSE;md5=1d52f4a66f1e0ed96776bf354ab7a2ed"
DEPENDS="sg3-utils"
SRC_URI = "http://sg.danny.cz/sg/p/${BPN}-${PV}.tgz \
           file://make-sysroot-work.patch \
           "

PACKAGES =+ "${PN}-scripts"
RDEPENDS_${PN}-scripts += "bash ${PN}"

SRC_URI[md5sum] = "be5786f37499018ef44f409597c92d42"
SRC_URI[sha256sum] = "376b78a414b1a9c47f3f13dbeb963e7a3ec7be126f83927d6856b5f7ac425e57"

inherit autotools

# Put the bash scripts to ${PN}-scripts
FILES_${PN}-scripts = "${bindir}/sas_disk_blink ${bindir}/scsi_ch_swp"
