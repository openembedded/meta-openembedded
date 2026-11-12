SUMMARY = "Markdown for C"
DESCRIPTION = "MD4C is Markdown parser implementation in C."
HOMEPAGE = "http://github.com/mity/md4c"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=f63468506b3d106579df14c9ffd34df8"



SRC_URI = " \
        git://github.com/mity/md4c.git;protocol=https;branch=master;tag=v${PV} \
"

SRCREV = "7fc1815a5eeba2af7d6120a76202bf59f3b6e6e4"

inherit cmake
