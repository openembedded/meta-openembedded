# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

HOMEPAGE = "https://lvgl.io/"
DESCRIPTION = "LVGL is an OSS graphics library to create embedded GUI"
SUMMARY = "Light and Versatile Graphics Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=bf1198c89ae87f043108cea62460b03a"

SRC_URI = "\
	git://github.com/lvgl/lvgl;protocol=https;branch=master \
	file://0001-fix-drm-Default-to-XRGB8888-framebuffer.patch \
	file://0002-fix-sdl-handle-both-LV_IMAGE_SRC_FILE-and-LV_IMAGE_S.patch \
	"
SRCREV = "e29d35b43c509b6d7189f5dac87139441669ae66"
PV .= "+git${SRCPV}"

inherit cmake

EXTRA_OECMAKE = "-DLIB_INSTALL_DIR=${baselib} -DBUILD_SHARED_LIBS=ON"
S = "${WORKDIR}/git"

ALLOW_EMPTY:${PN} = "1"

PACKAGECONFIG ??= "drm"
require lv-conf.inc

FILES:${PN}-dev += "\
    ${includedir}/${PN}/ \
    ${includedir}/${PN}/lvgl/ \
    "
