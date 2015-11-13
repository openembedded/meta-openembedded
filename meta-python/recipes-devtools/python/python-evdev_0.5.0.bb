SUMMARY = "Python evdev lib"
HOMEPAGE = "https://github.com/gvalkov/python-evdev"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b500cb3c70f576a0a75125beda9491a"

SRC_URI = "https://github.com/gvalkov/python-evdev/archive/v${PV}.zip"
SRC_URI[md5sum] = "30c11ee7bd4a943f8ae586d839b860f2"
SRC_URI[sha256sum] = "ce6a757e28b56a12a9ad4e86420db4b11918b75ba1cd70f7444e904a7284b6fe"

inherit setuptools
