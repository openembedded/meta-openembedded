DESCRIPTION = "Polish dictionary for Illume keyboard"
SECTION = "x11/data"
SRCREV = "1cc80e26a4558dfc2268b349d9a1f468e515bcfb"
PV = "1.0+gitr${SRCPV}"
PE = "1"
PR = "r0"
LICENSE = "unknown"

SRC_URI = "git://git.shr-project.org/repo/shr-themes.git;protocol=http;branch=master"

S = "${WORKDIR}/git/e-wm/${PN}"

FILES_${PN} = "${libdir}/enlightenment/modules/illume/dicts/Polish.dic"

do_install() {
    install -d ${D}${libdir}/enlightenment/modules/illume/dicts
    install -m 0644 ${S}/Polish.dic        ${D}${libdir}/enlightenment/modules/illume/dicts/Polish.dic
}
