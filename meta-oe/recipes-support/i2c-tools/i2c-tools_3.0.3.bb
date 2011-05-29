DESCRIPTION = "Set of i2c tools for linux"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://dl.lm-sensors.org/i2c-tools/releases/i2c-tools-${PV}.tar.bz2 \
           file://Module.mk \
          "
SRC_URI[md5sum] = "511376eed04455cdb277ef19c5f73bb4"
SRC_URI[sha256sum] = "23b28e474741834e3f1b35b0686528769a13adc92d2ff5603cbda1d6bd5e5629"

inherit autotools

do_compile_prepend() {
        cp ${WORKDIR}/Module.mk ${S}/eepromer/
        sed -i 's#/usr/local#/usr#' Makefile
        echo "include eepromer/Module.mk" >> Makefile
}

do_install_append() {
        install -d ${D}${includedir}/linux
        install -m 0644 include/linux/i2c-dev.h ${D}${includedir}/linux/i2c-dev-user.h
	rm -f ${D}${includedir}/linux/i2c-dev.h
}
