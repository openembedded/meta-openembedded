SUMMARY = "Ordered YAML loader and dumper for PyYAML."
HOMEPAGE = "https://github.com/Phynix/yamlloader"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6831ef36faa29329bce2420c5356f97e"

SRC_URI[sha256sum] = "c10ac1321a626b1ca8b09d3f3afe9855b818391c59992a76b65e4a2d95eac41b"

inherit pypi python_hatchling

DEPENDS += "\
    python3-hatch-vcs-native \
"

RDEPENDS:${PN}:class-target += "\
    python3-pyyaml \
"
