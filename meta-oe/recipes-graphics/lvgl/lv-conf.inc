PACKAGECONFIG[drm] = ",,libdrm"
PACKAGECONFIG[fbdev] = ",,"
PACKAGECONFIG[sdl] = ",,virtual/libsdl2 libsdl2-image"

LVGL_CONFIG_USE_DRM = "${@bb.utils.contains('PACKAGECONFIG', 'drm', '1', '0', d)}"

LVGL_CONFIG_USE_EVDEV = "${@bb.utils.contains_any('PACKAGECONFIG', 'drm fbdev', '1', '0', d)}"

LVGL_CONFIG_USE_FBDEV = "${@bb.utils.contains('PACKAGECONFIG', 'fbdev', '1', '0', d)}"

LVGL_CONFIG_USE_SDL = "${@bb.utils.contains('PACKAGECONFIG', 'sdl', '1', '0', d)}"
LVGL_CONFIG_SDL_FULLSCREEN ?= "0"

LVGL_CONFIG_LV_MEM_CUSTOM ?= "0"
LVGL_CONFIG_LV_MEM_SIZE ?= "(64 * 1024U)"
LVGL_CONFIG_LV_COLOR_DEPTH ?= "32"

LVGL_CONFIG_LV_USE_LOG    ?= "0"
LVGL_CONFIG_LV_LOG_LEVEL  ?= "LV_LOG_LEVEL_WARN"
LVGL_CONFIG_LV_LOG_PRINTF ?= "0"

LVGL_CONFIG_LV_USE_FONT_COMPRESSED ?= "0"

LVGL_CONFIG_LV_THEME_DEFAULT_DARK ?= "0"

DEBUG_BUILD ??= "0"

ALLOW_EMPTY:${PN} = "1"

EXTRA_OECMAKE += "-Dinstall:BOOL=ON -DLIB_INSTALL_DIR=${baselib}"

do_configure:append() {
    # If there is a configuration template, start from that
    [ -r "${S}/lv_conf_template.h" ] && cp -Lv "${S}/lv_conf_template.h" "${S}/lv_conf.h"

    sed -e "s|#if 0 .*Set it to \"1\" to enable content.*|#if 1 // Enabled by ${PN}|g" \
        \
        -e "s|\(^#define LV_USE_LINUX_DRM \).*|\1${LVGL_CONFIG_USE_DRM}|g" \
        \
        -e "s|\(^#define LV_USE_LINUX_FBDEV \).*|\1${LVGL_CONFIG_USE_FBDEV}|g" \
        \
        -e "s|\(^#define LV_USE_SDL \).*|\1${LVGL_CONFIG_USE_SDL}|g" \
        -e "s|\(^#define LV_USE_DRAW_SDL \).*|\1${LVGL_CONFIG_USE_SDL}|g" \
        -e "s|\(^ \+#define LV_SDL_BUF_COUNT \).*|\1 2|g" \
        -e "s|\(^ \+#define LV_SDL_FULLSCREEN \).*|\1${LVGL_CONFIG_SDL_FULLSCREEN}|g" \
        \
        -e "s|\(^#define LV_COLOR_DEPTH \).*|\1${LVGL_CONFIG_LV_COLOR_DEPTH}|g" \
        -e "s|\(#define LV_MEM_CUSTOM .*\).*|\1${LVGL_CONFIG_LV_MEM_CUSTOM}|g" \
        -e "s|\(^ \+#define LV_MEM_SIZE \).*|\1${LVGL_CONFIG_LV_MEM_SIZE}|g" \
        \
        -e "s|\(#define LV_TICK_CUSTOM \).*|\1 1|g" \
        -e "s|\(#define LV_TICK_CUSTOM_INCLUDE \).*|\1 <stdint.h>|g" \
        -e "s|\(#define LV_TICK_CUSTOM_SYS_TIME_EXPR \).*|extern uint32_t custom_tick_get(void);\n\1 (custom_tick_get())|g" \
        \
        -e "s|\(^#define LV_USE_EVDEV \).*|\1${LVGL_CONFIG_USE_EVDEV}|g" \
        \
        -e "s|\(^#define LV_USE_ASSERT_NULL \).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^#define LV_USE_ASSERT_MALLOC \).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^#define LV_USE_ASSERT_STYLE \).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^#define LV_USE_ASSERT_MEM_INTEGRITY \).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^#define LV_USE_ASSERT_OBJ \).*|\1${DEBUG_BUILD}|g" \
        \
        -e "s|\(^#define LV_USE_LOG \).*|\1${LVGL_CONFIG_LV_USE_LOG}|g" \
        -e "s|\(^ \+#define LV_LOG_LEVEL \).*|\1${LVGL_CONFIG_LV_LOG_LEVEL}|g" \
        -e "s|\(^ \+#define LV_LOG_PRINTF \).*|\1${LVGL_CONFIG_LV_LOG_PRINTF}|g" \
        \
        -e "s|\(^#define LV_USE_FONT_COMPRESSED \).*|\1${LVGL_CONFIG_LV_USE_FONT_COMPRESSED}|g" \
        -e "s|\(^ \+#define LV_THEME_DEFAULT_DARK \).*|\1${LVGL_CONFIG_LV_THEME_DEFAULT_DARK}|g" \
        \
        -i "${S}/lv_conf.h"
}
