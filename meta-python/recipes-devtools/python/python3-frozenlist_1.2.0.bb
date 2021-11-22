SUMMARY = "A list-like structure which implements collections.abc.MutableSequence, and which can be made immutable."
HOMEPAGE = "https://github.com/aio-libs/frozenlist"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cf056e8e7a0a5477451af18b7b5aa98c"

SRC_URI[sha256sum] = "68201be60ac56aff972dc18085800b6ee07973c49103a8aba669dee3d71079de"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"

