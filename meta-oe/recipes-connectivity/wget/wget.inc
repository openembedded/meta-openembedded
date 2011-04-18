DESCRIPTION = "A console URL download utility featuring HTTP, FTP, and more."
SECTION = "console/network"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
INC_PR = "r10"

S = "${WORKDIR}/wget-${PV}"

inherit autotools gettext

EXTRA_OECONF = " \
  --enable-ipv6 
  --with-libssl-prefix=${STAGING_DIR}${HOST_SYS}"

# The unslung kernel does not support ipv6
EXTRA_OECONF_unslung = "--without-ssl"
# SlugOS kernels do not support ipv6. Can be loaded as a module.
EXTRA_OECONF_slugos = "--without-ssl"

do_install_append () {
  mv ${D}${bindir}/wget ${D}${bindir}/wget.${PN}
}

pkg_postinst_${PN} () {
  update-alternatives --install ${bindir}/wget wget wget.${PN} 100
}

pkg_prerm_${PN} () {
  update-alternatives --remove wget wget.${PN}
}
