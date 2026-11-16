DESCRIPTION = "An extremely fast Python linter and code formatter, written in Rust."
SUMMARY = "An extremely fast Python linter and code formatter"
HOMEPAGE = "https://docs.astral.sh/ruff/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e679ca4f742cbfa29ab6a499529c2d39"

SRC_URI += "file://0001-ruff-Use-portable-atomic-for-AtomicU64-on-targets-wi.patch"

SRC_URI[sha256sum] = "625e57d80cd43352d12ea81690f3eac46cddc8c0f35026d137fb0f157f94592e"

inherit pypi python_maturin cargo-update-recipe-crates

CARGO_SRC_DIR = "rust"

require ${BPN}-crates.inc

INSANE_SKIP:${PN} = "already-stripped"

BBCLASSEXTEND = "native nativesdk"
