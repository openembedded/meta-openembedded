SUMMARY = "Python @deprecated decorator to deprecate old python classes, functions or methods."
HOMEPAGE = "https://deprecated.readthedocs.io/en/latest/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=656397bbedec0bd68b9163ff5e53032b"

SRC_URI[sha256sum] = "2f5a9fecc9961e26462846cf1e57f73a612b1d0b79c27dceeed8de647beadcee"

inherit pypi python_hatchling

RDEPENDS:${PN} += " \
	python3-wrapt \
"
