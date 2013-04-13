DESCRIPTION = "CPU burn app that loads the NEON coprocessor fully"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://burn.S;md5=823abc72c2cd448e87df9bc5355a4456"

DL_DIR_append = "/${PN}-${PV}"

# Ensure to make this available for machine which has neon
COMPATIBLE_MACHINE = "(${@bb.utils.contains("TUNE_FEATURES", "neon", "${MACHINE}", "Invalid!", d)})"

SRC_URI = "http://hardwarebug.org/files/burn.S;name=mru \
           http://github.com/downloads/ssvb/ssvb.github.com/ssvb-cpuburn-a8.S;name=ssvb"
SRC_URI[mru.md5sum] = "823abc72c2cd448e87df9bc5355a4456"
SRC_URI[mru.sha256sum] = "01d9fc04f83740c513c25401dcc89c11b2a5a6013e70bfca42b7b02129f88cd2"
SRC_URI[ssvb.md5sum] = "0acc570d943c41c7f8602b9ff6fa111d"
SRC_URI[ssvb.sha256sum] = "bfddd3226a499ffdf71bb58c05ccdc6dac5bb2c2c3bdb10ac610ee0b60aac087"

S = "${WORKDIR}"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} burn.S -o burn
    ${CC} ${CFLAGS} ${LDFLAGS} ssvb-cpuburn-a8.S -o burn-neona8
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/burn ${D}${bindir}/burn-neon
    install -m 0755 ${S}/burn-neona8 ${D}${bindir}/
}

