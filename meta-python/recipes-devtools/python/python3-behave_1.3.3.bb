SUMMARY = "A behavior-driven development framework, Python style"
HOMEPAGE = "https://github.com/behave/behave"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fca0a9c7e4e4148d675b4dafd5c1e80"

SRC_URI[sha256sum] = "2b8f4b64ed2ea756a5a2a73e23defc1c4631e9e724c499e46661778453ebaf51"

inherit pypi

PYPI_PACKAGE = "behave"

do_configure:prepend() {
    # Remove the duplicate "license" definition from setup.cfg
    sed -i '/license =/d' setup.cfg
}

RDEPENDS:${PN} += " \
    python3-parse \
    python3-parse-type \
    python3-six \
    python3-cucumber-tag-expressions \
    "
