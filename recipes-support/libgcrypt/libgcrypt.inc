DESCRIPTION = "A general purpose cryptographic library based on the code from GnuPG"
HOMEPAGE = "http://directory.fsf.org/project/libgcrypt/"
BUGTRACKER = "https://bugs.g10code.com/gnupg/index"
SECTION = "libs"
PRIORITY = "optional"

BBCLASSEXTEND = "native"

# helper program gcryptrnd and getrandom are under GPL, rest LGPL
LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LIB;md5=bbb461211a33b134d42ed5ee802b37ff"

DEPENDS = "libgpg-error"

SRC_URI = "ftp://ftp.gnupg.org/gcrypt/libgcrypt/libgcrypt-${PV}.tar.gz \
           file://add-pkgconfig-support.patch"

inherit autotools binconfig pkgconfig

EXTRA_OECONF = "--without-pth --disable-asm --with-capabilities"

# libgcrypt.pc is added locally and thus installed here
do_install_append() {
	install -d ${D}/${libdir}/pkgconfig
	install -m 0644 ${S}/src/libgcrypt.pc ${D}/${libdir}/pkgconfig/
}

ARM_INSTRUCTION_SET = "arm"

# move libgcrypt-config into -dev package
FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${bindir} ${libdir}/pkgconfig/*.pc"
