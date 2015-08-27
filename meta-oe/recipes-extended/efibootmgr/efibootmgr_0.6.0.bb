DESCRIPTION = "Linux user-space application to modify the EFI Boot Manager."
SUMMARY = "EFI Boot Manager"
HOMEPAGE = "http://linux.dell.com/efibootmgr/"
SECTION = "base"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

DEPENDS = "pciutils zlib"

COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"

SRC_URI = "http://linux.dell.com/efibootmgr/permalink/efibootmgr-${PV}.tar.gz \
    file://ldflags.patch \
    file://docbook-fixes.patch \
    file://w-keep-existing-mbr-signature.patch \
"

SRC_URI[md5sum] = "9e9a31d79e579644de83a14139b66d10"
SRC_URI[sha256sum] = "5167488b92950e60028d1025942ce6bda04638c6fb5e110abb8c8f687844d155"

EXTRA_OEMAKE = "'CC=${CC}' 'CFLAGS=${CFLAGS} -I${S}/src/lib -I${S}/src/include -fgnu89-inline'"

do_install () {
    install -D -p -m0755 src/efibootmgr/efibootmgr ${D}/${sbindir}/efibootmgr
}
