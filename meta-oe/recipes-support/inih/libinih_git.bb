DESCRIPTION = "C and C++ INI Library"
HOMEPAGE = "http://code.google.com/p/inih/"
PRIORITY = "optional"
PV = "0.0+gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dae28cbf28207425e0d0b3eb835f4bde"

PR = "r1"

# The github repository provides a cmake and pkg-config integration
SRCREV = "af230b4c7ef2529413ffc37cee513ef8f35c9598"
SRC_URI = "git://github.com/OSSystems/inih.git;protocol=git"

S = "${WORKDIR}/git"

inherit gitpkgv cmake

# We don't have libinih since we only have static libraries
ALLOW_EMPTY_${PN} = "1"
