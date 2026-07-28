DESCRIPTION = "An extremely fast Python linter and code formatter, written in Rust."
SUMMARY = "An extremely fast Python linter and code formatter"
HOMEPAGE = "https://docs.astral.sh/ruff/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e679ca4f742cbfa29ab6a499529c2d39"

DEPENDS += "python3-maturin-native"

SRC_URI[sha256sum] = "e460aafd5495ec89efaa6ced2e4a9a581116451e1c88b9d37ef497e0f8e93982"

inherit pypi python_setuptools3_rust cargo-update-recipe-crates

CARGO_SRC_DIR = "rust"

require ${BPN}-crates.inc

INSANE_SKIP:${PN} = "already-stripped"

BBCLASSEXTEND = "native nativesdk"
