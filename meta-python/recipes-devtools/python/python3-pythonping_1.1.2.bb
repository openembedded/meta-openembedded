SUMMARY = "PythonPing is simple way to ping in Python."
HOMEPAGE = "https://pypi.org/project/pythonping/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://setup.py;beginline=12;endline=12;md5=2d33c00f47720c7e35e1fdb4b9fab027"

SRC_URI[sha256sum] = "7f783688268334fbfdec31b3a3b105a347844bd621de48d63f38e6ecfaf14af2"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-io"
