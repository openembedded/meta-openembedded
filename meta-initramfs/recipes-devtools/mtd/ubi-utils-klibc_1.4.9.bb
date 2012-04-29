DESCRIPTION = "Tools for managing memory technology devices."
SECTION = "base"
DEPENDS = "zlib lzo e2fsprogs util-linux"
HOMEPAGE = "http://www.linux-mtd.infradead.org/"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://include/common.h;beginline=1;endline=17;md5=ba05b07912a44ea2bf81ce409380049c"


PR = "r0"

inherit klibc

# ubicrc32 needs 'feof' (in klibc_2.0)
# ubinize needs 'atof'
# ubiformat needs 'scanf'equivalent for klibc

SRC_URI = "git://git.infradead.org/mtd-utils.git;protocol=git;tag=995cfe51b0a3cf32f381c140bf72b21bf91cef1b \
            file://Makefile.patch \
            file://common.mk.patch \
            file://libmtd.c.patch \
            file://libubi.c.patch \
            "

S = "${WORKDIR}/git/"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} -I${S}include -I${S}ubi-utils/include -DWITHOUT_XATTR' 'BUILDDIR=${S}'"

do_install () {
    install -d ${D}${sbindir}
    install -m 755 ubi-utils/ubinfo ${D}${sbindir}/ubinfo
    install -m 755 ubi-utils/ubiattach ${D}${sbindir}/ubiattach
    install -m 755 ubi-utils/ubidetach ${D}${sbindir}/ubidetach
    install -m 755 ubi-utils/mtdinfo ${D}${sbindir}/mtdinfo
    install -m 755 ubi-utils/ubiupdatevol ${D}${sbindir}/ubiupdatevol
    install -m 755 ubi-utils/ubimkvol ${D}${sbindir}/ubimkvol
    install -m 755 ubi-utils/ubirename ${D}${sbindir}/ubirename
    install -m 755 ubi-utils/ubirmvol ${D}${sbindir}/ubirmvol
    install -m 755 ubi-utils/ubirsvol ${D}${sbindir}/ubirsvol
}

PACKAGES =+ "ubinfo-klibc ubiattach-klibc ubidetach-klibc mtdinfo-klibc ubiupdatevol-klibc \
             ubimkvol-klibc ubirename-klibc ubirmvol-klibc ubirsvol-klibc"

FILES_ubinfo-klibc = "${sbindir}/ubinfo"
FILES_ubiattach-klibc = "${sbindir}/ubiattach"
FILES_ubidetach-klibc = "${sbindir}/ubidetach"
FILES_mtdinfo-klibc = "${sbindir}/mtdinfo"
FILES_ubiupdatevol-klibc = "${sbindir}/ubiupdatevol"
FILES_ubimkvol-klibc = "${sbindir}/ubimkvol"
FILES_ubirename-klibc = "${sbindir}/ubirename"
FILES_ubirmvol-klibc = "${sbindir}/ubirmvol"
FILES_ubirsvol-klibc = "${sbindir}/ubirsvol"
