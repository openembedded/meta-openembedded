SUMMARY = "Cucumber Expressions - a simpler alternative to Regular Expressions"
DESCRIPTION = "Provides the cucumber-expression parser and matcher used by cucumber/behave"
HOMEPAGE = "https://github.com/cucumber/cucumber-expressions"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b2bb1048cefa23c375b8ce5f13313ea"

SRC_URI[sha256sum] = "f526dda8f3af10642afd5293ffdbe7b471d3c509633b88af03d05f62b387d294"

inherit pypi python_uv_build

RDEPENDS:${PN} += " \
    python3-core \
    python3-numbers \
"

BBCLASSEXTEND = "native nativesdk"
