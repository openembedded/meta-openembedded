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
        -e "s|\(^[ \t]*#define LV_USE_LINUX_DRM[ \t]\).*|\1${LVGL_CONFIG_USE_DRM}|g" \
        \
        -e "s|\(^[ \t]*#define LV_USE_LINUX_FBDEV[ \t]\).*|\1${LVGL_CONFIG_USE_FBDEV}|g" \
        \
        -e "s|\(^[ \t]*#define LV_USE_SDL[ \t]\).*|\1${LVGL_CONFIG_USE_SDL}|g" \
        -e "s|\(^[ \t]*#define LV_USE_DRAW_SDL[ \t]\).*|\1${LVGL_CONFIG_USE_SDL}|g" \
        -e "s|\(^[ \t]*#define LV_SDL_BUF_COUNT[ \t]\).*|\1 2|g" \
        -e "s|\(^[ \t]*#define LV_SDL_FULLSCREEN[ \t]\).*|\1${LVGL_CONFIG_SDL_FULLSCREEN}|g" \
        \
        -e "s|\(^[ \t]*#define LV_COLOR_DEPTH[ \t]\).*|\1${LVGL_CONFIG_LV_COLOR_DEPTH}|g" \
        -e "s|\(^[ \t]*#define LV_MEM_CUSTOM[ \t].*\).*|\1${LVGL_CONFIG_LV_MEM_CUSTOM}|g" \
        -e "s|\(^[ \t]*#define LV_MEM_SIZE[ \t]\).*|\1${LVGL_CONFIG_LV_MEM_SIZE}|g" \
        \
        -e "s|\(^[ \t]*#define LV_TICK_CUSTOM[ \t]\).*|\1 1|g" \
        -e "s|\(^[ \t]*#define LV_TICK_CUSTOM_INCLUDE[ \t]\).*|\1 <stdint.h>|g" \
        -e "s|\(^[ \t]*#define LV_TICK_CUSTOM_SYS_TIME_EXPR[ \t]\).*|extern uint32_t custom_tick_get(void);\n\1 (custom_tick_get())|g" \
        \
        -e "s|\(^[ \t]*#define LV_USE_EVDEV[ \t]\).*|\1${LVGL_CONFIG_USE_EVDEV}|g" \
        \
        -e "s|\(^[ \t]*#define LV_USE_ASSERT_NULL[ \t]\).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^[ \t]*#define LV_USE_ASSERT_MALLOC[ \t]\).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^[ \t]*#define LV_USE_ASSERT_STYLE[ \t]\).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^[ \t]*#define LV_USE_ASSERT_MEM_INTEGRITY[ \t]\).*|\1${DEBUG_BUILD}|g" \
        -e "s|\(^[ \t]*#define LV_USE_ASSERT_OBJ[ \t]\).*|\1${DEBUG_BUILD}|g" \
        \
        -e "s|\(^[ \t]*#define LV_USE_LOG[ \t]\).*|\1${LVGL_CONFIG_LV_USE_LOG}|g" \
        -e "s|\(^[ \t]*#define LV_LOG_LEVEL[ \t]\).*|\1${LVGL_CONFIG_LV_LOG_LEVEL}|g" \
        -e "s|\(^[ \t]*#define LV_LOG_PRINTF[ \t]\).*|\1${LVGL_CONFIG_LV_LOG_PRINTF}|g" \
        \
        -e "s|\(^[ \t]*#define LV_USE_FONT_COMPRESSED[ \t]\).*|\1${LVGL_CONFIG_LV_USE_FONT_COMPRESSED}|g" \
        -e "s|\(^[ \t]*#define LV_THEME_DEFAULT_DARK[ \t]\).*|\1${LVGL_CONFIG_LV_THEME_DEFAULT_DARK}|g" \
        \
        -i "${S}/lv_conf.h"
}
