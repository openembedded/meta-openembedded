SUMMARY = "plocate, a much faster locate"
HOMEPAGE = "https://plocate.sesse.net/"
DESCRIPTION = "plocate is a locate(1) based on posting lists, completely replacing mlocate with a much faster (and smaller) index. It is suitable as a default locate on your system."
SECTION = "base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit meson pkgconfig

DEPENDS = "zstd liburing"

SRC_URI = "https://plocate.sesse.net/download/${BP}.tar.gz;subdir=${BP} \
           file://0001-Include-linux-stat.h-only-when-sys-stat.h-is-not-inc.patch"

SRC_URI[sha256sum] = "68c1d5fbb11864403ae39c1a5937f13afd80b03e68041368831ef58cb613578e"
