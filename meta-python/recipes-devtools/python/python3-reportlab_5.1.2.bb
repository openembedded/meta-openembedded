SUMMARY = "The Reportlab Toolkit."
DESCRIPTION = "An Open Source Python library for generating PDFs and graphics."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cf24392f451ff6710fca1e96cefa0424"

SRC_URI[sha256sum] = "2d2785281fc435e39fb33eee7f8c10b10ffee626dad9de360310390aacaf2f38"

CVE_PRODUCT = "reportlab"
CVE_STATUS[CVE-2020-28463] = "fixed-version: has been fixed since 3.5.55"
inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

BBCLASSEXTEND = "native nativesdk"
