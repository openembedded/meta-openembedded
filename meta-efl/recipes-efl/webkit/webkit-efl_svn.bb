DESCRIPTION = "Webkit browser engine, EFL edition"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Source/WebKit/LICENSE;md5=4646f90082c40bcf298c285f8bab0b12 \
                    file://Source/JavaScriptCore/COPYING.LIB;md5=d0c6d6397a5d84286dda758da57bd691 \
                    file://Source/WebKit/efl/ewk/EWebKit.h;endline=20;md5=55ea170b1582797d2c69712de850f2fa \
"

DEPENDS = "icu libxslt sqlite3 gperf-native bison-native flex-native jpeg \
           libpng libxt fontconfig cairo freetype glib-2.0 libsoup-2.4 \
           libxml2 pango eina ecore evas edje"

SRCREV = "105069"
PV = "1.7.3+svnr${SRCPV}"
PR = "r1"

SRCREV_FORMAT = "source"

SRC_URI = "\
  svn://svn.webkit.org/repository/webkit/trunk;module=Source;name=source;proto=http;subdir=src \
  svn://svn.webkit.org/repository/webkit/trunk/;module=WebKitLibraries;proto=http;subdir=src \
  svn://svn.webkit.org/repository/webkit/trunk/;module=Tools;proto=http;subdir=src \
  file://0001-JavaScriptCore-add-EINA_LIBRARIES-to-shell-build-for.patch \
  file://CMakeLists.txt \
"

S = "${WORKDIR}/src"

do_configure_prepend() {
  cp ${WORKDIR}/CMakeLists.txt ${S};
}

inherit cmake lib_package pkgconfig perlnative

ARM_INSTRUCTION_SET = "arm"

EXTRA_OECMAKE = "-DPORT=Efl -DSHARED_CORE=ON"

LEAD_SONAME = "libewebkit.so"
PACKAGES =+ "${PN}launcher-dbg ${PN}launcher"

FILES_${PN} += "${datadir}/webkit-1.0/theme/default.edj ${datadir}/ewebkit-0/themes/default.edj"
FILES_${PN}launcher = "${bindir}/EWebLauncher"
FILES_${PN}launcher-dbg = "${bindir}/.debug/EWebLauncher"
