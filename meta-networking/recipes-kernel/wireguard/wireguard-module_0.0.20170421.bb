require wireguard.inc

inherit module

# This module requires Linux 3.10 higher and several networking related
# configuration options. For exact kernel requirements visit:
# https://www.wireguard.io/install/#kernel-requirements

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"

RRECOMMENDS_${PN} = "kernel-module-xt-hashlimit"
