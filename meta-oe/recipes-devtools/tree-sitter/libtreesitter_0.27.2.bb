SUMMARY = "Tree-sitter parsing library"
DESCRIPTION = "Tree-sitter is an incremental parsing system for programming tools. \
This is the C runtime library libtree-sitter, which editors such as neovim and \
emacs use to load language grammars."
HOMEPAGE = "https://tree-sitter.github.io/tree-sitter/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b83e3e9ab94483903b3bc21d9a1d41ed"

SRC_URI = "git://github.com/tree-sitter/tree-sitter.git;protocol=https;branch=release-0.27;tag=v${PV}"
SRCREV = "3fb7be49145acfb07e33b61ed5689e2b6771b2e9"
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
    -DBUILD_SHARED_LIBS=ON \
    -DTREE_SITTER_FEATURE_WASM=OFF \
"

BBCLASSEXTEND = "native"
