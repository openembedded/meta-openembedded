DESCRIPTION = "BSD replacement for libreadline"
HOMEPAGE = "http://www.thrysoee.dk/editline/"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=1e4228d0c5a9093b01aeaaeae6641533"

DEPENDS = "ncurses"

inherit autotools

SRC_URI = "http://www.thrysoee.dk/editline/${PN}-${PV}-3.0.tar.gz"

S = "${WORKDIR}/${PN}-${PV}-3.0"

SRC_URI[md5sum] = "0ea42e2c794da8ed32f6307b427f6590"
SRC_URI[sha256sum] = "0b5ec9aa41faff761cda7819add93e9d8cb9c0bad85e65a686475e8375ac8a71"
