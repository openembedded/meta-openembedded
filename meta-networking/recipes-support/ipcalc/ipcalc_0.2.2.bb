SUMMARY = "Tool to assist in network address calculations for IPv4 and IPv6."
HOMEPAGE = "https://github.com/nmav/ipcalc"

SECTION = "net"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "\
    https://github.com/nmav/ipcalc/archive/${PV}.tar.gz;downloadfilename=ipcalc-${PV}.tar.gz \
    file://0001-Makefile-pass-extra-linker-flags.patch \
"

SRC_URI[md5sum] = "343db932a3ad407328920b7430965faf"
SRC_URI[sha256sum] = "bf1b95eca219e564c85fa4233fe65342963cf3e8a303a7e10b4dd7269c864794"

export USE_GEOIP = "no"

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/ipcalc ${D}/${bindir}
}
