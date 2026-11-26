SUMMARY = "Taisei Project is an open source danmaku"
DESCRIPTION = "Taisei Project is an open source fan-game set in the world of \
               Tōhō Project. It is a top-down vertical-scrolling curtain fire \
               shooting game (STG), also known as a 'bullet hell' or 'danmaku'. \
               STGs are fast-paced games focused around pattern recognition and \
               mastery through practice."
HOMEPAGE = "https://taisei-project.org"
BUGTRACKER = "https://github.com/taisei-project/taisei/issues"
LICENSE = "CC-BY-4.0 AND CC0-1.0 AND MIT"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=23a03cac0907277013b7af25f8ed1494"

DEPENDS = "\
    cglm \
    freetype \
    gettext-native \
    libsdl3 \
    libunibreak \
    libwebp \
    opusfile \
    zstd \
"
DEPENDS:append:libc-musl = " libucontext"
LDFLAGS:append:libc-musl = " -lucontext"

SRC_URI = "gitsm://github.com/taisei-project/taisei.git;branch=v1.4.x;protocol=https;tag=v${PV}"
SRCREV = "5bfc9b842fe6d9bb8d1610f76f442ba07c72c4cf"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

inherit features_check meson mime mime-xdg pkgconfig

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'lto', d)}"

PACKAGECONFIG[a_null] = "-Da_null=true,-Da_null=false"
PACKAGECONFIG[developer] = "-Ddeveloper=true,-Ddeveloper=false"
PACKAGECONFIG[docs] = "-Ddocs=true,-Ddocs=false,python3-docutils-native"
PACKAGECONFIG[rtti] = "-Dcpp_rtti=true,-Dcpp_rtti=false,"
PACKAGECONFIG[lto] = "-Db_lto=true,-Db_lto=false,"

EXTRA_OEMESON += "-Dstrip=false"

FILES:${PN} += "${datadir}"
