SUMMARY = "Python 3.3+'s ipaddress for Python 2.6, 2.7, 3.2."
HOMEPAGE = "https://github.com/phihag/ipaddress"
LICENSE = "Python-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f538584cc3407bf76042def7168548a"

DEPENDS += "python-pip"

SRC_URI[md5sum] = "8bbf0326719fafb1f453921ef96729fe"
SRC_URI[sha256sum] = "3a21c5a15f433710aaa26f1ae174b615973a25182006ae7f9c26de151cd51716"

inherit pypi setuptools

BBCLASSEXTEND = "native nativesdk"
