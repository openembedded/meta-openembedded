DESCRIPTION = "Unicode Mingti (printed) TrueType Font"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/CJKUnifonts"
LICENSE = "${PN}"
SRC_DISTRIBUTE_LICENSES += "${PN}"
RPROVIDES_${PN} = "virtual-chinese-font"
PR = "r2"

SRC_URI = \
"http://archive.ubuntu.com/ubuntu/pool/main/t/ttf-arphic-uming/ttf-arphic-uming_0.2.${PV}.1.orig.tar.gz"
S = "${WORKDIR}"

require ttf.inc

FILES_${PN} = "${datadir}"


SRC_URI[md5sum] = "d219fcaf953f3eb1889399955a00379f"
SRC_URI[sha256sum] = "8038a6db9e832456d5da5559aff8d15130243be1091bf24f3243503a6f1bda98"
