require netcat.inc
DESCRIPTION = "OpenBSD Netcat"
HOMEPAGE = "http://ftp.debian.org"
LICENSE = "BSD-3-Clause"
PR = "r2"

DEPENDS += "glib-2.0"

SRC_URI = "${DEBIAN_MIRROR}/main/n/netcat-openbsd/netcat-openbsd_1.89.orig.tar.gz;name=netcat \
           ${DEBIAN_MIRROR}/main/n/netcat-openbsd/netcat-openbsd_1.89-4.diff.gz;name=netcat-patch"

SRC_URI[netcat.md5sum] = "7238ce15aae43069e96ba7faf03f153e"
SRC_URI[netcat.sha256sum] = "72e844dde8a2a7cba61971d493758dbea9ef0b164bccef15fd4a36490dc77f2b"
SRC_URI[netcat-patch.md5sum] = "00c65aa22243b76998174c733cbb64cc"
SRC_URI[netcat-patch.sha256sum] = "d5b6b2bd9788114bdcfbb71acad0263720b4fdc41529e287adf451b8610f84f1"

S = "${WORKDIR}/${BPN}-${PV}.orig"

do_configure[noexec] = "1"

do_compile() {
    cd ${S}
    while read line; do patch -p1 <debian/patches/$line; done <debian/patches/series
    pkgrel=4
    oe_runmake CFLAGS="$CFLAGS -DDEBIAN_VERSION=\"\\\"${pkgrel}\\\"\""
}

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/nc ${D}${bindir}/nc.${BPN}
}
ALTERNATIVE_PRIORITY = "50"
