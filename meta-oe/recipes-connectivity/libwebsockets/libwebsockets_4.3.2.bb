SUMMARY = "Canonical libwebsockets.org websocket library"
HOMEPAGE = "https://libwebsockets.org/"
LICENSE = "MIT & Zlib & BSD-3-Clause & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=382bfdf329e774859fd401eaf850d29b"

DEPENDS = "zlib"

S = "${WORKDIR}/git"
SRCREV = "b0a749c8e7a8294b68581ce4feac0e55045eb00b"
SRC_URI = "git://github.com/warmcat/libwebsockets.git;protocol=https;branch=v4.3-stable \
           file://0001-Fix-Werror-enum-int-mismatch-in-lws_tls_server_abort.patch"

UPSTREAM_CHECK_URI = "https://github.com/warmcat/${BPN}/releases"
UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

inherit cmake pkgconfig

PACKAGECONFIG ?= "libuv client server http2 ssl ${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
PACKAGECONFIG[client] = "-DLWS_WITHOUT_CLIENT=OFF,-DLWS_WITHOUT_CLIENT=ON,"
PACKAGECONFIG[http2] = "-DLWS_WITH_HTTP2=ON,-DLWS_WITH_HTTP2=OFF,"
PACKAGECONFIG[ipv6] = "-DLWS_IPV6=ON,-DLWS_IPV6=OFF,"
PACKAGECONFIG[libevent] = "-DLWS_WITH_LIBEVENT=ON,-DLWS_WITH_LIBEVENT=OFF,libevent"
PACKAGECONFIG[libev] = "-DLWS_WITH_LIBEV=ON,-DLWS_WITH_LIBEV=OFF,libev"
PACKAGECONFIG[libuv] = "-DLWS_WITH_LIBUV=ON,-DLWS_WITH_LIBUV=OFF,libuv"
PACKAGECONFIG[server] = "-DLWS_WITHOUT_SERVER=OFF,-DLWS_WITHOUT_SERVER=ON,"
PACKAGECONFIG[ssl] = "-DLWS_WITH_SSL=ON,-DLWS_WITH_SSL=OFF,openssl"
PACKAGECONFIG[static] = "-DLWS_WITH_STATIC=ON,-DLWS_WITH_STATIC=OFF -DLWS_LINK_TESTAPPS_DYNAMIC=ON,"
PACKAGECONFIG[systemd] = "-DLWS_WITH_SDEVENT=ON,-DLWS_WITH_SDEVENT=OFF,systemd"

python __anonymous() {
  if bb.utils.contains('PACKAGECONFIG', 'systemd', True, False, d) and not bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d):
    bb.fatal("PACKAGECONFIG contains systemd but DISTRO_FEATURES doesn't")
}

EXTRA_OECMAKE += " \
    -DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')} \
"

PACKAGES =+ "${PN}-testapps ${PN}-evlib-event ${PN}-evlib-uv ${PN}-evlib-ev ${PN}-evlib-sd"

FILES:${PN}-testapps += "${datadir}/libwebsockets-test-server/* ${bindir}/libwebsockets-test-*"
FILES:${PN}-evlib-event += "${libdir}/libwebsockets-evlib_event.so"
FILES:${PN}-evlib-uv += "${libdir}/libwebsockets-evlib_uv.so"
FILES:${PN}-evlib-ev += "${libdir}/libwebsockets-evlib_ev.so"
FILES:${PN}-evlib-sd += "${libdir}/libwebsockets-evlib_sd.so"

RDEPENDS:${PN} += " ${@bb.utils.contains('PACKAGECONFIG', 'libevent', '${PN}-evlib-event', '', d)}"
RDEPENDS:${PN} += " ${@bb.utils.contains('PACKAGECONFIG', 'libuv', '${PN}-evlib-uv', '', d)}"
RDEPENDS:${PN} += " ${@bb.utils.contains('PACKAGECONFIG', 'libev', '${PN}-evlib-ev', '', d)}"
RDEPENDS:${PN} += " ${@bb.utils.contains('PACKAGECONFIG', 'systemd', '${PN}-evlib-sd', '', d)}"

RDEPENDS:${PN}-dev += " ${@bb.utils.contains('PACKAGECONFIG', 'static', '${PN}-staticdev', '', d)}"

# Avoid absolute paths to end up in the sysroot.
SSTATE_SCAN_FILES += "*.cmake"
