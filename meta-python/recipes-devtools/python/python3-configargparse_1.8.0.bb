SUMMARY = "A drop-in replacement for argparse that allows options to also be set via config files and/or environment variables."
HOMEPAGE = "https://github.com/bw2/ConfigArgParse"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=da746463714cc35999ed9a42339f2943"

SRC_URI[sha256sum] = "22a417f4d7b00149f0af82ef7c491f8ecc4b1d5454633fd319b386f5eb806f92"


inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"

PACKAGECONFIG ?= "yaml toml"
PACKAGECONFIG[yaml] = ",,,python3-pyyaml"
PACKAGECONFIG[toml] = ",,,python3-toml"

RDEPENDS:${PN} += "\
    python3-core \
    python3-shell \
    python3-json \
"

BBCLASSEXTEND = "native nativesdk"
