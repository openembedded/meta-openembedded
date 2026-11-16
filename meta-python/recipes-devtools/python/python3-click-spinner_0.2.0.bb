SUMMARY = "Spinner for Click"
HOMEPAGE = "https://github.com/click-contrib/click-spinner"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=69a92e89bf5eb0912e714bad75dad065"

SRC_URI[sha256sum] = "01371550596bc18f1e1546bb2956745eed387235235e664bc96f8cc675b7d6e8"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "python3-json"
