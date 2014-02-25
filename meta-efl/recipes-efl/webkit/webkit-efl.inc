DESCRIPTION = "Webkit browser engine, EFL edition"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Source/WebKit/LICENSE;md5=4646f90082c40bcf298c285f8bab0b12 \
                    file://Source/JavaScriptCore/COPYING.LIB;md5=d0c6d6397a5d84286dda758da57bd691 \
                    file://Source/WebKit/efl/ewk/EWebKit.h;endline=20;md5=55ea170b1582797d2c69712de850f2fa \
"

# you need harfbuzz with icu enabled, you can add this to your config:
# PACKAGECONFIG_append_pn-harfbuzz = " icu"
DEPENDS = "icu libxslt sqlite3 gperf-native bison-native flex-native jpeg \
           libpng libxt fontconfig cairo freetype glib-2.0 libsoup-2.4 \
           libxml2 pango eina ecore evas edje eldbus harfbuzz enchant \
           ruby-native"

SRC_URI = "\
    ${E_RELEASES}/libs/webkit-efl/webkit-efl-164189.tar.xz \
    file://0001-WebKitHelpers.cmake-Add-Wno-error-cast-align.patch \
    file://0002-ARMAssembler.h-Don-t-generate-BKPT-and-BLX-for-armv4.patch \
    file://0003-Fix-linking-issue.patch \
"
SRC_URI[md5sum] = "731513fc042ec8e03840bc1ab6a66771"
SRC_URI[sha256sum] = "660aefd65c0e5c6494eaec30539cda5f40fbdff17f28e7e83d341b245227cccd"

S = "${WORKDIR}/efl-webkit"

inherit cmake lib_package pkgconfig perlnative pythonnative

ARM_INSTRUCTION_SET = "arm"

EXTRA_OECMAKE = " \
    -DPORT=Efl \
    -DSHARED_CORE=On \
    -DENABLE_DRAG_SUPPORT=On \
    -DENABLE_WEB_AUDIO=Off \
    -DENABLE_VIDEO=Off \
    -DENABLE_VIDEO_TRACK=Off \
    -DENABLE_ACCESSIBILITY=Off \
    -DENABLE_BATTERY_STATUS=Off \
"
# generated ASM code isn't compatible with armv[45]
# it was removed from macro assembler in upstream commit 121885
# https://bugs.webkit.org/show_bug.cgi?id=90198
EXTRA_OECMAKE_append_armv4 = " -DENABLE_LLINT=Off -DENABLE_JIT=Off -DENABLE_DFG_JIT=Off -DENABLE_FTL_JIT=Off"
EXTRA_OECMAKE_append_armv5 = " -DENABLE_LLINT=Off -DENABLE_JIT=Off -DENABLE_DFG_JIT=Off -DENABLE_FTL_JIT=Off"

# hack it in OptionsEfl.cmake because that overrules -DENABLE_LLINT=Off
do_configure_prepend_armv4() {
    sed -i 's/WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_LLINT ON)/WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_LLINT OFF)/g' ${S}/Source/cmake/OptionsEfl.cmake
}
do_configure_prepend_armv5() {
    sed -i 's/WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_LLINT ON)/WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_LLINT OFF)/g' ${S}/Source/cmake/OptionsEfl.cmake
}

# and disabled LLINT currently isn't supported, so restrict to armv7a and x86*
COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE_i586 = "(.*)"
COMPATIBLE_MACHINE_x86-64 = "(.*)"
COMPATIBLE_MACHINE_armv7a = "(.*)"

LEAD_SONAME = "libewebkit.so"
PACKAGES =+ "${PN}launcher-dbg ${PN}launcher ${PN}-inspector"

FILES_${PN} += "${datadir}/webkit-1.0/theme/default.edj ${datadir}/ewebkit-0/themes/default.edj"
FILES_${PN}-dev += "${libdir}/cmake"
FILES_${PN}launcher = "${bindir}/EWebLauncher"
FILES_${PN}launcher-dbg = "${bindir}/.debug/EWebLauncher"
FILES_${PN}-inspector += "${datadir}/ewebkit-0/inspector"
