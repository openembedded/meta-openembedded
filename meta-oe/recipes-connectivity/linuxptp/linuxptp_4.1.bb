SUMMARY = "linuxptp package for linux"
DESCRIPTION = "Precision Time Protocol (PTP) according to IEEE standard 1588 \
for Linux"
HOMEPAGE = "http://linuxptp.sourceforge.net/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

LINUXPTP_SRC_URI = "http://sourceforge.net/projects/linuxptp"

SRC_URI = "${LINUXPTP_SRC_URI}/files/v4.1/linuxptp-${PV}.tgz \
           file://build-Allow-CC-and-prefix-to-be-overriden.patch \
           file://Use-cross-cpp-in-incdefs.patch \
           file://0001-include-string.h-for-strncpy.patch \
           "

SRC_URI[md5sum] = "1db8699fc155965341759be5e5aad938"
SRC_URI[sha256sum] = "e1743d44f8208897e30895da3579e670ff919b914feb4b5a949f3e421ddde535"

UPSTREAM_CHECK_URI = "${LINUXPTP_SRC_URI}/files/"
UPSTREAM_CHECK_REGEX = "(?P<pver>\d+(\.\d+)+)/"

EXTRA_OEMAKE = "ARCH=${TARGET_ARCH} EXTRA_CFLAGS='${CFLAGS}' mandir=${mandir}"

export KBUILD_OUTPUT="${RECIPE_SYSROOT}"

do_install() {
    oe_runmake install DESTDIR=${D} prefix=${prefix}

    # Install example configs from source tree
    install -d ${D}${docdir}/${PN}
    cp -R --no-dereference --preserve=mode,links ${S}/configs \
        ${D}${docdir}/${PN}
}

PACKAGES =+ "${PN}-configs"

FILES:${PN}-configs += "${docdir}"
