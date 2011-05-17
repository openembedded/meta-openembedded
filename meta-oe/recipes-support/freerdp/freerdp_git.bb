# Copyright (C) 2010, 2011, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.1"

SRCREV = "f5aef3be8fac9a02b0c7bbb5424831a7b285e7bb"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git;protocol=git"

S = "${WORKDIR}/git"
