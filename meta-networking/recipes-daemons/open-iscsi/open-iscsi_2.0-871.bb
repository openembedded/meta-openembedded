DESCRIPTION = "Open-iSCSI project is a high performance, transport \
independent, multi-platform implementation of RFC3720."
HOMEPAGE = "http://www.open-iscsi.org/"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "http://www.open-iscsi.org/bits/${BP}.tar.gz"
SRC_URI[md5sum] = "0c403e8c9ad41607571ba0e6e8ff196e"
SRC_URI[sha256sum] = "bcea8746ae82f2ada7bc05d2aa59bcda1ca0d5197f05f2e16744aae59f0a7dcb"
SRC_URI += "file://iscsi_sysfs.c-add-include-files.patch \
    file://open-iscsi.service"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile () {
    oe_runmake user
}

do_install_append () {
    if ${@base_contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/open-iscsi.service ${D}${systemd_unitdir}/system/
    fi
}

do_install () {
    oe_runmake DESTDIR="${D}" install_user
}

inherit ${@base_contains('VIRTUAL-RUNTIME_init_manager','systemd','systemd','',d)}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "open-iscsi.service"
SYSTEMD_AUTO_ENABLE = "enable"
