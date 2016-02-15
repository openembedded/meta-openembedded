SUMMARY = "MessagePack (de)serializer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=cd9523181d9d4fbf7ffca52eaa2a5751"

PR = "r0"

SRC_URI[md5sum] = "3b82bc542d5599896695512e7c32f42d"
SRC_URI[sha256sum] = "a07cd6615a6bf38cfa2f010b121c7e77b74a3e7b971ef3e475c3d33308014cbb"

PYPI_PACKAGE = "msgpack-python"
inherit pypi setuptools
