DESCRIPTION = "Ultralightweight JSON parser in ANSI C"
AUTHOR = "Dave Gamble"
HOMEPAGE = "https://github.com/DaveGamble/cJSON"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=218947f77e8cb8e2fa02918dc41c50d0"

SRC_URI = "git://github.com/DaveGamble/cJSON.git;branch=master;protocol=https"
SRCREV = "87d8f0961a01bf09bef98ff89bae9fdec42181ee"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE += "\
    -DENABLE_CJSON_UTILS=On \
    -DENABLE_CUSTOM_COMPILER_FLAGS=OFF \
    -DBUILD_SHARED_AND_STATIC_LIBS=On \
"

BBCLASSEXTEND = "native nativesdk"
