SUMMARY = "The Real First Universal Charset Detector. Open, modern and actively maintained alternative to Chardet."
HOMEPAGE = "https://github.com/ousret/charset_normalizer"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=48178f3fc1374ad7e830412f812bde05"

SRC_URI += "file://0001-pyproject.toml-relax-setuptools-upper-bound.patch"
SRC_URI[sha256sum] = "a250888753faa289e2997962fbb17c4960cbdb58575533ca6fceee68c224bdff"

DEPENDS += "python3-setuptools-scm-native python3-mypy-native"

inherit pypi python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN} += " \
	python3-core \
	python3-logging \
	python3-codecs \
	python3-json \
"

RDEPENDS:${PN}-ptest:append:libc-glibc  = " glibc-charmap-gb18030 glibc-charmaps"

do_install_ptest:append() {
    install -d ${D}${PTEST_PATH}/data
    install -m 0644 ${S}/data/* ${D}${PTEST_PATH}/data/
}

BBCLASSEXTEND = "native nativesdk"
