SUMMARY = "Python humanize utilities"
HOMEPAGE = "http://github.com/jmoiron/humanize"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE;md5=4ecc42519e84f6f3e23529464df7bd1d"

SRC_URI[sha256sum] = "1338ba97415c96556758a6e2f65977ed406dddf4620d4c6db9bbdfd07f0f1232"

inherit pypi python_hatchling

DEPENDS += "\
    python3-setuptools-scm-native \
    python3-hatch-vcs-native \
"

RDEPENDS:${PN} += "\
    python3-datetime \
    python3-setuptools \
"

BBCLASSEXTEND = "native nativesdk"
