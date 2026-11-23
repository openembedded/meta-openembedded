DESCRIPTION = "luaposix is a POSIX binding for Lua."
LICENSE = "MIT"
HOMEPAGE = "https://github.com/luaposix/luaposix"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3f4d11c0590d3adb0a38c88477af3598"

DEPENDS += "lua-native lua virtual/crypt"

SRC_URI = "git://github.com/luaposix/luaposix.git;branch=release-v${PV};tag=v${PV};protocol=https;name=luaposix \
           git://github.com/gvvaughan/luke.git;branch=release-v0.9;tag=v0.9;protocol=https;name=luke;destsuffix=luke \
"
SRCREV_luaposix = "71638fb00c93a38d64daeccdeecedf2a44498006"
SRCREV_luke = "b18fd6eef0af4380c081976b2e016c76956b571a"
SRCREV_FORMAT = "luaposix"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)*)$"

LUA_VERSION = "5.5"

B = "${S}"

LUKE = "${UNPACKDIR}/luke/build-aux/luke --file=lukefile.oe"

inherit pkgconfig

# luaposix only describes its build in the rockspec, so turn that into a lukefile
do_configure() {
    cat ${S}/luaposix-${PV}-1.rockspec - > ${B}/lukefile.oe <<LUKEFILE

luke_format = 0
version = '${PV}'
defines = build.defines
incdirs = build.incdirs
modules = build.modules
build = nil
source = nil
description = nil
dependencies = nil
build_dependencies = nil
test_dependencies = nil
test = nil
rockspec_format = nil
LUKEFILE
}

do_compile() {
    ${LUKE} LUA_INCDIR=${STAGING_INCDIR} CFLAGS="${CFLAGS}"
}

do_install() {
    ${LUKE} PREFIX=${D}${prefix} INST_LIBDIR=${D}${libdir}/lua/${LUA_VERSION} install
}

FILES:${PN} = "${datadir}/lua/${LUA_VERSION} \
               ${libdir}/lua/${LUA_VERSION}"

RDEPENDS:${PN} = "lua-std-normalize"
