DESCRIPTION = "LFTP is a sophisticated file transfer program with \
               command line interface. It supports FTP, HTTP, \
               FISH, SFTP, HTTPS and FTPS protocols"
HOMEPAGE = "http://lftp.yar.ru/"
SECTION = "console/network"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://lftp.yar.ru/ftp/lftp-${PV}.tar.bz2 \
           file://fix-gcc-6-conflicts-signbit.patch \
          "
SRC_URI[md5sum] = "12b1fcbf13f41e9cdb0903fc670fa1f1"
SRC_URI[sha256sum] = "c4159f056afee41866a6c2d639655bc351e6d3486bbe7758eaedb24f6a4239d5"

inherit autotools gettext pkgconfig

acpaths = "-I ./m4"

EXTRA_OECONF += "--with-modules"

PACKAGECONFIG ??= "openssl zlib gnutls readline expat"
PACKAGECONFIG[openssl] = "--with-openssl, --without-openssl, openssl"
PACKAGECONFIG[zlib] = "--with-zlib=${STAGING_INCDIR}/.., --without-zlib, zlib"
PACKAGECONFIG[gnutls] = "--with-gnutls, --without-gnutls, gnutls"
PACKAGECONFIG[readline] = "--with-readline=${STAGING_INCDIR}/.. --with-readline-inc=${STAGING_INCDIR} --with-readline-lib=-lreadline, --with-readline=no, readline"
PACKAGECONFIG[expat] = "--with-expat=${STAGING_INCDIR}/.. --with-expat-inc=${STAGING_INCDIR} --with-expat-lib=-lexpat, , expat"

do_install_append() {
	rm -rf ${D}${libdir}/charset.alias
}
FILES_${PN} += "${datadir}/icons/hicolor"
FILES_${PN}-dbg += "${libdir}/lftp/${PV}/.debug"
RDEPENDS_${PN} = "perl bash readline"
