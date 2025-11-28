SUMMARY = "raw image decoder"
LICENSE = "LGPL-2.1-only | CDDL-1.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=74c9dffdc42805f9c0de2f97df6031fc"

SRC_URI = "git://github.com/LibRaw/LibRaw.git;branch=master;protocol=https \
           file://CVE-2023-1729.patch \
           file://CVE-2025-43961-43962.patch \
           file://CVE-2025-43963.patch \
           file://CVE-2025-43964.patch \
           "
SRCREV = "0209b6a2caec189e6d1a9b21c10e9e49f46e5a92"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

DEPENDS = "jpeg jasper lcms"

# The fixes are already included in the current versin (0.20.2)
CVE_CHECK_IGNORE += " \
    CVE-2020-35530 \
    CVE-2020-35531 \
    CVE-2020-35532 \
    CVE-2020-35533 \
    CVE-2020-35534 \
    CVE-2020-35535 \
    "
