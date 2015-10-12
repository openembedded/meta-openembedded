# Copyright (C) 2010-2012, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "1.2.5+gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"

EXTRA_OECMAKE += "-DBUILD_STATIC_LIBS=OFF \
                  -DCMAKE_POSITION_INDEPENDANT_CODE=ON \
                  -DWITH_MANPAGES=OFF \
                 "

SRCREV = "62da9d28c674814c81c245c1c7882eb0da7be76b"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git \
           file://winpr-makecert-Build-with-install-RPATH.patch \
          "

S = "${WORKDIR}/git"
