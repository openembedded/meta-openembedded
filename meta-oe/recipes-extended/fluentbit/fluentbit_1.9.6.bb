SUMMARY = "Fast Log processor and Forwarder"
DESCRIPTION = "Fluent Bit is a data collector, processor and  \
forwarder for Linux. It supports several input sources and \
backends (destinations) for your data. \
"

HOMEPAGE = "http://fluentbit.io"
BUGTRACKER = "https://github.com/fluent/fluent-bit/issues"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"
SECTION = "net"

SRC_URI = "https://releases.fluentbit.io/1.9/source-${PV}.tar.gz;subdir=fluent-bit-${PV} \
           file://0001-CMakeLists.txt-Do-not-use-private-makefile-target.patch \
           file://0002-flb_info.h.in-Do-not-hardcode-compilation-directorie.patch \
           file://0003-mbedtls-Do-not-overwrite-CFLAGS.patch \
           file://0004-build-Make-systemd-init-systemd-detection-contingent.patch \
           "
SRC_URI[sha256sum] = "5ef7dd97e10936269fe5f4e5d3ebf16559333066f7d6757ba12464a9d6186570"

S = "${WORKDIR}/fluent-bit-${PV}"

DEPENDS = "zlib bison-native flex-native openssl"
DEPENDS += "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"

PACKAGECONFIG[yaml] = "-DFLB_CONFIG_YAML=On,-DFLB_CONFIG_YAML=Off,libyaml"
PACKAGECONFIG[kafka] = "-DFLB_OUT_KAFKA=On,-DFLB_OUT_KAFKA=Off,librdkafka"
PACKAGECONFIG[examples] = "-DFLB_EXAMPLES=On,-DFLB_EXAMPLES=Off"
PACKAGECONFIG[jemalloc] = "-DFLB_JEMALLOC=On,-DFLB_JEMALLOC=Off,jemalloc"
#TODO add more fluentbit options to PACKAGECONFIG[]

DEPENDS:append:libc-musl = " fts "

# flex hardcodes the input file in #line directives leading to TMPDIR contamination of debug sources.
do_compile:append() {
    find ${B} -name '*.c' -or -name '*.h' | xargs sed -i -e 's|${TMPDIR}|/usr/src/debug/${PN}/${EXTENDPE}${PV}-${PR}/|g'
}

FLB_JEMALLOC_OPTIONS_LIST = "--with-jemalloc-prefix=je_ --with-lg-quantum=3"

PACKAGECONFIG ?= "yaml"

LTO = ""

# Use CMake 'Unix Makefiles' generator
OECMAKE_GENERATOR ?= "Unix Makefiles"

# Fluent Bit build options
# ========================

# Host related setup
EXTRA_OECMAKE += "-DGNU_HOST=${HOST_SYS} -DFLB_TD=1"

# Disable LuaJIT and filter_lua support
EXTRA_OECMAKE += "-DFLB_LUAJIT=Off -DFLB_FILTER_LUA=Off "

# Disable Library and examples
EXTRA_OECMAKE += "-DFLB_SHARED_LIB=Off"

# Enable systemd iff systemd is in DISTRO_FEATURES
EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES','systemd','-DFLB_SYSTEMD=On','-DFLB_SYSTEMD=Off',d)}"

EXTRA_OECMAKE:append:riscv64 = " -DFLB_DEPS='atomic'"
EXTRA_OECMAKE:append:riscv32 = " -DFLB_DEPS='atomic'"

inherit cmake systemd pkgconfig

SYSTEMD_SERVICE:${PN} = "td-agent-bit.service"

EXTRA_OECMAKE += "-DCMAKE_DEBUG_SRCDIR=/usr/src/debug/${PN}/${EXTENDPE}${PV}-${PR}/"
TARGET_CC_ARCH += " ${SELECTED_OPTIMIZATION}"
