SUMMARY = "Perl interface to the libxml2 library"
DESCRIPTION = "This module is an interface to libxml2, providing XML and HTML parsers \ 
with DOM, SAX and XMLReader interfaces, a large subset of DOM Layer 3 \
interface and a XML::XPath-like interface to XPath API of libxml2. \
The module is split into several packages which are not described in this \
section; unless stated otherwise, you only need to use XML::LibXML; in \
your programs."

HOMEPAGE = "http://search.cpan.org/dist/XML-LibXML-1.99/"
SECTION = "libs"
LICENSE = "Artistic-1.0|GPLv1+"
DEPENDS += "libxml2 \
        libxml-sax-perl-native \
        zlib \
"
RDEPENDS_${PN} += "libxml2 \
        libxml-sax-perl \
        libxml-sax-base-perl \
        zlib \
"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/S/SH/SHLOMIF/XML-LibXML-${PV}.tar.gz;name=libxml \
	file://disable-libxml2-check.patch \
	file://fix-CATALOG-conditional-compile.patch \
	file://using-DOCB-conditional.patch \
"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=75e021e35a906347f46c9ff163653e2a \
			file://LICENSE;md5=97871bde150daeb5e61ad95137ff2446"
SRC_URI[libxml.md5sum] = "a53a743bf053a0cb4afb41513fb8a684"
SRC_URI[libxml.sha256sum] = "b154f2dad3033b30d22ac81b8985b69ad35450b0c552db394cd03bb36845812a"

S = "${WORKDIR}/XML-LibXML-${PV}"

inherit cpan

EXTRA_CPANFLAGS = "INC=-I${STAGING_INCDIR}/libxml2 LIBS=-L${STAGING_LIBDIR}"

BBCLASSEXTEND = "native"

CFLAGS += " -D_GNU_SOURCE "
BUILD_CFLAGS += " -D_GNU_SOURCE "

do_configure_prepend() {
	rm -rf ${S}/.pc/*
}

FILES_${PN}-dbg =+ "${libdir}/perl/vendor_perl/*/auto/XML/LibXML/.debug/"
