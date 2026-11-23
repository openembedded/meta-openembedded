SUMMARY = "Optional static typing for Python"
HOMEPAGE = "https://github.com/python/mypy"
LICENSE = "MIT AND Python-2.0 AND BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=572a4af76db6990f02b2d82f387ea10f"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "63b590965691e540a335fa74dadf31a7069142f6b72e234689bbd244edf55623"

BBCLASSEXTEND = "native nativesdk"

DEPENDS += " \
    python3-ast-serialize-native \
    python3-librt-native \
    python3-mypy-extensions-native \
    python3-types-psutil-native \
    python3-types-setuptools-native \
    python3-typing-extensions-native \
    python3-pathspec-native \
"

RDEPENDS:${PN} += " \
    python3-ast-serialize \
    python3-librt \
    python3-modules \
    python3-mypy-extensions \
    python3-pathspec \
    python3-typing-extensions \
"
