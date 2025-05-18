# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

src_org = "lvgl"
SRC_URI = "gitsm://github.com/${src_org}/lv_lib_png;destsuffix=${S};protocol=https;nobranch=1"
SRCREV = "bf1531afe07c9f861107559e29ab8a2d83e4715a"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6fc0df890c5270ef045981b516bb8f2"

HOMEPAGE = "https://docs.lvgl.io"
SUMMARY = "PNG decoder for LVGL"
DESCRIPTION = "Allow the use of PNG images in LVGL. This implementation uses lodepng"

DEPENDS += "lvgl"

inherit cmake

TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE=1"
TARGET_CFLAGS += "-I${RECIPE_SYSROOT}/${includedir}/lvgl"

FILES:${PN}-dev = "\
    ${includedir}/lvgl/lv_lib_png/ \
    "

FILES:${PN}-staticdev = "${libdir}/"
