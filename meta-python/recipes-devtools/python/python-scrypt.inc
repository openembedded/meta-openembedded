DESCRIPTION = "Bindings for the scrypt key derivation function library"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
HOMEPAGE="https://bitbucket.org/mhallin/py-scrypt/overview"

SRC_URI[md5sum] = "0704e59cc3afb3845c27bb3827baeea9"
SRC_URI[sha256sum] = "d4a5a4f53450b8ef629bbf1ee4be6105c69936e49b3d8bc621ac2287f0c86020"

inherit pypi

RDEPENDS_${PN}_class-target += "\
"
