SUMMARY = "Verify certificates using native system trust stores"
DESCRIPTION = "Truststore is a library which exposes native system \
    certificate stores (ie \"trust stores\") through an ssl.SSLContext-like \
    API."
HOMEPAGE = "https://github.com/sethmlarson/truststore"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=74420fc3965c4558a4a1529e63c2867f"

SRC_URI[sha256sum] = "30d36967ccaded5cbb38d602c433f53600036c79d502f4533a49b60a03bbefcd"

inherit pypi python_flit_core

RDEPENDS:${PN} += "python3-io"
