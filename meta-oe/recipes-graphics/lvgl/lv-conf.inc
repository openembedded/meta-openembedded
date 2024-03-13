PACKAGECONFIG[drm] = ",,libdrm"
PACKAGECONFIG[fbdev] = ",,"
PACKAGECONFIG[sdl] = ",,virtual/libsdl2 libsdl2-image"

LVGL_CONFIG_USE_DRM = "${@bb.utils.contains('PACKAGECONFIG', 'drm', '1', '0', d)}"

LVGL_CONFIG_USE_EVDEV = "${@bb.utils.contains_any('PACKAGECONFIG', 'drm fbdev', '1', '0', d)}"

LVGL_CONFIG_USE_FBDEV = "${@bb.utils.contains('PACKAGECONFIG', 'fbdev', '1', '0', d)}"

LVGL_CONFIG_USE_SDL = "${@bb.utils.contains('PACKAGECONFIG', 'sdl', '1', '0', d)}"

LVGL_CONFIG_LV_MEM_CUSTOM ?= "0"
LVGL_CONFIG_LV_COLOR_DEPTH ?= "32"

ALLOW_EMPTY:${PN} = "1"

EXTRA_OECMAKE += "-Dinstall:BOOL=ON -DLIB_INSTALL_DIR=${baselib}"

do_configure:append() {
    # If there is a configuration template, start from that
    [ -r "${S}/lv_conf_template.h" ] && cp -Lv "${S}/lv_conf_template.h" "${S}/lv_conf.h"

    sed -e "s|#if 0 .*Set it to \"1\" to enable the content.*|#if 1 // Enabled by ${PN}|g" \
        \
        -e "s|\(^#define LV_USE_LINUX_DRM \).*|#define LV_USE_LINUX_DRM ${LVGL_CONFIG_USE_DRM}|g" \
        \
        -e "s|\(^#define LV_USE_LINUX_FBDEV \).*|#define LV_USE_LINUX_FBDEV ${LVGL_CONFIG_USE_FBDEV}|g" \
        \
        -e "s|\(^#define LV_USE_SDL \).*|#define LV_USE_SDL ${LVGL_CONFIG_USE_SDL}|g" \
        -e "s|\(^#define LV_USE_DRAW_SDL \).*|#define LV_USE_DRAW_SDL ${LVGL_CONFIG_USE_SDL}|g" \
        -e "s|\(^    #define LV_SDL_BUF_COUNT \).*|    #define LV_SDL_BUF_COUNT 2|g" \
        \
        -e "s|\(^#define LV_COLOR_DEPTH \).*|#define LV_COLOR_DEPTH ${LVGL_CONFIG_LV_COLOR_DEPTH}|g" \
        -e "s|\(#define LV_MEM_CUSTOM .*\)0|\1${LVGL_CONFIG_LV_MEM_CUSTOM}|g" \
        \
        -e "s|\(#define LV_TICK_CUSTOM \).*|\1 1|g" \
        -e "s|\(#define LV_TICK_CUSTOM_INCLUDE \).*|\1 <stdint.h>|g" \
        -e "s|\(#define LV_TICK_CUSTOM_SYS_TIME_EXPR \).*|extern uint32_t custom_tick_get(void);\n\1 (custom_tick_get())|g" \
        \
        -e "s|\(^#define LV_USE_EVDEV \).*|#define LV_USE_EVDEV ${LVGL_CONFIG_USE_EVDEV}|g" \
        \
        -i "${S}/lv_conf.h"
}
