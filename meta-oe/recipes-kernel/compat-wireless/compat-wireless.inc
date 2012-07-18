DESCRIPTION = "Latest wireless drivers"
HOMEPAGE = "http://wireless.kernel.org/en/users/Download"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=d7810fab7487fb0aad327b76f1be7cd7"
RDEPENDS = "wireless-tools"
PR = "r0"

COMPAT_WIRELESS_VERSION = "3.5-rc5-1"

SRC_URI = " \
  http://www.orbit-lab.org/kernel/compat-wireless-3-stable/v3.5/compat-wireless-${COMPAT_WIRELESS_VERSION}.tar.bz2 \
  file://0001-ath5k-fix-compilation-without-CONFIG_PCI.patch \
"

PV = "${COMPAT_WIRELESS_VERSION}"

S = "${WORKDIR}/compat-wireless-${COMPAT_WIRELESS_VERSION}"

inherit module

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D}"

do_configure_append() {
	sed -i "s#@./scripts/update-initramfs## " Makefile
}

do_install() {
	oe_runmake DEPMOD=echo DESTDIR="${D}" INSTALL_MOD_PATH="${D}" LDFLAGS="" install-modules
}

SRC_URI[md5sum] = "5d26bb5fd830ba67e920e0e4b2cff284"
SRC_URI[sha256sum] = "f77ec3afd7c8fb1d9583cb5c82cdc683816c7fc43cebebc1f0f029d172fcce9a"
