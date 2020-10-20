SUMMARY = "Alternative regular expression module, to replace re."
HOMEPAGE = "https://bitbucket.org/mrabarnett/mrab-regex/src"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=f0a3e4a2554ebb89c046c93d45d8e4bc"

inherit pypi setuptools3

SRC_URI[md5sum] = "ef9f43ab182949c79b2c8f869788f8aa"
SRC_URI[sha256sum] = "d25f5cca0f3af6d425c9496953445bf5b288bb5b71afc2b8308ad194b714c159"

BBCLASSEXTEND = "native nativesdk"
