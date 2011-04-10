DESCRIPTION = "A C library for multiple-precision floating-point computations with exact rounding"
HOMEPAGE = "http://www.mpfr.org/"
LICENSE="GPLv3&LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LESSER;md5=6a6a8e020838b23406c81b19c1d46df6"
SECTION = "devel"
BBCLASSEXTEND = "native nativesdk"
DEPENDS = "gmp"

inherit autotools

do_fixup() {
        rm ${S}/PATCHES || true
}

addtask fixup after do_unpack before do_patch

NATIVE_INSTALL_WORKS = "1"
PR = "r4"

SRC_URI = "http://www.mpfr.org/${BP}/${BP}.tar.bz2 \
           file://p4.patch"

# fix build in thumb mode for armv4t
SRC_URI_append_thumb = " file://long-long-thumb.patch"

EXTRA_OECONF_append_virtclass-native = " --enable-static"

SRC_URI[md5sum] = "f45bac3584922c8004a10060ab1a8f9f"
SRC_URI[sha256sum] = "8f4e5f9c53536cb798a30455ac429b1f9fc75a0f8af32d6e0ac31ebf1024821f"
