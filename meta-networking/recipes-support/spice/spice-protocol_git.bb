#
# Copyright (C) 2013 Wind River Systems, Inc.
#

SUMMARY = "Simple Protocol for Independent Computing Environments"
DESCRIPTION = "SPICE (the Simple Protocol for Independent Computing \
Environments) is a remote-display system built for virtual \
environments which allows users to view a computing 'desktop' \ 
environment - not only on its computer-server machine, but also from \
anywhere on the Internet and using a wide variety of machine \
architectures."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=b37311cb5604f3e5cc2fb0fd23527e95"

PV = "0.12.13+git${SRCPV}"

SRCREV_spice-protocol = "8dda82b49d8f848a25e3a1ef6df943276c59e462"
#SRCREV_spice-common = "70d4739ce2f90f904fa96e22e438e9b424a3dd42"

#SRCREV_FORMAT = "spice-protocol_spice-common"

SRC_URI = " \
    git://anongit.freedesktop.org/spice/spice-protocol;name=spice-protocol \
"
#   git://anongit.freedesktop.org/spice/spice-common;destsuffix=git/spice-common;name=spice-common 

S = "${WORKDIR}/git"

inherit autotools gettext pkgconfig

BBCLASSEXTEND = "native nativesdk"
