SECTION = "utils"
DESCRIPTION = "LVM2 is a set of utilities to manage logical volumes in Linux."
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"
INC_PR = "r0"


S = "${WORKDIR}/LVM2.${PV}"
SRC_URI = "ftp://sources.redhat.com/pub/lvm2/old/LVM2.${PV}.tgz \
           file://lvm.conf"

# Unset user/group to unbreak install.
EXTRA_OECONF = "--with-user= \
                --with-group= \
                --disable-o_direct \
                --enable-realtime \
                --enable-applib \
                --enable-cmdlib \
                --enable-udev_sync \
                --enable-udev_rules \
                --enable-pkgconfig \
                --enable-dmeventd \
                --with-udev-prefix= \
              "

inherit autotools

do_install_append() {
    # Install machine specific configuration file
    install -m 0644 ${WORKDIR}/lvm.conf ${D}${sysconfdir}/lvm/lvm.conf
}

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN} += "${libdir}/device-mapper/*.so ${base_libdir}/udev"
FILES_${PN}-dbg += "${libdir}/device-mapper/.debug"

