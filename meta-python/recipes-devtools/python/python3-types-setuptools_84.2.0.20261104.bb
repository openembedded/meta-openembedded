SUMMARY = "Typing stubs for setuptools"
HOMEPAGE = "https://github.com/python/typeshed"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=320eb2c24b8d2e890b6800000ce9352c"

inherit pypi python_setuptools_build_meta


SRC_URI[sha256sum] = "1ec758bbeddf88d52f963fe663beabc2117b6aae73cf3d2902d7dd0df81bd76e"

BBCLASSEXTEND = "native"
