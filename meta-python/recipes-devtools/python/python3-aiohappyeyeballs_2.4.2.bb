SUMMARY = "Happy Eyeballs"
DESCRIPTION = "This library exists to allow connecting with Happy Eyeballs when you already have a list of addrinfo and not a DNS name."
HOMEPAGE = "https://github.com/aio-libs/aiohappyeyeballs"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fcf6b249c2641540219a727f35d8d2c2"

SRC_URI[sha256sum] = "4ca893e6c5c1f5bf3888b04cb5a3bee24995398efef6e0b9f747b5e89d84fd74"

inherit pypi python_poetry_core

BBCLASSEXTEND = "native nativesdk"

