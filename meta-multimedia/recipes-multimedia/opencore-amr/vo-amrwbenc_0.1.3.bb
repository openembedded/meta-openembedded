DESCRIPTION = "VisualOn Adaptive Multi Rate Wideband (AMR-WB) encoder"
HOMEPAGE = "http://sourceforge.net/projects/opencore-amr/"
SECTION = "libs"

LICENSE = "Apache-2.0"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=dd2c2486aca02190153cf399e508c7e7"

SRC_URI = "${SOURCEFORGE_MIRROR}/opencore-amr/${BP}.tar.gz"
SRC_URI[md5sum] = "f63bb92bde0b1583cb3cb344c12922e0"
SRC_URI[sha256sum] = "5652b391e0f0e296417b841b02987d3fd33e6c0af342c69542cbb016a71d9d4e"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/opencore-amr/files/vo-amrwbenc/"
UPSTREAM_CHECK_REGEX = "vo-amrwbenc-(?P<pver>\d+(\.\d+)+)\.tar\.gz"

inherit autotools
