SUMMARY = "Takao Fonts are a community developed derivatives of IPA Fonts."
DESCRIPTION = "Takao Fonts are a community developed derivatives of IPA Fonts."

HOMEPAGE = "https://launchpad.net/takao-fonts"
SECTION = "User Interface/X"

LICENSE = "IPA"
LIC_FILES_CHKSUM = "file://IPA_Font_License_Agreement_v1.0.txt;md5=6cd3351ba979cf9db1fad644e8221276"

SRC_URI = "http://launchpad.net/takao-fonts/003.02/${PV}/+download/takao-fonts-ttf-${PV}.zip"
SRC_URI[md5sum] = "ca480e5edb9f26d871bf6df6cb910306"
SRC_URI[sha256sum] = "2f526a16c7931958f560697d494d8304949b3ce0aef246fb0c727fbbcc39089e"

S = "${WORKDIR}/${BPN}-ttf-${PV}"

do_install() {
    install -m 0755 -d ${D}/${datadir}/fonts
    install -m 0644 -p ${S}/*.ttf ${D}/${datadir}/fonts/
}

FILES_${PN} += "${datadir}/fonts/*.ttf"
