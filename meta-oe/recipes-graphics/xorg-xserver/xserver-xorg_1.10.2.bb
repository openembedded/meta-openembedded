# Assign it here, since the hal->udev transition happens post 1.7 in angstrom
DISTRO_XORG_CONFIG_MANAGER_angstrom = "udev"

require xorg-xserver-common.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=74df27b6254cc88d2799b5f4f5949c00"

DESCRIPTION = "the X.Org X server"
DEPENDS += "mesa-dri pixman libpciaccess openssl dri2proto glproto font-util-native"
PE = "2"
PR = "${INC_PR}.0"

SRC_URI += " \
            file://50b9d3142ff90af2f7fa35b7b1bf9e5a07723dbd.patch \
            file://randr-support.patch \
            file://hack-fbdev-ignore-return-mode.patch \
            file://hack-assume-pixman-supports-overlapped-blt.patch \
            file://replace-pkgconfig-sdkdir-poking.patch \
           "
SRC_URI[md5sum] = "c9ba50bd44ea70da51f13100336a5484"
SRC_URI[sha256sum] = "65264f6640568b9db8d738aec1ddd036c3ae21b7ba05b98d006759d11a72792c"

do_install_prepend() {
        mkdir -p ${D}/${libdir}/X11/fonts
}

# The NVidia driver requires Xinerama support in the X server. Ion uses it.
PACKAGE_ARCH_ion = "${MACHINE_ARCH}"
XINERAMA = "${@['--disable-xinerama','--enable-xinerama'][bb.data.getVar('MACHINE',d) in ['ion']]}"

EXTRA_OECONF += " ${CONFIG_MANAGER_OPTION} ${XINERAMA} --disable-xsdl --disable-xfake --disable-xfbdev --disable-dmx"
EXTRA_OECONF += " --enable-dri2 --disable-unit-tests --disable-docs --disable-devel-docs"

export LDFLAGS += " -ldl "
