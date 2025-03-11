SUMMARY = "Happy Eyeballs"
DESCRIPTION = "This library exists to allow connecting with Happy Eyeballs when you already have a list of addrinfo and not a DNS name."
HOMEPAGE = "https://github.com/aio-libs/aiohappyeyeballs"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fcf6b249c2641540219a727f35d8d2c2"

SRC_URI[sha256sum] = "18fde6204a76deeabc97c48bdd01d5801cfda5d6b9c8bbeb1aaaee9d648ca191"

inherit pypi python_poetry_core

BBCLASSEXTEND = "native nativesdk"

