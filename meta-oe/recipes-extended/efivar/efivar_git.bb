SUMMARY = "Tools to manipulate UEFI variables"
DESCRIPTION = "efivar provides a simple command line interface to the UEFI variable facility"
HOMEPAGE = "https://github.com/vathpela/efivar"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

DEPENDS = "popt"

SRCREV = "c9e1f24a81b5374408bca5616402276e47171cf7"
PV = "0.7+git${SRCPV}"
SRC_URI = "git://github.com/vathpela/efivar.git"

S = "${WORKDIR}/git"

do_install() {
    oe_runmake install DESTDIR=${D}
}
