SUMMARY = "Portable SDK for UPnP* Devices"
DESCRIPTION = "The Portable SDK for UPnP Devices is an SDK for development of \
UPnP device and control point applications. It consists of the core UPnP \
protocols along with a UPnP-specific eXtensible Markup Language (XML) parser \
supporting the Document Object Model (DOM) Level 2 API and an optional, \
integrated mini web server for serving UPnP related documents."
HOMEPAGE = "http://pupnp.sourceforge.net/"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"

PV = "1.8.4+git${SRCPV}"
# release-1.8.4
SRCREV = "d5a01fc9895daae98a0c5a8c7d3afce46add529d"
SRC_URI = "git://github.com/mrjimenez/pupnp.git;protocol=https"

S="${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--enable-reuseaddr"

# Enable LFS support ( for samples )
CFLAGS += "-D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64"
CXXFLAGS += "-D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64"
