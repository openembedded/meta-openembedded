SUMMARY = "UBI utils statically compiled against klibc"
DESCRIPTION = "Small sized tools from mtd-utils for use with initramfs."
SECTION = "base"
DEPENDS = "zlib lzo e2fsprogs util-linux"
HOMEPAGE = "http://www.linux-mtd.infradead.org/"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://include/common.h;beginline=1;endline=17;md5=ba05b07912a44ea2bf81ce409380049c"


inherit klibc

SRC_URI = "git://git.infradead.org/mtd-utils.git;tag=ca39eb1d98e736109c64ff9c1aa2a6ecca222d8f \
            file://Makefile.patch \
            file://common.mk.patch \
            file://libubi.c.patch \
            file://libiniparser.c.patch \
            file://ubiformat.c.patch \
"

S = "${WORKDIR}/git/"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} -I${S}include -DWITHOUT_XATTR' 'BUILDDIR=${S}'"

do_install () {

    install -d ${D}${sbindir}
    oe_runmake DESTDIR="${D}" install

}

PACKAGES = "ubi-utils-klibc-dbg"

PACKAGES =+ "ubinfo-klibc ubiattach-klibc ubidetach-klibc mtdinfo-klibc ubiupdatevol-klibc \
             ubimkvol-klibc ubirename-klibc ubirmvol-klibc ubirsvol-klibc \
             ubinize-klibc ubiformat-klibc ubicrc32-klibc"

FILES_ubinfo-klibc = "${sbindir}/ubinfo"
FILES_ubiattach-klibc = "${sbindir}/ubiattach"
FILES_ubidetach-klibc = "${sbindir}/ubidetach"
FILES_mtdinfo-klibc = "${sbindir}/mtdinfo"
FILES_ubiupdatevol-klibc = "${sbindir}/ubiupdatevol"
FILES_ubimkvol-klibc = "${sbindir}/ubimkvol"
FILES_ubirename-klibc = "${sbindir}/ubirename"
FILES_ubirmvol-klibc = "${sbindir}/ubirmvol"
FILES_ubirsvol-klibc = "${sbindir}/ubirsvol"
FILES_ubinize-klibc = "${sbindir}/ubinize"
FILES_ubiformat-klibc = "${sbindir}/ubiformat"
FILES_ubicrc32-klibc = "${sbindir}/ubicrc32"
