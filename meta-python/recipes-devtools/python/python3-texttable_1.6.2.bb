SUMMARY = "module for creating simple ASCII tables"
HOMEPAGE = "https://github.com/foutaise/texttable/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=af37e32174857b1f2043245f3651c824"

inherit pypi setuptools3

SRC_URI[md5sum] = "763141f0bdf598cdc5cf780ed4d2eb94"
SRC_URI[sha256sum] = "eff3703781fbc7750125f50e10f001195174f13825a92a45e9403037d539b4f4"

BBCLASSEXTEND = "native nativesdk"
