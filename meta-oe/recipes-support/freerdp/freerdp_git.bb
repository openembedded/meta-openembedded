# Copyright (C) 2010, 2011, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.2"

SRCREV = "d1eef40acc994d85cb78c0a98914edc097c9b44d"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git;protocol=git"

S = "${WORKDIR}/git"
