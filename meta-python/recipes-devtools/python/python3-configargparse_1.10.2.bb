SUMMARY = "A drop-in replacement for argparse that allows options to also be set via config files and/or environment variables."
HOMEPAGE = "https://github.com/bw2/ConfigArgParse"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=da746463714cc35999ed9a42339f2943"

SRC_URI[sha256sum] = "2deb0b3d6d46ff6e3218fcd350127507c5e3c11e21c8072a9fb24531dfc9bd4a"


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
