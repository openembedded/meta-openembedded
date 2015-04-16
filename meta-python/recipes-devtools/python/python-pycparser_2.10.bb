SUMMARY = "Parser of the C language, written in pure Python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d29d3ce07825100c58ca57eea171ab65"

SRC_URI[md5sum] = "d87aed98c8a9f386aa56d365fe4d515f"
SRC_URI[sha256sum] = "957d98b661c0b64b580ab6f94b125e09b6714154ee51de40bca16d3f0076b86c"

inherit pypi

BBCLASSEXTEND = "native"
