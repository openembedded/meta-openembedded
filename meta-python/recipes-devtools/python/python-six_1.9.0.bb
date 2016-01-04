SUMMARY = "Python 2 and 3 compatibility library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6f00d4a50713fa859858dd9abaa35b21"

SRC_URI[md5sum] = "476881ef4012262dfc8adc645ee786c4"
SRC_URI[sha256sum] = "e24052411fc4fbd1f672635537c3fc2330d9481b18c0317695b46259512c91d5"

inherit pypi

do_compile_append() {
    export BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS}
    ${PYTHON} setup.py -q bdist_egg --dist-dir ./
}
do_install_append() {
    install -m 0644 ${S}/*.egg ${D}/${PYTHON_SITEPACKAGES_DIR}/
}
