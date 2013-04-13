DESCRIPTION = "Set of i2c tools for linux"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

RDEPENDS_${PN} += "perl"

SRC_URI = "http://pkgs.fedoraproject.org/repo/pkgs/i2c-tools/i2c-tools-3.1.0.tar.bz2/f15019e559e378c6e9d5d6299a00df21/i2c-tools-${PV}.tar.bz2 \
           file://Module.mk \
"
SRC_URI[md5sum] = "f15019e559e378c6e9d5d6299a00df21"
SRC_URI[sha256sum] = "960023f61de292c6dd757fcedec4bffa7dd036e8594e24b26a706094ca4c142a"

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
