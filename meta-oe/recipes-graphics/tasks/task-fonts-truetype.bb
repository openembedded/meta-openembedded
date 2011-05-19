DESCRIPTION = "Install one of these tasks to get support for truetype fonts."
SECTION = "fonts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PV = "1.0"
PR = "r1"

inherit task allarch

PACKAGES += "\
  ${PN}-core \
  ${PN}-chinese \
  ${PN}-japanese \
"

RRECOMMENDS_task-fonts-truetype = "\
  ${PN}-core \
  ${PN}-chinese \
  ${PN}-japanese \
"

RDEPENDS_task-fonts-truetype-core = "\
  fontconfig-utils \
  \
  ttf-dejavu-common \
  ttf-dejavu-sans \
  ttf-dejavu-sans-mono \
"
#  ttf-dejavu-serif \

RDEPENDS_task-fonts-truetype-chinese = "\
  ${PN}-core \
  ttf-arphic-uming \
"

RDEPENDS_task-fonts-truetype-japanese = "\
  ${PN}-core \
  ttf-sazanami-gothic \
  ttf-sazanami-mincho \
"
