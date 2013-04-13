DESCRIPTION = "Set of i2c tools for linux - Python module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://smbusmodule.c;startline=1;endline=17;md5=fa24df321a520ff8e10f203425ab9fa8"

SRC_URI = "http://dl.lm-sensors.org/i2c-tools/releases/i2c-tools-${PV}.tar.bz2 \
"
SRC_URI[md5sum] = "511376eed04455cdb277ef19c5f73bb4"
SRC_URI[sha256sum] = "23b28e474741834e3f1b35b0686528769a13adc92d2ff5603cbda1d6bd5e5629"

DEPENDS = "i2c-tools"

inherit distutils

S = "${WORKDIR}/i2c-tools-${PV}/py-smbus/"

do_configure_prepend() {
    # Adjust for OE header rename
    sed -i s:linux/i2c-dev.h:linux/i2c-dev-user.h: Module.mk
    sed -i s:linux/i2c-dev.h:linux/i2c-dev-user.h: smbusmodule.c
}
