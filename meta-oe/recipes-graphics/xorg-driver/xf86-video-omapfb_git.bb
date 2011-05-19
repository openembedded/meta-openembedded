require recipes-graphics/xorg-driver/xf86-driver-common.inc

INC_PR ?= "r15"

DESCRIPTION = "X.Org X server -- OMAP display driver"
PE = "1"
PV = "0.1.1+${PR}+gitr${SRCREV}"
PR = "${INC_PR}.3"

LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://src/omapfb-driver.c;beginline=1;endline=30;md5=a44c2a37e04d1c2c5f0313afb493f833"

SRC_URI = "git://git.pingu.fi/xf86-video-omapfb;protocol=http \
           file://0001-blacklist-tv-out.patch \
           file://0002-Revert-Set-virtual-size-when-configuring-framebuffer.patch \
           file://0003-force-plain-mode.patch \
          "

SRCREV_pn-${PN} = "28c006c94e57ea71df11ec4fff79d7ffcfc4860f"
S = "${WORKDIR}/git"

EXTRA_OECONF_armv7a = " --enable-neon "

# Use overlay 2 on omap3 to enable other apps to use overlay 1 (e.g. dmai or omapfbplay)
do_compile_prepend_armv7a () {
        sed -i -e s:fb1:fb2:g ${S}/src/omapfb-xv.c
}

CFLAGS += " -I${STAGING_INCDIR}/xorg "
