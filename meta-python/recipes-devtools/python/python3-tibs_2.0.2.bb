SUMMARY = "A sleek Python library for binary data"
DESCRIPTION = "tibs provides immutable (Tibs) and mutable (Mutibs) binary \
sequences with a Rust core. It is the engine used by bitstring."
HOMEPAGE = "https://github.com/scott-griffiths/tibs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8e9baae83130ec8cef3f37faee22781"

SRC_URI[sha256sum] = "63a7b87e262c558ccc16be67d34fe60620e39b49bf644ac65e71c4553173ac8f"

require ${BPN}-crates.inc

DEPENDS += "zstd"

inherit pypi python_maturin cargo-update-recipe-crates pkgconfig

BBCLASSEXTEND = "native nativesdk"
