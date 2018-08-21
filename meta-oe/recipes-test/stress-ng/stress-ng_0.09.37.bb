SUMMARY = "A tool to load and stress a computer system"
HOMEPAGE = "http://kernel.ubuntu.com/~cking/stress-ng/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "zlib libaio"

SRC_URI = "http://kernel.ubuntu.com/~cking/tarballs/${BPN}/${BP}.tar.xz \
           file://0001-Revert-Makefile-force-sync-after-build-in-case-reboo.patch \
           file://0001-stress-ng-don-t-enable-TARGET_CLONES-for-musl.patch \
           "
SRC_URI_append_libc-musl = " \
    file://0001-Several-changes-to-fix-musl-build.patch \
    "
SRC_URI[md5sum] = "81d73313ea7778f749470b6f950553af"
SRC_URI[sha256sum] = "8494afb2f2e2cf7e79ec9c6c3129308b098e530141ba2b07d6547708fd63af3d"

UPSTREAM_CHECK_URI ?= "http://kernel.ubuntu.com/~cking/tarballs/${BPN}/"
UPSTREAM_CHECK_REGEX ?= "(?P<pver>\d+(\.\d+)+)\.tar"

CFLAGS += "-Wall -Wextra -DVERSION='"$(VERSION)"'"

do_install_append() {
    install -d ${D}${bindir}
    install -m 755 ${S}/stress-ng ${D}${bindir}/stress-ng
}
