SUMMARY = "Python bindings for interfacing with the Zstandard compression library"
DESCRIPTION = "This project provides Python bindings for interfacing with the \
               Zstandard compression library. A C extension and CFFI interface \
	       are provided."
HOMEPAGE = "https://github.com/indygreg/python-zstandard"
BUGTRACKER = "https://github.com/indygreg/python-zstandard/issues"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3ae87c50fd64b6f0942823686871e758"

DEPENDS = "python3-cffi"

RDEPENDS_${PN} = "\
    libzstd (>= 1.4.0) \
    python3-cffi \
"

inherit setuptools3 pypi

SRC_URI[sha256sum] = "fe3198b81c00032326342d973e526803f183f97aa9e9a98e3f897ebafe21178f"

# Because the pyproject.toml is still in development and it contains invalid
# requirements.
INSANE_SKIP += "pep517-backend"
