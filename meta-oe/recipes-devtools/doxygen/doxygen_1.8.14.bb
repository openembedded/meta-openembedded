DESCRIPTION = "Doxygen is the de facto standard tool for generating documentation from annotated C++ sources."
HOMEPAGE = "http://www.doxygen.org/"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit cmake python3native

DEPENDS = "flex-native bison-native"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.src.tar.gz \
           file://0001-build-don-t-look-for-Iconv.patch"

SRC_URI[md5sum] = "41d8821133e8d8104280030553e2b42b"
SRC_URI[sha256sum] = "d1757e02755ef6f56fd45f1f4398598b920381948d6fcfa58f5ca6aa56f59d4d"

BBCLASSEXTEND = "native"
