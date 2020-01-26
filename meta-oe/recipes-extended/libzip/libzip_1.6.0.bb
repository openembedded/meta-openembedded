DESCRIPTION = "libzip is a C library for reading, creating, and modifying zip archives."
HOMEPAGE = "https://libzip.org/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e33bb117aa55f9aad3d28e29256f9919"

DEPENDS = "zlib bzip2"

PACKAGECONFIG[ssl] = "-DENABLE_OPENSSL=ON,-DENABLE_OPENSSL=OFF,openssl"
PACKAGECONFIG[lzma] = "-DENABLE_LZMA=ON,-DENABLE_LZMA=OFF,xz"

PACKAGECONFIG ?= "ssl lzma"

inherit cmake

SRC_URI = "https://libzip.org/download/libzip-${PV}.tar.xz"

SRC_URI[md5sum] = "c0245c3e68e472d75d9f8572e24eb5d0"
SRC_URI[sha256sum] = "a6cd804b4b3cffd997bd563fec693eb7eb052c225909a98a620689c2befb699b"
