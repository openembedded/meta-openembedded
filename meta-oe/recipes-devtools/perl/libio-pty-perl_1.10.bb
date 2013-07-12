SUMMARY = "Perl module for pseudo tty IO"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://META.yml;beginline=7;endline=8;md5=f86a66cc3d9522a227298d6898b181a2"

SRC_URI = "http://www.cpan.org/modules/by-module/IO/IO-Tty-${PV}.tar.gz"

SRC_URI[md5sum] = "46baec86a145e57f0ec661fa412b097c"
SRC_URI[sha256sum] = "31a6e21ad187ec5ea93c2b898d6b3519647c5fce9f394b6d3c05a1f7c3f6f8b1"

S = "${WORKDIR}/IO-Tty-${PV}"

inherit cpan

