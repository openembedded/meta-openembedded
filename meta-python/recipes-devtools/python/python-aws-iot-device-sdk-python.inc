DESCRIPTION = "SDK for connecting to AWS IoT using Python."
HOMEPAGE = "https://github.com/aws/aws-iot-device-sdk-python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9ac49901b833e769c7d6f21e8dbd7b30"

SRC_URI[md5sum] = "6835982c7fb36b19aa474d346f29ec3d"
SRC_URI[sha256sum] = "ac98d39d7230e06fc9a30bdb3398c985a7c357b10898a769db022d315855102a"

inherit pypi

PYPI_PACKAGE = "AWSIoTPythonSDK"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-json \
"

BBCLASSEXTEND = "native nativesdk"
