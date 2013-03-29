SUMMARY = "LibYAML is a YAML parser and emitter written in C."
HOMEPAGE = "http://pyyaml.org/wiki/LibYAML"
SECTION = "libs/devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6015f088759b10e0bc2bf64898d4ae17"

SRC_URI = "http://pyyaml.org/download/libyaml/yaml-${PV}.tar.gz;name=libyaml"
SRC_URI[libyaml.md5sum] = "36c852831d02cf90508c29852361d01b"
SRC_URI[libyaml.sha256sum] = "7bf81554ae5ab2d9b6977da398ea789722e0db75b86bffdaeb4e66d961de6a37"

S = "${WORKDIR}/yaml-${PV}"

inherit autotools

BBCLASSEXTEND = "native"
