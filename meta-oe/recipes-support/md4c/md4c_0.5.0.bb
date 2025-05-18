SUMMARY = "Markdown for C"
DESCRIPTION = "MD4C is Markdown parser implementation in C."
HOMEPAGE = "http://github.com/mity/md4c"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=383f39920f391605af6e8e46e60e2378"


S = "${WORKDIR}/git"

SRC_URI = " \
        git://github.com/mity/md4c.git;protocol=https;branch=master \
"

SRCREV = "481230f4180cb0e0aad56da06de9327d5bacd570"

inherit cmake
