SUMMARY = "Python parser and AST serializer for mypy"
DESCRIPTION = "Fast Python extension that parses Python files with vendored \
Ruff crates and serializes the AST in the binary format used by mypy's \
native parser."
HOMEPAGE = "https://github.com/mypyc/ast_serialize"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc12afd29b3bf645cc59f9ae0d9c6700 \
                    file://crates/LICENSE;md5=e679ca4f742cbfa29ab6a499529c2d39"

SRC_URI[sha256sum] = "401cc84e8888d4d76f2c8d296fa006ed1a5ff993b6aff90dd777c03973efd025"

require ${BPN}-crates.inc

inherit pypi python_maturin cargo-update-recipe-crates

BBCLASSEXTEND = "native nativesdk"
