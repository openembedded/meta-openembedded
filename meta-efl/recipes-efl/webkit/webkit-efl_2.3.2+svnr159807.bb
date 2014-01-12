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
    ${E_RELEASES}/libs/webkit-efl/webkit-efl-159807.tar.xz \
    file://gcc-4.8.2.fix.patch \
    file://0001-WebKitHelpers.cmake-Add-Wno-error-cast-align.patch \
    file://0001-ButterflyInlines.h-remove-indexingHeader-preCapacity.patch \
"
SRC_URI[md5sum] = "3e1377d976460dd14db0dd3cd39b8e48"
SRC_URI[sha256sum] = "a99531299e41ba4671b32bbf46c3efc4d65960c9c87949a87f76e622c284f178"

S = "${WORKDIR}/${BPN}"

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

LEAD_SONAME = "libewebkit.so"
PACKAGES =+ "${PN}launcher-dbg ${PN}launcher ${PN}-inspector"

FILES_${PN} += "${datadir}/webkit-1.0/theme/default.edj ${datadir}/ewebkit-0/themes/default.edj"
FILES_${PN}-dev += "${libdir}/cmake"
FILES_${PN}launcher = "${bindir}/EWebLauncher"
FILES_${PN}launcher-dbg = "${bindir}/.debug/EWebLauncher"
FILES_${PN}-inspector += "${datadir}/ewebkit-0/inspector"
