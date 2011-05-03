# Copyright (C) 2010, 2011, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.0"

SRCREV = "7bbde4fb3bc3a781364d0a626c49bc165cc507fd"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git;protocol=git"

S = "${WORKDIR}/git"
