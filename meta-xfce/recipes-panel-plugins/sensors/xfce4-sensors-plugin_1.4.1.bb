SUMMARY = "Sensors plugin for the Xfce Panel"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-sensors-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b94789bed9aec03b9656a9cc5398c706"

inherit xfce-panel-plugin

SRC_URI[sha256sum] = "37d0dc569e735e482a18545c24eb1ce0229d6910e3a4d4c1dee0680999234be2"
SRC_URI += "file://0001-Do-not-check-for-sys-class-power_supply-we-are-cross.patch"

EXTRA_OECONF = " \
    --disable-procacpi \
    --disable-xnvctrl \
"

do_configure:prepend() {
    sed -i 's:LIBSENSORS_CFLAGS=.*:LIBSENSORS_CFLAGS=-I${STAGING_INCDIR}:g' ${S}/configure.ac
}

PACKAGECONFIG[libsensors] = "--enable-libsensors,--disable-libsensors, lmsensors"
PACKAGECONFIG[hddtemp]    = "--enable-hddtemp,--disable-hddtemp, hddtemp"
PACKAGECONFIG[netcat]     = "--enable-netcat,--disable-netcat, netcat"
PACKAGECONFIG[libnotify]  = "--enable-notification,--disable-notification, libnotify"

FILES_SOLIBSDEV = "${libdir}/xfce4/modules/lib*${SOLIBSDEV}"
