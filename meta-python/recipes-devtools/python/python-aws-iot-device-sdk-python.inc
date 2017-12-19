DESCRIPTION = "SDK for connecting to AWS IoT using Python."
HOMEPAGE = "https://github.com/aws/aws-iot-device-sdk-python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9ac49901b833e769c7d6f21e8dbd7b30"

SRC_URI[md5sum] = "97e8cf0d40ef3765d979c19b534a716d"
SRC_URI[sha256sum] = "d68cbff396143e837b4c23b6621d3b7ae374281afc75eed8e51a28625d9125ba"

inherit pypi

PYPI_PACKAGE = "AWSIoTPythonSDK"

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-json \
"

BBCLASSEXTEND = "native nativesdk"
