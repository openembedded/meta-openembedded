DESCRIPTION = "kmod - handle kernel modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

PR = "r2"

inherit autotools gitpkgv

PKGV = "${GITPKGVTAG}"
SRCREV = "80e54cb675d08f4f81b093a1919465ea86110a3b"
SRC_URI = "git://git.profusion.mobi/kmod.git \
           file://0001-man-disable-man-page-generation-because-we-don-t-hav.patch \
"

S = "${WORKDIR}/git"

do_configure_prepend() {
	gtkdocize --docdir ${S}/libkmod/docs || touch ${S}/libkmod/docs/gtk-doc.make
}

PACKAGES =+ "libkmod"
FILES_libkmod = "${libdir}/lib*${SOLIBS}"

