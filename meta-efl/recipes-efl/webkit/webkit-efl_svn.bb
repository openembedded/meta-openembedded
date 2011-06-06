DESCRIPTION = "Webkit browser engine, EFL edition"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://WebKit/LICENSE;md5=4646f90082c40bcf298c285f8bab0b12 \
                    file://JavaScriptCore/COPYING.LIB;md5=d0c6d6397a5d84286dda758da57bd691 \
                    file://WebKit/efl/ewk/EWebKit.h;endline=20;md5=55ea170b1582797d2c69712de850f2fa \
"

DEPENDS = "icu libxslt sqlite3 gperf-native bison-native flex-native jpeg \
           libpng libxt fontconfig cairo freetype glib-2.0 libsoup-2.4 \
           libxml2 pango eina ecore evas edje"

SRCREV = "79192"
PV = "1.3.11+svnr${SRCPV}"

SRCREV_FORMAT = "source"

SRC_URI = "\
  svn://svn.webkit.org/repository/webkit/trunk;module=Source;name=source;proto=http;subdir=src \
  svn://svn.webkit.org/repository/webkit/trunk/;module=Tools;proto=http;subdir=src \
  file://revert.r78057.patch \
 "

S = "${WORKDIR}/src/Source"

inherit cmake lib_package pkgconfig

# Wants to jump too far for THUMB on armv4t
# in WebCore::DocTypeStringsHash::doctype_hash_function(char const*, unsigned int)':
# DocTypeStrings.cpp:(.text._ZN7WebCore18DocTypeStringsHash21doctype_hash_functionEPKcj[WebCore::DocTypeStringsHash::doctype_hash_function(char const*, unsigned int)]+0x12): relocation truncated to fit: R_ARM_THM_CALL against symbol `__gnu_thumb1_case_uhi' defined in .text section in x86_64-linux/usr/armv4t/lib/gcc/arm-oe-linux-gnueabi/4.5.2/libgcc.a(_thumb1_case_uhi.o)
# the same in WebCore::CSSValueKeywordsHash::value_hash_function(char const*, unsigned int)':
#             WebCore::CSSPropertyNamesHash::propery_hash_function(char const*, unsigned int)':
#             WebCore::ColorDataHash::colordata_hash_function(char const*, unsigned int)':
ARM_INSTRUCTION_SET = "ARM"

EXTRA_OECMAKE = "-DPORT=Efl -DSHARED_CORE=ON"

LEAD_SONAME = "libewebkit.so"
PACKAGES =+ "${PN}launcher-dbg ${PN}launcher"

FILES_${PN} += "${datadir}/webkit-1.0/theme/default.edj ${datadir}/ewebkit-0/themes/default.edj"
FILES_${PN}launcher = "${bindir}/EWebLauncher"
FILES_${PN}launcher-dbg = "${bindir}/.debug/EWebLauncher"
