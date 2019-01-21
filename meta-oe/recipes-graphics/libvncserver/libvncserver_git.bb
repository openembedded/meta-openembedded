DESCRIPTION = "library for easy implementation of a RDP/VNC server"
HOMEPAGE = "https://libvnc.github.io"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=361b6b837cad26c6900a926b62aada5f"

PACKAGECONFIG ??= " \
    gcrypt \
    gnutls \
    jpeg \
    png \
    ${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','',d)} \
    sdl \
    zlib \
"
PACKAGECONFIG[gcrypt] = ",,libgcrypt,libgcrypt"
PACKAGECONFIG[gnutls] = ",,gnutls"
PACKAGECONFIG[jpeg] = ",-DWITH_JPEG=OFF,jpeg"
PACKAGECONFIG[openssl] = ",-DWITH_OPENSSL=OFF,openssl"
PACKAGECONFIG[png] = ",-DWITH_PNG=OFF,libpng,libpng"
PACKAGECONFIG[systemd] = ",,systemd"
PACKAGECONFIG[sdl] = ",,libsdl2"
PACKAGECONFIG[zlib] = ",,zlib"

inherit cmake

SRC_URI = "git://github.com/LibVNC/libvncserver"
SRCREV = "f997b5a75fa171d79c5e568b7157fba83c8d8355"

S = "${WORKDIR}/git"
