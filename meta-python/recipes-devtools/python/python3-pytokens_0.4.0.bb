SUMMARY = "A Fast, spec compliant Python 3.14+ tokenizer that runs on older Pythons."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=627dc9a75d5dcc4759b26bacf13a4c46"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "6b0b03e6ea7c9f9d47c5c61164b69ad30f4f0d70a5d9fe7eac4d19f24f77af2d"

DEPENDS += "python3-mypy-native"
