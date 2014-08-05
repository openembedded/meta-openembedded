SUMMARY = "netcf"
DESCRIPTION = "netcf is a cross-platform network configuration library."
HOMEPAGE = "https://fedorahosted.org/netcf/"
SECTION = "console/tools"
LICENSE = "LGPLv2.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbb461211a33b134d42ed5ee802b37ff"

SRCREV = "b8a19dc860b38c97c42115d8a559b78853452a4b"
PV = "0.2.3+git${SRCPV}"

SRC_URI = "git://git.fedorahosted.org/netcf.git;protocol=git \
"

DEPENDS += "augeas libnl libxslt libxml2 gnulib"

S = "${WORKDIR}/git"

inherit gettext autotools-brokensep pkgconfig

EXTRA_OECONF_append_class-target = " --with-driver=redhat"
do_configure_prepend() {
	cd ${S}
	rm -f .gitmodules
	./bootstrap --gnulib-srcdir=${STAGING_DATADIR}/gnulib
}

do_install_append() {
     mv ${D}${sysconfdir}/rc.d/init.d/ ${D}${sysconfdir}
     rm -rf ${D}${sysconfdir}/rc.d/
}
