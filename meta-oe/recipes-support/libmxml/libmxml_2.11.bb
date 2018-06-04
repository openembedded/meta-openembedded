DESCRIPTION = "Tiny XML Library"
LICENSE = "Mini-XML-License"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6ba38606d63bb042c5d8cfee182e120"
HOMEPAGE = "https://www.msweet.org/mxml/"
BUGTRACKER = "https://github.com/michaelrsweet/mxml/issues"

SRC_URI = " \
    https://github.com/michaelrsweet/mxml/archive/v${PV}.tar.gz;downloadfilename=${BP}.tar.gz \
    file://0001-remove-rpath-from-configure.in-and-configure.patch \
    file://0002-link-libmxml-with-pthread.patch \
    file://0003-add-missing-LDFLAGS-to-Makefile.patch \
    file://0004-mxml-string-compile-headers.patch \
    file://0005-several-autoheader-define-fixes.patch \
    file://0006-several-compile-fixes.patch \
    file://0007-Makefile-require-libmxml.a-for-its-installation.patch \
"

SRC_URI[md5sum] = "b9b49af414a2f0a8b13636d9635c3b6a"
SRC_URI[sha256sum] = "7d3dfe661e50908fe41aef9b97ba6f7f158cab5208515c6be9f5bc9daf032329"

inherit autotools-brokensep gettext

S = "${WORKDIR}/mxml-${PV}"

EXTRA_OEMAKE = "DSTROOT=${D}"

PACKAGES += " ${PN}-bin "
FILES_${PN} = "${libdir}/*"
FILES_${PN}-bin = "${bindir}/*"
