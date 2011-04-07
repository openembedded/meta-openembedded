DESCRIPTION = "Basic task to get a device booting"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${POKYBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r53"

inherit task

# packages which content depend on MACHINE_FEATURES need to be MACHINE_ARCH
#
PACKAGE_ARCH = "${MACHINE_ARCH}"

#
# those ones can be set in machine config to supply packages needed to get machine booting
#
MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS ?= ""

# update-alternatives script installed in rootfs needs to be the same as
# u-a script used for building image which is defined with
# PREFERRED_PROVIDER_virtual/update-alternatives-native

DISTRO_UPDATE_ALTERNATIVES ?= "${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${PREFERRED_PROVIDER_virtual/update-alternatives}", d)}"

# Make sure we build the kernel
DEPENDS = "virtual/kernel"

#
# minimal set of packages - needed to boot
#
RDEPENDS_task-boot = "\
    base-files \
    base-passwd \
    busybox \
    ${@base_contains("MACHINE_FEATURES", "keyboard", "keymaps", "", d)} \
    modutils-initscripts \
    netbase \
    ${DISTRO_UPDATE_ALTERNATIVES} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    "

RRECOMMENDS_task-boot = "\
    kernel \
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    "
