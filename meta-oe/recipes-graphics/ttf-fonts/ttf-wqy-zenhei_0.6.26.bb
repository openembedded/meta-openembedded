require ttf.inc

DESCRIPTION = "WenQuanYi Zen Hei - A Hei-Ti Style Chinese font"
AUTHOR = "Qianqian Fang and The WenQuanYi Project Contributors"
HOMEPAGE = "http://wqy.sourceforge.net/en/"
LICENSE = "GPLv2"
PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/wqy/wqy-zenhei-${PV}-0.tar.gz"
S = "${WORKDIR}/wqy-zenhei"

do_install_append () { 
	install -d ${D}${sysconfdir}/fonts/conf.d/
	install -m 0644 ${S}/44-wqy-zenhei.conf ${D}${sysconfdir}/fonts/conf.d/
	install -m 0644 ${S}/66-wqy-zenhei-sharp.conf ${D}${sysconfdir}/fonts/conf.d/
}

PACKAGES = "${PN}"

FILES_${PN} = "${datadir}/fonts ${sysconfdir}"

SRC_URI[md5sum] = "bf2c1cb512606d995873bada27c777da"
SRC_URI[sha256sum] = "47355b6ec84bb309614b6d657ddfda993b96ed0be569264f82e523b254f945b2"
