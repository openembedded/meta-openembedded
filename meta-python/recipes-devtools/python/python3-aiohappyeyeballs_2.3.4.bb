SUMMARY = "Happy Eyeballs"
DESCRIPTION = "This library exists to allow connecting with Happy Eyeballs when you already have a list of addrinfo and not a DNS name."
HOMEPAGE = "https://github.com/aio-libs/aiohappyeyeballs"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fcf6b249c2641540219a727f35d8d2c2"

SRC_URI[sha256sum] = "7e1ae8399c320a8adec76f6c919ed5ceae6edd4c3672f4d9eae2b27e37c80ff6"

inherit pypi python_poetry_core

BBCLASSEXTEND = "native nativesdk"

