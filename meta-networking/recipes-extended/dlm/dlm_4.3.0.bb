DESCRIPTION = "dlm control daemon and tool"

SECTION = "utils"
HOMEPAGE = "https://gitlab.com/linux-gfs2/dlm"

REQUIRED_DISTRO_FEATURES = "systemd"

SRC_URI = "git://gitlab.com/linux-gfs2/dlm.git;protocol=https;branch=main;tag=dlm-${PV} \
           file://0001-Include-sys-sysmacros.h-for-major-minor-macros-in-gl.patch \
           file://0001-make-Replace-cp-a-with-mode-preserving-options.patch \
           file://0002-dlm_controld-Define-__always_inline-when-libc-does-n.patch \
           "

SRCREV = "c5929265f4c3e3f8adb0126c456424b70c9a546e"

UPSTREAM_CHECK_GITTAGREGEX = "^dlm-(?P<pver>\d+(\.\d+)+)$"

LICENSE = "GPL-2.0-only AND GPL-2.0-or-later AND LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README.license;md5=531f5086ad0f36f6e22cb6085e1c41d5"

DEPENDS += "corosync"

inherit pkgconfig systemd features_check

PACKAGECONFIG ??= ""

PACKAGECONFIG[pacemaker] = ",,pacemaker"

SYSTEMD_SERVICE:${PN} = "dlm.service"
SYSTEMD_AUTO_ENABLE = "enable"

export EXTRA_OEMAKE = ""

CFPROTECTION ?= "-fcf-protection=full"
CFPROTECTION:riscv32 = ""
CFPROTECTION:riscv64 = ""
CFPROTECTION:arm = ""
CFPROTECTION:aarch64 = ""

CFLAGS += "${CFPROTECTION}"

PARALLEL_MAKE = ""

DONTBUILD = "${@bb.utils.contains('PACKAGECONFIG', 'pacemaker', '', 'fence', d)}"

do_compile() {
    sed -i "s/libsystemd-daemon/libsystemd/g" ${S}/dlm_controld/Makefile
    sed -i -e "s/ ${DONTBUILD}//g" ${S}/Makefile
    oe_runmake 'CC=${CC}'
}

do_install() {
    oe_runmake install DESTDIR=${D} LIBDIR=${libdir}
    install -Dm 0644 ${S}/init/dlm.sysconfig ${D}${sysconfdir}/sysconfig/dlm
    install -Dm 0644 ${S}/init/dlm.init ${D}${sysconfdir}/init.d/dlm

    # install systemd unit files
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -Dm 0644 ${S}/init/dlm.service ${D}${systemd_unitdir}/system/dlm.service
    fi
}
