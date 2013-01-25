DESCRIPTION = "A tool to run a command as a daemon"
HOMEPAGE = "http://software.clapper.org/daemonize/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=3cf9084faa88bc8554a9139d8d7dd35f"
PV = "1.7.3+git${SRCPV}"

inherit autotools

SRCREV = "93788682a4749d8b577ce6549d6d3dd6df166ba4"
SRC_URI = "git://github.com/bmc/daemonize.git;protocol=git"

S = "${WORKDIR}/git"
