DESCRIPTION = "Liblinebreak is an implementation of the line breaking algorithm as described in Unicode 5.1.0 Standard Annex 14, Revision 22"
HOMEPAGE = "http://vimgadgets.sourceforge.net/liblinebreak/"
SECTION = "libs"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENCE;md5=f89a1ce02e2dba48c1c0a8e6038464c2"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/vimgadgets/liblinebreak/${PV}/liblinebreak-${PV}.tar.gz"
SRC_URI[sha256sum] = "dd8cba554d260cf686865cc53d0c6be98102a80f56f8152ca5e7307bf06a66b8"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/vimgadgets/files/liblinebreak/"
UPSTREAM_CHECK_REGEX = "${BPN}/(?P<pver>\d+(\.\d+)+)"

inherit autotools
