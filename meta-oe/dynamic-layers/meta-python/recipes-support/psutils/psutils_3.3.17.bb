SUMMARY = "Utilities for manipulating PDF and PostScript documents"
DESCRIPTION = "PSUtils is a collection of utilities for manipulating PDF \
and PostScript documents: page selection and rearrangement, arrangement \
into signatures for booklet printing, and page merging for n-up printing."
HOMEPAGE = "https://github.com/rrthomas/psutils"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=87212b5f1ae096371049a12f80034f32"

SRC_URI = "https://github.com/rrthomas/psutils/releases/download/v${PV}/psutils-${PV}.tar.gz"
SRC_URI[sha256sum] = "3ff76cc9f9fb535ec553d1591de56d6dbaa0d9f4df86778f6a49731ebd764cdf"

UPSTREAM_CHECK_URI = "https://github.com/rrthomas/psutils/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/v(?P<pver>\d+(\.\d+)+)"

inherit python_setuptools_build_meta

# setup.py generates the man pages from the argparse parsers and runs 'paper'
DEPENDS += " \
    libpaper-native \
    python3-argparse-manpage-native \
    python3-puremagic-native \
    python3-pypdf-native \
"

# setup.py runs 'paper', which needs a default size and finds no paperspecs in a relocated sysroot
export PAPERSIZE = "a4"
export XDG_CONFIG_HOME = "${STAGING_ETCDIR_NATIVE}"

RDEPENDS:${PN} += " \
    libpaper \
    python3-core \
    python3-io \
    python3-puremagic \
    python3-pypdf \
"

BBCLASSEXTEND = "native"
