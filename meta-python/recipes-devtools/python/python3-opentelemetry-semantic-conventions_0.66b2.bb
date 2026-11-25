SUMMARY = "OpenTelemetry Semantic Conventions"
DESCRIPTION = "Semantic conventions for OpenTelemetry, an open source \
observability framework for cloud native software."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[sha256sum] = "7da5f1a5dc4383eb66711d4d3d3e71824d9403ac1559fea021cc103c1a4c4976"

inherit pypi python_hatchling

UPSTREAM_CHECK_REGEX = "(?i)opentelemetry[_-]semantic[_-]conventions-(?P<pver>\d+(\.\d+)+b\d+)\.tar\.gz"

RDEPENDS:${PN} += " \
    python3-opentelemetry-api \
    python3-typing-extensions \
"
