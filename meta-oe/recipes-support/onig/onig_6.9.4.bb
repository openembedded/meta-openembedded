SUMMARY = "Regular expressions library"
DESCRIPTION = "Oniguruma is a modern and flexible regular expressions library. \
It encompasses features from different regular expression \
implementations that traditionally exist in different languages. \
Character encoding can be specified per regular expression object."
HOMEPAGE = "https://github.com/kkos/oniguruma"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ee043784bdce7503e619b2d1a85798b"

SRC_URI = "\
    https://github.com/kkos/oniguruma/releases/download/v${PV}/${BP}.tar.gz \
    file://0001-build-don-t-link-against-host-system-libraries.patch \
"

SRC_URI[md5sum] = "a12d2fe997b789bd87cf63799c091879"
SRC_URI[sha256sum] = "4669d22ff7e0992a7e93e116161cac9c0949cd8960d1c562982026726f0e6d53"

BINCONFIG = "${bindir}/onig-config"

inherit autotools binconfig-disabled

BBCLASSEXTEND = "native"
