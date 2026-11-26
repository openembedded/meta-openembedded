SUMMARY = "A New System Troubleshooting Tool Built for the Way You Work"
DESCRIPTION = "Sysdig is open source, system-level exploration: capture \
system state and activity from a running Linux instance, then save, \
filter and analyze."
HOMEPAGE = "http://www.sysdig.org/"
LICENSE = "Apache-2.0 AND (GPL-2.0-only OR MIT)"
LIC_FILES_CHKSUM = "file://COPYING;md5=f8fee3d59797546cffab04f3b88b2d44"

inherit cmake pkgconfig

#OECMAKE_GENERATOR = "Unix Makefiles"
JIT ?= "jit"
JIT:mipsarchn32 = ""
JIT:mipsarchn64 = ""
JIT:riscv64 = ""
JIT:riscv32 = ""
JIT:powerpc = ""
JIT:powerpc64le = ""
JIT:powerpc64 = ""

DEPENDS += "lua${JIT} zlib ncurses jsoncpp tbb elfutils re2 valijson \
            uthash libbpf clang-native bpftool-native yaml-cpp nlohmann-json"
RDEPENDS:${PN} = "bash"

SRC_URI = "git://github.com/draios/sysdig.git;branch=dev;protocol=https;tag=${PV};name=sysdig \
           git://github.com/falcosecurity/libs;protocol=https;branch=release/0.21.x;tag=0.21.0;name=falco;subdir=${BB_GIT_DEFAULT_DESTSUFFIX}/falcosecurity-libs \
           git://github.com/falcosecurity/libs;protocol=https;branch=release/0.21.x;tag=8.1.0+driver;name=driver;subdir=${BB_GIT_DEFAULT_DESTSUFFIX}/driver \
           file://0001-cmake-Make-the-container-plugin-download-optional.patch \
           file://0001-Avoid-duplicate-operations-of-add_library.patch;patchdir=./falcosecurity-libs \
          "
SRCREV_sysdig = "7f09646f010f257f3854bc7a6cfbf432c85897d3"
SRCREV_falco = "1dcb369c20625db51f6cdca3e62435f61449eacb"
SRCREV_driver = "bd2b9aa2d7ddd9f65158fd8c95ea4bbc10eae9a0"

SRCREV_FORMAT = "sysdig_falco"

UPSTREAM_CHECK_GITTAGREGEX = "^(?P<pver>\d+(\.\d+)+)$"

EXTRA_OECMAKE = "\
                -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
                -DBUILD_DRIVER=OFF \
                -DUSE_BUNDLED_FALCOSECURITY_LIBS=ON \
                -DENABLE_CONTAINER_PLUGIN=OFF \
                -DUSE_BUNDLED_DEPS=OFF \
                -DCREATE_TEST_TARGETS=OFF \
                -DDIR_ETC=${sysconfdir} \
                -DLUA_INCLUDE_DIR=${STAGING_INCDIR}/luajit-2.1 \
                -DFALCOSECURITY_LIBS_SOURCE_DIR=${S}/falcosecurity-libs \
                -DDRIVER_SOURCE_DIR=${S}/driver/driver \
                -DVALIJSON_INCLUDE=${STAGING_INCDIR}/valijson \
                -DUSE_BUNDLED_RE2=OFF \
                -DUSE_BUNDLED_TBB=OFF \
                -DUSE_BUNDLED_JSONCPP=OFF \
                -DBUILD_SYSDIG_MODERN_BPF=OFF \
                -DCREATE_TEST_TARGETS=OFF \
                -DCURSES_INCLUDE_DIR=${STAGING_INCDIR} \
                -DCURSES_LIBRARIES=${STAGING_LIBDIR}/libncurses.so\;${STAGING_LIBDIR}/libform.so \
"

# sysdig's chisel code does #include "lua.h", but the only lua.h it must ever
# resolve to is LuaJIT's (it links libluajit-5.1.so). The native sysroot also
# ships a stock Lua header (lua-native pulls lua.h into
# recipe-sysroot-native/usr/include), and in Lua >= 5.4.4 luaL_openlibs is a
# macro for luaL_openselectedlibs while lua_setglobal/getglobal/pcallk are real
# symbols, none of which LuaJIT provides -- so compiling chisel against it makes
# the link fail with undefined Lua symbols.
#
# That native include dir leaks onto the target compile line because sysdig's
# ncurses.cmake runs find_package(Curses), which resolves CURSES_INCLUDE_DIR to
# the native sysroot (native also carries ncurses.h/form.h) and then does
# include_directories(${CURSES_INCLUDE_DIR}), placing it ahead of the LuaJIT
# include dir. Pin Curses to the target sysroot so the native dir (and its stray
# lua.h) never reaches the target build; lua.h then resolves via LUAJIT_INCLUDE.
CXXFLAGS:append = " -I${STAGING_INCDIR}/luajit-2.1"
CFLAGS:append = " -I${STAGING_INCDIR}/luajit-2.1"

#To fix do_package QA Issue
do_compile:append() {
    sed -i -e "s,${WORKDIR},,g" ${B}/libsinsp/libsinsp.pc
    sed -i -e "s,${WORKDIR},,g" ${B}/libscap/libscap.pc
}

#CMAKE_VERBOSE = "VERBOSE=1"


FILES:${PN} += " \
    ${DIR_ETC}/* \
    ${datadir}/zsh/* \
    ${prefix}/src/*  \
"
# Use getaddrinfo_a is a GNU extension in libsinsp
# It should be fixed in sysdig, until then disable
# on musl
# Something like this https://code.videolan.org/ePirat/vlc/-/commit/01fd9fe4c7f6c5558f7345f38abf0152e17853ab  is needed to fix it
COMPATIBLE_HOST:libc-musl = "null"
COMPATIBLE_HOST:mips = "null"
COMPATIBLE_HOST:riscv64 = "null"
COMPATIBLE_HOST:riscv32 = "null"
COMPATIBLE_HOST:powerpc = "null"
COMPATIBLE_HOST:powerpc64le = "null"
