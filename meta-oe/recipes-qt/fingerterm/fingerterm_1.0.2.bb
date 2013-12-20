DESCRIPTION = "A terminal emulator for Harmattan (Nokia N9/N950) with a custom \
virtual keyboard and usability-enhancing features such as URL grabber, pan \
gestures and customizable shortcut menu. Designed especially to be used with \
screen and irssi."
HOMEPAGE = "http://hqh.unlink.org/harmattan"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SECTION = "qt/app"


inherit qt4x11 pkgconfig

SRC_URI = " \
    http://hqh.unlink.org/harmattan/fingerterm-${PV}.tar.gz \
"

SRC_URI[md5sum] = "d53b0f463c025583cd52accf95715448"
SRC_URI[sha256sum] = "c21a07cc411d09739bd7fa248eba2bf96427b3f032382a044b6674dd82d95150"

S = "${WORKDIR}/${PN}"

FILES_${PN} = " \
    ${bindir}/${PN} \
    ${datadir}/applications/${PN}.desktop \
    ${datadir}/pixmaps/${PN}.png \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/${PN} ${D}${bindir}

    sed -i -e '/Exec.*/d' fingerterm.desktop
    sed -i -e '/Icon.*/d' fingerterm.desktop

    echo 'Exec=${bindir}/${PN}' >> fingerterm.desktop
    echo 'Icon=${PN}' >> fingerterm.desktop

    install -d ${D}${datadir}/applications
    install -m 0644 ${S}/${PN}.desktop ${D}${datadir}/applications

    install -d ${D}${datadir}/pixmaps
    install -m 0644 ${S}/${PN}.png ${D}${datadir}/pixmaps
}
