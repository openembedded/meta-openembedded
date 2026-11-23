SUMMARY = "Xdelta is a tool for differential compression"
DESCRIPTION = "Open-source binary diff, differential compression tools, \
               VCDIFF (RFC 3284) delta compression."
HOMEPAGE = "http://xdelta.org/"
SECTION = "console/utils"

LICENSE = "Apache-2.0 AND (CC0-1.0 OR Apache-2.0 OR Apache-2.0-with-LLVM-exception)"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cf96fa0d649f7c7b16616d95e7880a73 \
                    file://blake3/LICENSE_A2;md5=36f2d65d5af763138c43f12eab1ded63 \
                    file://blake3/LICENSE_A2LLVM;md5=9f15032a73646326104ebe0c70580f1a \
                    file://blake3/LICENSE_CC0;md5=65d3616852dbf7b1a6d4b53b00626032"

# BLAKE3 is needed for armor mode, upstream's CMake would fetch it during configure
SRC_URI = "git://github.com/jmacd/xdelta.git;branch=main;protocol=https;tag=v${PV};name=xdelta \
           git://github.com/BLAKE3-team/BLAKE3.git;branch=master;protocol=https;tag=1.8.5;name=blake3;destsuffix=${BP}/xdelta3/blake3 \
           "
SRCREV_xdelta = "5f8f5a553181c139d91b0289c009e5c276c5318c"
SRCREV_blake3 = "93a431c78a52d7ccf0f366f106467f5070e6075e"
SRCREV_FORMAT = "xdelta_blake3"
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>3(\.\d+)+)$"

S = "${UNPACKDIR}/${BP}/xdelta3"

inherit cmake

EXTRA_OECMAKE = "-DXD3_BUILD_TESTS=OFF \
                 -DXD3_ARMOR=ON \
                 -DFETCHCONTENT_SOURCE_DIR_BLAKE3=${S}/blake3 \
"

# Optional secondary compression
PACKAGECONFIG ??= ""
PACKAGECONFIG[lzma] = "-DXD3_LZMA_MODE=on,-DXD3_LZMA_MODE=off,xz"

BBCLASSEXTEND = "native nativesdk"
