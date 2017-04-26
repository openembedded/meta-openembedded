require wireguard.inc

inherit module-base kernel-module-split

DEPENDS = "virtual/kernel libmnl"

# This module requires Linux 3.10 higher and several networking related
# configuration options. For exact kernel requirements visit:
# https://www.wireguard.io/install/#kernel-requirements

EXTRA_OEMAKE_append = " \
    KERNELDIR=${STAGING_KERNEL_DIR} \
    CC='${CC}' \
    KERNEL_CC='${KERNEL_CC}' \
    "
KERNEL_MODULES_META_PACKAGE = "${PN}"

MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"

RRECOMMENDS_${PN} = "kernel-module-xt-hashlimit"
