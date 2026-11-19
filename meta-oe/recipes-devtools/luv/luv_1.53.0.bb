SUMMARY = "libuv bindings for LuaJIT and Lua"
HOMEPAGE = "https://github.com/luvit/luv"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

LUV_REV = "1"
SRC_URI = "https://github.com/luvit/luv/releases/download/${PV}-${LUV_REV}/luv-${PV}-${LUV_REV}.tar.gz"
SRC_URI[sha256sum] = "569b488d5d31609ea466a6c70e008a42661ec773ea1584d3d766d46280ab7cf4"

S = "${UNPACKDIR}/luv-${PV}-${LUV_REV}"

DEPENDS = "luajit libuv"

inherit cmake

EXTRA_OECMAKE = " \
    -DBUILD_SHARED_LIBS=ON \
    -DWITH_SHARED_LIBUV=ON \
    -DBUILD_MODULE=OFF \
    -DLUA_BUILD_TYPE=System \
    -DWITH_LUA_ENGINE=LuaJIT \
"

BBCLASSEXTEND = "native"
