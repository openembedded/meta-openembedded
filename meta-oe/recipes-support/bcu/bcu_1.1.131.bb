SUMMARY = "NXP Board Control Utilities"
DESCRIPTION = "The NXP Board Control Utilities are able to control various \
               features of NXP i.MX evaluation boards (EVK) from a host \
               computer. \
               Features like resetting the board, selecting the boot mode, \
               monitoring power consumption, programming the EEPROM and others"
HOMEPAGE = "https://github.com/nxp-imx/bcu"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=884d48c2aa7b82e1ad4a33909fab24b6"

SRC_URI = "git://github.com/nxp-imx/bcu;protocol=https;branch=master;tag=${BPN}_${PV} \
           file://0001-Fix-error-on-ubuntu-25-builds.patch \
           file://0001-create_version_h.sh-Add-a-shebang.patch \
           "
SRCREV = "a6625496b140e9e7a738c59e75bb163d3edd6f09"

# bcu_1.2.1 is a stray 2023 tag outside master; releases are 1.1.x
PE = "1"
UPSTREAM_CHECK_GITTAGREGEX = "^bcu_(?P<pver>1\.1\.\d+)$"

DEPENDS = "libyaml libusb1 libftdi"

inherit cmake pkgconfig

BBCLASSEXTEND = "native nativesdk"
