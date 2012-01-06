DESCRIPTION = "kmod - handle kernel modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit autotools gitpkgv

PKGV = "${GITPKGVTAG}"
SRCREV = "b30a71b8e8b0a4b099f50696c310a9e370a50afa"
SRC_URI = "git://git.profusion.mobi/kmod.git"

S = "${WORKDIR}/git"

PACKAGES =+ "libkmod"
FILES_libkmod = "${libdir}/lib*${SOLIBS}"

