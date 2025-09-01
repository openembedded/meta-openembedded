SUMMARY = "A behavior-driven development framework, Python style"
HOMEPAGE = "https://github.com/behave/behave"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fca0a9c7e4e4148d675b4dafd5c1e80"

PV .= "+git${SRCREV}"
SRCREV = "c7ddf9aedf4d525a87f0be57dd83f2a3f63dd497"
SRC_URI += "git://github.com/behave/behave;branch=main;protocol=https"


inherit python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-parse-type \
    python3-setuptools \
    python3-six \
    python3-cucumber-tag-expressions \
    "
