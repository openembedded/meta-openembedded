SUMMARY = "Fast, Extensible Progress Meter"
HOMEPAGE = "https://tqdm.github.io/"
SECTION = "devel/python"

LICENSE = "MIT AND MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=9a9bed097dea538bf341c8623c8f8852"

SRC_URI[sha256sum] = "700c5e85dcd5f009dd6222588a29180a193a748247a5d855b4d67db93d79a53b"

CVE_PRODUCT = "tqdm"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
	python3-logging \
	python3-numbers \
"

BBCLASSEXTEND = "native nativesdk"
