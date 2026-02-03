SUMMARY = "orjson is a fast, correct JSON library for Python"
HOMEPAGE = "https://pypi.org/project/orjson/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-MIT;md5=b377b220f43d747efdec40d69fcaa69d"

SRC_URI[sha256sum] = "0a54c72259f35299fd033042367df781c2f66d10252955ca1efb7db309b954cb"

CVE_PRODUCT = "orjson"

require ${BPN}-crates.inc

inherit pypi python_maturin cargo-update-recipe-crates

SRC_URI += " \
    file://0001-Guard-avx512-module-with-x86-target-cfg.patch \
    file://0002-Guard-x86-feature-detection-macro-in-pystrref-object.patch \
"
DEPENDS = "python3-maturin-native"

RDEPENDS:${PN} += "python3-maturin python3-mypy"

do_compile:prepend() {
    sed -i "/panic = \"abort\"/d" ${S}/Cargo.toml
}

BBCLASSEXTEND = "native nativesdk"
