SUMMARY = "The xmlschema library is an implementation of XML Schema for Python."
HOMEPAGE = "https://github.com/sissaschool/xmlschema"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=385fddea479acdec12ab77a938f68cd9"

SRC_URI[sha256sum] = "174c531dd869cd29bf2d1203603d9e619bddf168d6289725738914c96c80936e"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-elementpath \
    python3-modules \
"

BBCLASSEXTEND = "native nativesdk"
