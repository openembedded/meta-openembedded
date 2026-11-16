SUMMARY = "Generate JSON-RPC requests and parse responses in Python"
HOMEPAGE = "https://github.com/explodinglabs/jsonrpcclient"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=10f3d7679914df805c98fb351172e677"

SRCREV = "0de0775557275f93b660f05d9946f10f3ca66726"
SRC_URI = "git://github.com/explodinglabs/jsonrpcclient.git;branch=release/4.x;protocol=https"

inherit python_setuptools_build_meta


RDEPENDS:${PN} += "\
    python3-json \
    python3-math \
    python3-netclient \
"

BBCLASSEXTEND = "native nativesdk"
