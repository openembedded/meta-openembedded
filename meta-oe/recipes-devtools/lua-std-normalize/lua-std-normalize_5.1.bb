SUMMARY = "Normalized Lua functions"
DESCRIPTION = "std.normalize injects deterministic versions of core Lua \
functions that do not behave identically across Lua implementations into a \
module's lexical environment."
HOMEPAGE = "https://github.com/lua-stdlib/normalize"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=06275ffb1a4a2c92e56a9bd5e3e38b4c"

SRC_URI = "git://github.com/lua-stdlib/normalize.git;protocol=https;branch=release-v${PV};tag=v${PV}"
SRCREV = "f3e8d7639d62cebe18829f6e1284f9e4faeb78a6"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)*)$"

LUA_VERSION = "5.5"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/lua/${LUA_VERSION}/std/normalize
    install -m 0644 ${S}/lib/std/normalize/init.lua ${S}/lib/std/normalize/_core.lua \
        ${D}${datadir}/lua/${LUA_VERSION}/std/normalize/
    sed -e 's|@version@|${PV}|' ${S}/lib/std/normalize/version.lua.in \
        > ${D}${datadir}/lua/${LUA_VERSION}/std/normalize/version.lua
}

FILES:${PN} = "${datadir}/lua/${LUA_VERSION}"

RDEPENDS:${PN} = "lua"
