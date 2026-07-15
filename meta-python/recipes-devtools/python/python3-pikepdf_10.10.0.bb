SUMMARY = "Read and write PDFs with Python, powered by qpdf"
HOMEPAGE = "https://github.com/pikepdf/pikepdf"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI[sha256sum] = "9f134806b2fe608ccb21379a664ddcefeac3f6944100d343b350299d3c69754e"

SRC_URI += "file://0001-pyproject.toml-Do-not-strip.patch"

inherit pypi python_setuptools_build_meta

# pikepdf uses the C++20 language but no C++20 named modules. CMake's module
# dependency scanning (clang-scan-deps) does not work in the cross environment,
# so turn it off.
export CMAKE_ARGS = "-DCMAKE_CXX_SCAN_FOR_MODULES=OFF"

PYPI_PACKAGE = "pikepdf"

CVE_PRODUCT = "pikepdf"

DEPENDS += " \
	python3-pybind11-native \
        python3-nanobind-native \
	python3-scikit-build-core-native \
	ninja-native \
	qpdf \
"

RDEPENDS:${PN} += " \
	python3-pillow \
	python3-lxml \
"

BBCLASSEXTEND = "native nativesdk"
