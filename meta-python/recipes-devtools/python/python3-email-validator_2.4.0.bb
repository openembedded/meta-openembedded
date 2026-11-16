SUMMARY = "A robust email address syntax and deliverability validation library."
SECTION = "devel/python"
LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2890aee62bd2a4c3197e2059016a397e"

SRC_URI[sha256sum] = "be487bc50bc3bd832a98515a55a15649d9bec06d954fa178663190714b6c4ee1"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-dnspython python3-idna"
