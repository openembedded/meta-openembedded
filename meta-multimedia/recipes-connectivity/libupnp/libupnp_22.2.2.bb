SUMMARY = "Portable SDK for UPnP* Devices"
DESCRIPTION = "The Portable SDK for UPnP Devices is an SDK for development of \
UPnP device and control point applications. It consists of the core UPnP \
protocols along with a UPnP-specific eXtensible Markup Language (XML) parser \
supporting the Document Object Model (DOM) Level 2 API and an optional, \
integrated mini web server for serving UPnP related documents."
HOMEPAGE = "http://pupnp.sourceforge.net/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"

SRCREV = "fac0494691434c35243131f66a0b27ea0c29f700"
SRC_URI = "git://github.com/pupnp/pupnp.git;protocol=https;branch=main;tag=release-${PV} \
          "

inherit cmake pkgconfig

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DUPNP_BUILD_STATIC=OFF \
    -DUPNP_BUILD_SAMPLES=OFF \
    -DUPNP_ENABLE_TESTING=OFF \
    -DUPNP_ENABLE_TESTING_INTEGRATION=OFF \
    -DUPNP_MINISERVER_REUSEADDR=ON \
    -DDOWNLOAD_AND_BUILD_DEPS=OFF \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[openssl] = "-DUPNP_ENABLE_OPEN_SSL=ON,-DUPNP_ENABLE_OPEN_SSL=OFF,openssl"
