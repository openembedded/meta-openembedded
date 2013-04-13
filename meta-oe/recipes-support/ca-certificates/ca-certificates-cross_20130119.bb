require ca-certificates-${PV}.inc

do_install() {
    install -d ${D}${bindir_crossscripts}
    install -m755 ${S}/sbin/update-ca-certificates ${D}${bindir_crossscripts}
}

SYSROOT_PREPROCESS_FUNCS += "ca_certificates_sysroot_preprocess"

ca_certificates_sysroot_preprocess() {
    sysroot_stage_dir ${D}${bindir_crossscripts} ${SYSROOT_DESTDIR}${bindir_crossscripts}
}
