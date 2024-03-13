SUMMARY = "LVGL Demo Application for Framebuffer"
HOMEPAGE = "https://github.com/lvgl/lv_port_linux_frame_buffer"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=802d3d83ae80ef5f343050bf96cce3a4 \
                    file://lvgl/LICENCE.txt;md5=bf1198c89ae87f043108cea62460b03a"

SRC_URI = "\
	git://github.com/lvgl/lv_port_linux_frame_buffer.git;protocol=https;branch=master;name=demo \
	git://github.com/lvgl/lvgl;protocol=https;branch=master;name=lvgl;subdir=git/lvgl \
	"
SRCREV_demo = "dccc6a1ca48372aa993dbea7a8e17dec6f42df6a"
SRCREV_lvgl = "e29d35b43c509b6d7189f5dac87139441669ae66"
SRCREV_FORMAT = "demo_lvgl"
PV .= "+git${SRCPV}"

EXTRA_OEMAKE = "DESTDIR=${D}"

PACKAGECONFIG ??= "drm"
LVGL_CONFIG_DRM_CARD ?= "/dev/dri/card0"
require lv-drivers.inc

inherit cmake

S = "${WORKDIR}/git"

TARGET_CFLAGS += "-I${STAGING_INCDIR}/libdrm"

do_configure:prepend() {
	if [ "${LVGL_CONFIG_USE_DRM}" -eq 1 ] ; then
		# Add libdrm build dependency
		sed -i '/^target_link_libraries/ s@pthread@& drm@' "${S}/CMakeLists.txt"
		# Switch from fbdev to drm usage
		sed -i "s@lv_linux_fbdev_set_file.*@lv_linux_drm_set_file(disp, \"${LVGL_CONFIG_DRM_CARD}\", -1);@g" "${S}/main.c"
		sed -i 's@fbdev@drm@g' "${S}/main.c"
	fi

	if [ "${LVGL_CONFIG_USE_SDL}" -eq 1 ] ; then
		# Add libsdl build dependency
		sed -i '/^target_link_libraries/ s@pthread@& SDL2 SDL2_image@' "${S}/CMakeLists.txt"
		# Switch from fbdev to sdl usage
		sed -i 's@lv_linux_fbdev_create()@lv_sdl_window_create(atoi(getenv("LV_VIDEO_WIDTH") ? : "800"), atoi(getenv("LV_VIDEO_HEIGHT") ? : "480"))@g' "${S}/main.c"
		sed -i '/lv_linux_fbdev_set_file/ d' "${S}/main.c"
	fi
}

do_install:append() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/bin/main ${D}${bindir}/lvgl
}
