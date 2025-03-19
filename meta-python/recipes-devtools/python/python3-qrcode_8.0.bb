SUMMARY = "QR Code image generator"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4b802d2a65df4626623c79757f486af9"

SRC_URI[sha256sum] = "025ce2b150f7fe4296d116ee9bad455a6643ab4f6e7dce541613a4758cbce347"

inherit pypi python_poetry_core

RDEPENDS:${PN} = " \
    python3-six \
    python3-pillow \
    python3-pypng \
    python3-typing-extensions \
"
