DESCRIPTION = "Latest wireless drivers"
HOMEPAGE = "http://wireless.kernel.org/en/users/Download"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=d7810fab7487fb0aad327b76f1be7cd7"
RDEPENDS_${PN} = "wireless-tools"
INC_PR = "r1"

# depends on config options which are not enabled in qemu* MACHINEs by default
# config.mk:25: *** "ERROR: your kernel has CONFIG_CFG80211=y, you should have it CONFIG_CFG80211=m if you want to use this thing.".  Stop.
EXCLUDE_FROM_WORLD = "1"

COMPAT_WIRELESS_VERSION = "${PV}-1"
SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"

SRC_URI = " \
    http://www.orbit-lab.org/kernel/compat-wireless-3-stable/v${SHRT_VER}/compat-wireless-${COMPAT_WIRELESS_VERSION}.tar.bz2 \
    file://0001-ath5k-fix-compilation-without-CONFIG_PCI.patch \
"

S = "${WORKDIR}/compat-wireless-${COMPAT_WIRELESS_VERSION}"

inherit module

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D}"

do_configure_append() {
    sed -i "s#@./scripts/update-initramfs## " Makefile
}

do_install() {
    oe_runmake DEPMOD=echo DESTDIR="${D}" INSTALL_MOD_PATH="${D}" LDFLAGS="" install-modules
}
