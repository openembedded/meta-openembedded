SUMMARY = "NFS client library"
HOMEPAGE = "https://github.com/sahlberg/libnfs"
LICENSE = "BSD-2-Clause AND GPL-3.0-only AND LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=825301ba17efc9d188ee0abd4b924ada"

SRC_URI = "git://github.com/sahlberg/libnfs.git;protocol=https;branch=master;tag=libnfs-${PV} \
           file://0001-cmake-Do-not-write-absolute-library-paths-into-libnf.patch \
           "
SRCREV = "5c48d277783073a1be5aa3ce8ff1ca9a690c20a0"

UPSTREAM_CHECK_GITTAGREGEX = "^libnfs-(?P<pver>\d+(\.\d+)+)$"

inherit cmake pkgconfig

PACKAGECONFIG ??= "tls"
PACKAGECONFIG[krb5] = "-DENABLE_KRB5=ON,-DENABLE_KRB5=OFF,krb5"
PACKAGECONFIG[rdma] = "-DENABLE_RDMA=ON,-DENABLE_RDMA=OFF,rdma-core"
PACKAGECONFIG[tls] = "-DENABLE_TLS=ON,-DENABLE_TLS=OFF,gnutls"

do_install:append() {
	rm -f ${D}${libdir}/cmake/libnfs/libnfs-config.cmake
}
