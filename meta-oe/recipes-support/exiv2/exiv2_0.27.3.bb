SUMMARY = "Exif, Iptc and XMP metadata manipulation library and tools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=625f055f41728f84a8d7938acc35bdc2"

DEPENDS = "zlib expat"

SRC_URI = "https://github.com/Exiv2/${BPN}/releases/download/v${PV}/${BP}-Source.tar.gz \
           file://0001-Use-compiler-fcf-protection-only-if-compiler-arch-su.patch \
           file://CVE-2021-29457.patch \
           file://CVE-2021-29458.patch \
           file://CVE-2021-29463.patch \
           file://CVE-2021-29464.patch \
           file://CVE-2021-29470.patch \
           file://CVE-2021-29473.patch \
           file://CVE-2021-3482.patch \
           file://CVE-2021-29623.patch \
           file://CVE-2021-32617.patch \
           file://CVE-2021-32815.patch \
           file://CVE-2021-34334-1.patch \
           file://CVE-2021-34334-2.patch \
           file://CVE-2021-34334-3.patch \
           file://CVE-2021-34334-4.patch \
           file://CVE-2021-34335-1.patch \
           file://CVE-2021-34335-2.patch \
           "
SRC_URI[sha256sum] = "a79f5613812aa21755d578a297874fb59a85101e793edc64ec2c6bd994e3e778"

# Once patch is obsolete (project should be aware due to PRs), dos2unix can be removed either
inherit dos2unix

S = "${WORKDIR}/${BPN}-${PV}-Source"

inherit cmake gettext
