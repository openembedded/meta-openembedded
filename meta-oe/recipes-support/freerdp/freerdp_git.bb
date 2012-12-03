# Copyright (C) 2010-2012, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.2"

EXTRA_OECMAKE += "-DWITH_MANPAGES=OFF"

SRCREV = "f311acaffb9190567e2b478a98d7cbfaf2709f6b"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git"

S = "${WORKDIR}/git"
