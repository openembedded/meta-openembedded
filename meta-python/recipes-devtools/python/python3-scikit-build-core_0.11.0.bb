SUMMARY = "Build backend for CMake based projects"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b4e748e5f102e31c9390dcd6fa66f09"

PYPI_PACKAGE = "scikit_build_core"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

DEPENDS = "python3-hatch-vcs-native"

inherit pypi python_hatchling

SRC_URI += "file://0001-builder.py-Check-PYTHON_INCLUDE_DIR.patch"
SRC_URI[sha256sum] = "423d8b0885bf1942816c851f8ec7c1efc0a7ecad4f38ff43d5ba869a894b107b"

BBCLASSEXTEND = "native nativesdk"
