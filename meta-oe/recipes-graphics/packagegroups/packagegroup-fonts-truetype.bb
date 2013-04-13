DESCRIPTION = "Install one of these tasks to get support for truetype fonts."
SECTION = "fonts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PV = "1.0"
PR = "r2"

inherit packagegroup allarch

PACKAGES += "\
    ${PN}-core \
    ${PN}-chinese \
    ${PN}-japanese \
"
RPROVIDES_${PN} += "task-fonts-truetype"
RPROVIDES_${PN}-core += "task-fonts-truetype-core"
RPROVIDES_${PN}-chinese += "task-fonts-truetype-chinese"
RPROVIDES_${PN}-japanese += "task-fonts-truetype-japanese"
RREPLACES_${PN} += "task-fonts-truetype"
RREPLACES_${PN}-core += "task-fonts-truetype-core"
RREPLACES_${PN}-chinese += "task-fonts-truetype-chinese"
RREPLACES_${PN}-japanese += "task-fonts-truetype-japanese"
RCONFLICTS_${PN} += "task-fonts-truetype"
RCONFLICTS_${PN}-core += "task-fonts-truetype-core"
RCONFLICTS_${PN}-chinese += "task-fonts-truetype-chinese"
RCONFLICTS_${PN}-japanese += "task-fonts-truetype-japanese"

RRECOMMENDS_${PN} = "\
    ${PN}-core \
    ${PN}-chinese \
    ${PN}-japanese \
"

RDEPENDS_${PN}-core = "\
    fontconfig-utils \
    \
    ttf-dejavu-common \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
"
#  ttf-dejavu-serif

RDEPENDS_${PN}-chinese = "\
    ${PN}-core \
    ttf-arphic-uming \
"

RDEPENDS_${PN}-japanese = "\
    ${PN}-core \
    ttf-sazanami-gothic \
    ttf-sazanami-mincho \
"
