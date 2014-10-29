SUMMARY = "LibYAML is a YAML 1.1 parser and emitter written in C."
DESCRIPTION = "LibYAML is a C library for parsing and emitting data in YAML 1.1, \
a human-readable data serialization format. "
HOMEPAGE = "http://pyyaml.org/wiki/LibYAML"
SECTION = "libs/devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6015f088759b10e0bc2bf64898d4ae17"

SRC_URI = "http://pyyaml.org/download/libyaml/yaml-${PV}.tar.gz \
           file://libyaml-CVE-2014-2525.patch \
          "

SRC_URI[md5sum] = "24f6093c1e840ca5df2eb09291a1dbf1"
SRC_URI[sha256sum] = "fa87ee8fb7b936ec04457bc044cd561155e1000a4d25029867752e543c2d3bef"

S = "${WORKDIR}/yaml-${PV}"

inherit autotools

BBCLASSEXTEND = "native"
