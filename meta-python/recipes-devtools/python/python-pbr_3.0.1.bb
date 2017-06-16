DESCRIPTION = "Python Build Reasonableness: PBR is a library that injects some useful and sensible default behaviors into your setuptools run"
HOMEPAGE = "https://pypi.python.org/pypi/pbr"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

SRC_URI[md5sum] = "c537dbb9756e319be25772b1fe1c05fa"
SRC_URI[sha256sum] = "d7e8917458094002b9a2e0030ba60ba4c834c456071f2d0c1ccb5265992ada91"

inherit pypi setuptools

RDEPENDS_${PN}_class-target += " \
        python-pip \
        "

BBCLASSEXTEND = "native"
