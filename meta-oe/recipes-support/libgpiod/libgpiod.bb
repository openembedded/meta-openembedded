SUMMARY = "C library and tools for interacting with the linux GPIO character device"
HOMEPAGE = "https://github.com/brgl/libgpiod"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=2caced0b25dfefd4c601d92bd15116de"

UPSTREAM_CHECK_URI = "git://github.com/brgl/libgpiod/releases"

SRC_URI = "git://github.com/brgl/libgpiod.git"

SRCREV = "7ab5e53b69cce313ba87033a442cabd417f5d895"

PV = "0.2+git${SRCPV}"

S = "${WORKDIR}/git"

SRC_URI[md5sum] = "68f039487e940c15bbfc50a10ab4715b"
SRC_URI[sha256sum] = "7d7085d4e9cb811742ca8c5fe03458067efaeaa8abc23968d2e3c56bcc2d2ef8"

inherit autotools pkgconfig

# enable tools
PACKAGECONFIG ?= "tools"

PACKAGECONFIG[tests] = "--enable-tests,--disable-tests,kmod udev"
PACKAGECONFIG[tools] = "--enable-tools,--disable-tools,"

PACKAGES += " ${PN}-tools"

FILES_${PN}-tools = "${bindir}/*"
