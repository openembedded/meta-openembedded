SUMMARY = "Module for serializing and de-serializing Java objects."
DESCRIPTION = "python-javaobj is a python library that provides functions\
 for reading and writing (writing is WIP currently) Java objects serialized\
 or will be deserialized by ObjectOutputStream. This form of object\
 representation is a standard data interchange format in Java world."
HOMEPAGE = "https://github.com/tcalmant/python-javaobj"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[sha256sum] = "088ff086c6aa2912f342754c43b0ca3a504fb869bb5e6f13395131e87744ef3c"

inherit pypi python_setuptools_build_meta


BBCLASSEXTEND = "native nativesdk"

RDEPENDS:${PN} += " \
    python3-compression \
    python3-logging \
"
