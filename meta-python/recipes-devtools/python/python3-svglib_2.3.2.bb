SUMMARY = "A pure-Python library for reading and converting SVG."
DESCRIPTION = "Svglib is a Python library for reading SVG files and \
    converting them (to a reasonable degree) to other formats using the \
    ReportLab Open Source toolkit."

LICENSE = "LGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b52f2d57d10c4f7ee67a7eb9615d5d24"

CVE_PRODUCT = "svglib"

SRC_URI[sha256sum] = "625b62cf440ab57f5def42d5be47a0d6b73c02711ef86ab1545650a98ac1ad5f"

inherit pypi python_hatchling

BBCLASSEXTEND = "native nativesdk"

RDEPENDS:${PN} = " \
    python3-pillow \
    python3-html \
    python3-cssselect2 \
    python3-webencodings \
    python3-lxml \
    python3-reportlab \
"
