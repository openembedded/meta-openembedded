DESCRIPTION = "Incremental is a small library that versions your Python projects"
HOMEPAGE = "https://github.com/twisted/incremental"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6ca9b07f08e2c72d48c74d363d1e0e15"

SRC_URI[sha256sum] = "912feeb5e0f7e0188e6f42241d2f450002e11bbc0937c65865045854c24c0bd0"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-twisted-core \
    python3-click \
"

# -native is needed to build python[3]-twisted, however, we need to take steps to
# prevent a circular dependency. The build apparently does not use the part of
# python-incremental which uses python-twisted, so this hack is OK.
RDEPENDS:python3-incremental-native:remove = "python3-twisted-core-native"
BBCLASSEXTEND = "native"
