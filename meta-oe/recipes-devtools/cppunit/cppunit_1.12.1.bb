# cppunit OE build file
# Copyright (C) 2005, Koninklijke Philips Electronics NV.  All Rights Reserved
# Released under the MIT license (see packages/COPYING)

DESCRIPTION = "Unit Testing Library for C++"
HOMEPAGE = "http://cppunit.sourceforge.net/"
LICENSE = "LGPL"
SECTION = "devel"
PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/cppunit/cppunit-${PV}.tar.gz"

inherit autotools binconfig

CXXFLAGS_powerpc += "-lstdc++"


SRC_URI[md5sum] = "bd30e9cf5523cdfc019b94f5e1d7fd19"
SRC_URI[sha256sum] = "ac28a04c8e6c9217d910b0ae7122832d28d9917fa668bcc9e0b8b09acb4ea44a"
