SUMMARY = "Snagboot intends to be an open-source replacement vendor-specific tools used to recover and/or reflash embedded platforms."

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI[sha256sum] = "40b045e6225f3544080558e4bd604d116d4cffceea80cb84307579d914e4e498"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    bash \
    python3-fcntl \
    python3-pyusb \
    python3-pyyaml \
    python3-setuptools \
    python3-six \
"

do_install:append() {
    install -D -m 0644 ${S}/src/snagrecover/50-snagboot.rules ${D}${sysconfdir}/udev/rules.d/50-snagboot.rules
}
