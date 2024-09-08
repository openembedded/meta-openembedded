SUMMARY = "Calculate the distance between 2 points on Earth"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20a52d2c688975e989fcbee3e6c8d1a1"

SRC_URI[sha256sum] = "357e41dfddc4a0f2b1c941d92a590cac840f7ce4b3da14b45b68d968b3ad7cc7"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-numpy"

BBCLASSEXTEND = "native nativesdk"
