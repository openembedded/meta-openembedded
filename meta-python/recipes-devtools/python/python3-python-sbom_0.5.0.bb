SUMMARY = "SBOMs for Python distributions: write PEP 770 CycloneDX SBOMs into wheels"
HOMEPAGE = "https://github.com/tox-dev/python-sbom"
LICENSE = "MIT AND Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=11610a9d8fd95649cf8159be12b98cb7 \
    file://src/python_sbom/_validate/schemas/LICENSE;md5=68aefe2439be54463bf213af4ca4d678 \
"

SRC_URI[sha256sum] = "fc7b2c46fa40f52e587ac59cada775e83dcac231fafa90b7a71c3554527ddfb6"

PYPI_PACKAGE = "python-sbom"

inherit pypi python_hatchling

DEPENDS += " \
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-vcs-native \
    python3-installer-native \
    python3-packageurl-python-native \
    python3-packaging-native \
    python3-zipremove-native \
"

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-compression \
    python3-core \
    python3-crypt \
    python3-datetime \
    python3-email \
    python3-io \
    python3-json \
    python3-netclient \
    python3-installer \
    python3-packageurl-python \
    python3-packaging \
    python3-tomllib \
    python3-zipremove \
"

BBCLASSEXTEND = "native nativesdk"
