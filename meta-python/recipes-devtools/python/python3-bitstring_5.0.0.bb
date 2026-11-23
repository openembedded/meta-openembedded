SUMMARY = "Simple construction, analysis and modification of binary data."
HOMEPAGE = "https://github.com/scott-griffiths/bitstring"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=963a24c175e658fbf16a764135121ffa"

SRC_URI[sha256sum] = "01d876b6e7bbd912bfa3ae1d028ed419ed2193245fe2337f21d30afaa391c053"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} = "\
    python3-core \
    python3-io \
    python3-mmap \
    python3-numbers \
    python3-tibs \
"

BBCLASSEXTEND = "native nativesdk"
