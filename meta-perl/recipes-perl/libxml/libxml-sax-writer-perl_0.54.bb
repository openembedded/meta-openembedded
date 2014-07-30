SUMMARY = "XML::SAX::Writer - SAX2 Writer"
DESCRIPTION = "\
XML::SAX::Writer helps to serialize SAX2 representations of XML documents to \
strings, files, and other flat representations. It handles charset encodings, \
XML escaping conventions, and so forth. It is still considered alpha, \
although it has been put to limited use in settings such as XML::LibXML and \
the AxKit XML Application Server. \
"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPLv1+"
HOMEPAGE = "http://search.cpan.org/dist/XML-SAX-Writer/"
DEPENDS += "libxml-filter-buffertext-perl-native"
RDEPENDS_${PN} += "libxml-filter-buffertext-perl"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/P/PE/PERIGRIN/XML-SAX-Writer-${PV}.tar.gz"
SRC_URI[md5sum] = "383139d76418a82b9800dc4f8b568891"
SRC_URI[sha256sum] = "a1b4d959aed8f8337523c4cef4b431e56e619c795dc6f99a868548952101cf3d"

LIC_FILES_CHKSUM = "file://README;beginline=45;endline=46;md5=d41d8cd98f00b204e9800998ecf8427e"

S = "${WORKDIR}/XML-SAX-Writer-${PV}"

inherit cpan

BBCLASSEXTEND = "native"
