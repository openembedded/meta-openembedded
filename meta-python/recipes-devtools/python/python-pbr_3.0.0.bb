DESCRIPTION = "Python Build Reasonableness: PBR is a library that injects some useful and sensible default behaviors into your setuptools run"
HOMEPAGE = "https://pypi.python.org/pypi/pbr"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

SRC_URI[md5sum] = "b9ef99a98350a57bbe55b1142807f224"
SRC_URI[sha256sum] = "568f988af109114fbfa0525dcb6836b069838360d11732736ecc82e4c15d5c12"

inherit pypi setuptools

RDEPENDS_${PN}_class-target += " \
        python-pip \
        "

BBCLASSEXTEND = "native"
