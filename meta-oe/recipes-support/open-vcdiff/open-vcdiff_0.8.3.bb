SUMMARY = "An encoder/decoder for the VCDIFF (RFC3284) format"
DESCRIPTION = "A library with a simple API is included, as well as a \
               command-line executable that can apply the encoder and \
               decoder to source, target, and delta files. \
               A slight variation from the draft standard is defined \
               to allow chunk-by-chunk decoding when only a partial \
               delta file window is available."
HOMEPAGE = "http://code.google.com/p/open-vcdiff/"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b2c8309843bc5297cfb4dc84f43f3ccc"

SRC_URI = "http://open-vcdiff.googlecode.com/files/${BPN}-${PV}.tar.gz \
           file://aclocal-amflags-fix.patch"
SRC_URI[md5sum] = "5f848e4916b407879f55e0ca74c3f6af"
SRC_URI[sha256sum] = "b7e47db78866082e9e8b5782f5491092c8414f0acc0440aea03b14c7d1d3c371"

inherit autotools
