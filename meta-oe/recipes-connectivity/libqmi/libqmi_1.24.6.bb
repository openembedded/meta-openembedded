SUMMARY = "libqmi is a library for talking to WWAN devices by QMI protocol"
DESCRIPTION = "libqmi is a glib-based library for talking to WWAN modems and devices which speak the Qualcomm MSM Interface (QMI) protocol"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/libqmi"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

DEPENDS = "glib-2.0 glib-2.0-native"

inherit autotools pkgconfig bash-completion

SRC_URI = "http://www.freedesktop.org/software/${BPN}/${BPN}-${PV}.tar.xz \
           "

SRC_URI[md5sum] = "5d50233394a33e43dee3e70e197323e5"
SRC_URI[sha256sum] = "1325257bc16de7b2b443fa689826c993474bdbd6e78c7a1e60e527269b44d8c9"

PACKAGECONFIG ??= "udev mbim"
PACKAGECONFIG[udev] = ",--without-udev,libgudev"
PACKAGECONFIG[mbim] = "--enable-mbim-qmux,--disable-mbim-qmux,libmbim"

EXTRA_OECONF_append_toolchain-clang = " --enable-more-warnings=no"
