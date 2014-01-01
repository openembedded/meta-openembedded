# Copyright (C) 2010-2012, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "1.0.2+gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"

EXTRA_OECMAKE += "-DWITH_MANPAGES=OFF"

SRCREV = "f311acaffb9190567e2b478a98d7cbfaf2709f6b"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git;branch=stable-1.0"

S = "${WORKDIR}/git"
