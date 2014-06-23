SUMMARY = "Manage plain dm-crypt and LUKS encrypted volumes"
DESCRIPTION = "Cryptsetup is used to conveniently setup dm-crypt managed \
device-mapper mappings. These include plain dm-crypt volumes and \
LUKS volumes. The difference is that LUKS uses a metadata header \
and can hence offer more features than plain dm-crypt. On the other \
hand, the header is visible and vulnerable to damage."
HOMEPAGE = "http://code.google.com/p/cryptsetup/"
SECTION = "console"
LICENSE = "GPL-2.0-with-OpenSSL-exception"
LIC_FILES_CHKSUM = "file://COPYING;md5=32107dd283b1dfeb66c9b3e6be312326"

DEPENDS = "util-linux lvm2 popt libgcrypt"

SRC_URI = "http://cryptsetup.googlecode.com/files/cryptsetup-${PV}.tar.bz2"
SRC_URI[md5sum] = "cd834da49fbe92dd66df02cc5c61280f"
SRC_URI[sha256sum] = "15723f0198303d4bcb99d480b7a773918e2d319f0348457988c063bdd03e109a"

inherit autotools gettext pkgconfig

# Use openssl because libgcrypt drops root privileges
# if libgcrypt is linked with libcap support
PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[openssl] = "--with-crypto_backend=openssl,,openssl"
PACKAGECONFIG[gcrypt] = "--with-crypto_backend=gcrypt,,libgcrypt"

RRECOMMENDS_${PN} = "kernel-module-aes-generic \
                     kernel-module-dm-crypt \
                     kernel-module-md5 \
                     kernel-module-cbc \
                     kernel-module-sha256-generic \
"

EXTRA_OECONF = "--enable-static"
