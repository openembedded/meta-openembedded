SUMMARY = "A list-like structure which implements collections.abc.MutableSequence, and which can be made immutable."
HOMEPAGE = "https://github.com/aio-libs/frozenlist"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cf056e8e7a0a5477451af18b7b5aa98c"

SRC_URI[sha256sum] = "effc641518696471cf4962e8e32050133bc1f7b2851ae8fd0cb8797dd70dc202"

inherit pypi python_setuptools_build_meta cython

DEPENDS += " \
    python3-expandvars-native \
"

BBCLASSEXTEND = "native nativesdk"
