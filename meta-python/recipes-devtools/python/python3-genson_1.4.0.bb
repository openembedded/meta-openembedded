DESCRIPTION = "GenSON is a powerful, user-friendly JSON Schema generator built in Python."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6c30d55dbfb3a28d420d918534adf6b"

SRC_URI[sha256sum] = "bc7f1c1bae87a21ca44d81149aec95a3f4468d676de9b8b08caa064f3c50b3da"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native nativesdk"
