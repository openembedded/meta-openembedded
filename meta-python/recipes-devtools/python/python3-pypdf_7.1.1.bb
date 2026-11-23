SUMMARY = "A pure-python PDF library"
DESCRIPTION = "pypdf is a pure-python PDF library capable of splitting, \
merging, cropping, and transforming the pages of PDF files. It can also \
add custom data, viewing options, and passwords to PDF files."
HOMEPAGE = "https://github.com/py-pdf/pypdf"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=15692a75ec5daafc92ff9e5505a17cc5"

SRC_URI[sha256sum] = "2eff4e2fc5fb22a6d6711e6f1166cdc9ab5765d90cd0d77dfea3f4aa91a27b1d"

inherit pypi python_flit_core

RDEPENDS:${PN} += " \
    python3-codecs \
    python3-compression \
    python3-core \
    python3-crypt \
    python3-datetime \
    python3-io \
    python3-json \
    python3-logging \
    python3-math \
    python3-netclient \
    python3-numbers \
    python3-stringold \
    python3-xml \
"

BBCLASSEXTEND = "native nativesdk"
