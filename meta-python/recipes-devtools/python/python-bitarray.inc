SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=f1c84020645d906bd6569f6bcf071eca"

SRC_URI[md5sum] = "ce21bdfb6d074bd051abd601667b238e"
SRC_URI[sha256sum] = "4f8706b651243c9faa981f075bcbdef2fab83e9b9bc9211ed2cb5849f9a68342"

inherit pypi

BBCLASSEXTEND = "native nativesdk"
