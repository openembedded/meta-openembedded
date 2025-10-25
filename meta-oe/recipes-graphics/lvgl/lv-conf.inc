# SPDX-FileCopyrightText: Axis Communication AB <https://www.axis.com/>
# SPDX-License-Identifier: MIT

DEPENDS += "python3-kconfiglib-native python3-pcpp-native"

SRC_URI += " \
    file://defconfig \
    ${@oe.utils.vartrue('DEBUG_BUILD', 'file://debug.cfg', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'drm', 'file://drm.cfg', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'fbdev', 'file://fbdev.cfg', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'gridnav', 'file://gridnav.cfg', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'sdl', 'file://sdl.cfg', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'thorvg', 'file://thorvg.cfg', '', d)} \
"

PACKAGECONFIG ??= "drm"

PACKAGECONFIG[drm] = ",,libdrm libevdev"
PACKAGECONFIG[fbdev] = ",,libevdev"
PACKAGECONFIG[gridnav] = ",,"
PACKAGECONFIG[thorvg] = ",,"
PACKAGECONFIG[sdl] = ",,virtual/libsdl2 libsdl2-image"

inherit python3native

EXTRA_OECMAKE += "-DLV_BUILD_USE_KCONFIG=ON"

# Add libdrm include if drm is selected in PACKAGECONFIG
LVGL_FLAGS += " \
    ${@bb.utils.contains('PACKAGECONFIG', 'drm', '-I${STAGING_INCDIR}/libdrm', '', d)} \
"
CFLAGS += "${LVGL_FLAGS}"
CXXFLAGS += "${LVGL_FLAGS}"

KCONFIG_CONFIG_ROOTDIR ?= "${S}"
KCONFIG_FILE ?= "${KCONFIG_CONFIG_ROOTDIR}/Kconfig"
export KCONFIG_CONFIG ?= "${KCONFIG_CONFIG_ROOTDIR}/.config"

do_configure() {
    cat ${UNPACKDIR}/defconfig ${@" ".join(find_cfgs(d))} > ${B}/defconfig
    defconfig --kconfig ${KCONFIG_FILE} ${B}/defconfig

    cmake_do_configure
}
