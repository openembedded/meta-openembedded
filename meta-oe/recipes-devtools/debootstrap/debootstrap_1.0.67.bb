DESCRIPTION = "Install a Debian system into a subdirectory"
HOMEPAGE = "https://wiki.debian.org/Debootstrap"
SECTION = "devel"
PRIORITY = "optional"
LICENSE = "debootstrap-custom-license"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=1e68ced6e1689d4cd9dac75ff5225608"

inherit autotools pkgconfig

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI  = "\
    http://http.debian.net/debian/pool/main/d/debootstrap/debootstrap_1.0.67.tar.gz \
    file://devices.tar.gz;unpack=0 \
"

SRC_URI[md5sum] = "eacabfe2e45415af60b1d74c3a23418a"
SRC_URI[sha256sum] = "0a12e0a2bbff185d47711a716b1f2734856100e8784361203e834fed0cffa51b"

S = "${WORKDIR}/${PN}-${PV}"

do_install_prepend() {
    cp ${WORKDIR}/devices.tar.gz ${S}
    cd "${S}"
}
