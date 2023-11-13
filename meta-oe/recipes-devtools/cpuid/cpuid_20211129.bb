SUMMARY = "Linux tool to dump x86 CPUID information about the CPU(s)"
DESCRIPTION = "cpuid dumps detailed information about the CPU(s) gathered \
from the CPUID instruction, and also determines the exact model of CPU(s). \
It supports Intel, AMD, and VIA CPUs, as well as older Transmeta, Cyrix, \
UMC, NexGen, Rise, and SiS CPUs"
HOMEPAGE="http://www.etallen.com/cpuid.html"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "http://www.etallen.com/${BPN}/${BP}.src.tar.gz \
           file://0001-Makefile-update-the-hardcode-path-to-bindir-mandir.patch \
           "
SRC_URI[sha256sum] = "230772bb88c44732e68a42d2eff43bcff46d893bf4ea6e04151d4cb6e8c88e2f"

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"

inherit perlnative

do_install () {
    oe_runmake DESTDIR=${D} bindir=${bindir} mandir=${mandir} install
}

RDEPENDS:${PN} = "perl"

INSANE_SKIP:${PN} += "already-stripped"
