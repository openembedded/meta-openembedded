DESCRIPTION = "Replacement syslog API"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=b8ba8e77bcda9a53fac0fe39fe957767"
PR = "r0"

SRC_URI = "http://www.balabit.com/downloads/files/syslog-ng/sources/3.2.2/source/${PN}_${PV}.tar.gz"

inherit autotools pkgconfig

SRC_URI[md5sum] = "f9c32814f78ea2629850f0440de5ff34"
SRC_URI[sha256sum] = "f78c218faa6b6b378f25c640a5d1188b1ecbd57d3a527432263fd44b17bbd1c9"
