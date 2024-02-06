SUMMARY = "Google Spreadsheets Python API"
HOMEPAGE = "https://github.com/burnash/gspread"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9488e21983675fa56dc05af558b83e2f"

SRC_URI[sha256sum] = "3b5efe315aeaa290d21befc8ee7bb0239db78c7b495d8831ae29fd1a182292a1"

S = "${WORKDIR}/gspread-${PV}"

RDEPENDS:${PN} = "python3-requests"

inherit pypi python_poetry_core
