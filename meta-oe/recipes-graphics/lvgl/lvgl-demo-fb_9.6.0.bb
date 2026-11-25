SUMMARY = "LVGL Demo Application for Framebuffer"
HOMEPAGE = "https://github.com/lvgl/lv_port_linux_frame_buffer"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=802d3d83ae80ef5f343050bf96cce3a4 \
                    file://lvgl/LICENCE.txt;md5=4570b6241b4fced1d1d18eb691a0e083"

DEPENDS = "python3-pcpp-native"

PV .= "+git"

SRC_URI = "\
    git://github.com/lvgl/lv_port_linux_frame_buffer.git;protocol=https;branch=release/v9.6;name=demo \
    git://github.com/lvgl/lvgl;protocol=https;branch=release/v9.6;tag=v9.6.0;name=lvgl;subdir=${BB_GIT_DEFAULT_DESTSUFFIX}/lvgl \
"

SRCREV_demo = "0728bc4eff0ff85ef787db3f557a654c5c7c9c41"
SRCREV_lvgl = "80ca777e37a2b176770726a02e07a6fb79ef0b39"
SRCREV_FORMAT = "demo_lvgl"
UPSTREAM_CHECK_COMMITS = "1"

inherit cmake pkgconfig

require lv-conf.inc

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/bin/lvglsim ${D}${bindir}
}
