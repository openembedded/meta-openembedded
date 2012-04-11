DESCRIPTION = "CPU burn app that loads the NEON coprocessor fully"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://burn.S;md5=823abc72c2cd448e87df9bc5355a4456"

PR = "r1"

# only for ARM systems infact it should check for MACHINE_FEATURE
# neon or something. For now we spare non arm arches from picking this up

COMPATIBLE_HOST = "arm.*"

SRC_URI = "http://hardwarebug.org/files/burn.S;name=mru \
           http://github.com/downloads/ssvb/ssvb.github.com/ssvb-cpuburn-a8.S;name=ssvb"
SRC_URI[mru.md5sum] = "823abc72c2cd448e87df9bc5355a4456"
SRC_URI[mru.sha256sum] = "01d9fc04f83740c513c25401dcc89c11b2a5a6013e70bfca42b7b02129f88cd2"
SRC_URI[ssvb.md5sum] = "f1af41dcd56e4f0f7e92b67109dc01ed"
SRC_URI[ssvb.sha256sum] = "95e2267a684be0d848dd6f935e769fa3ed810274ebacddc3fe8ddac4b34838c2"

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

