SUMMARY = "Module for serializing and de-serializing Java objects."
DESCRIPTION = "python-javaobj is a python library that provides functions\
 for reading and writing (writing is WIP currently) Java objects serialized\
 or will be deserialized by ObjectOutputStream. This form of object\
 representation is a standard data interchange format in Java world."
HOMEPAGE = "https://github.com/tcalmant/python-javaobj"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://javaobj.py;beginline=21;endline=33;md5=8b504419ce78f8133b21b4d4a89ef7e9"

SRC_URI[md5sum] = "1e3f02b5c4c0853d653b7b8590d9ed2b"
SRC_URI[sha256sum] = "1dea6be0848c58013d92d8249ef6bc8688bbff7f13c78e02dbf08a449b4c0885"

inherit pypi

BBCLASSEXTEND = "native nativesdk"
