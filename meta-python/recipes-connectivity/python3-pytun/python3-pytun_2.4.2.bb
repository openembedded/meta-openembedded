SUMMARY = "Python TUN/TAP tunnel module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=13f7629e8e4989b66b4a913ab05a91de"

SRCREV = "7bb880635689a751429ff8f219e39b0b712485bf"
PYPI_SRC_URI = "git://github.com/montag451/pytun;protocol=https;branch=master;tag=v${PV};destsuffix=${PYPI_PACKAGE_SDIST}-${PV}"

PYPI_PACKAGE = "python-pytun"
PYPI_PACKAGE_SDIST = "python-pytun"

inherit pypi setuptools3
