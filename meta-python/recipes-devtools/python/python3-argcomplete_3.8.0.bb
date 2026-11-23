SUMMARY = "Argcomplete provides easy, extensible command line tab completion of arguments for your Python script."
HOMEPAGE = "https://github.com/kislyuk/argcomplete"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=2ee41112a44fe7014dce33e26468ba93"

SRC_URI[sha256sum] = "0114d634013e11ac92cc329b3a7d886c3506e98ddc7bb23c99b1782aacc8b8bd"


inherit pypi python_hatchling

DEPENDS += "python3-hatch-vcs-native"

RDEPENDS:${PN} += "\
    python3-core \
    python3-io \
"

BBCLASSEXTEND = "native nativesdk"

