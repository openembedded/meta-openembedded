SUMMARY = "C library and tools for interacting with the linux GPIO character device"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=2caced0b25dfefd4c601d92bd15116de"

SRC_URI = "https://www.kernel.org/pub/software/libs/libgpiod/${BP}.tar.xz"

SRC_URI[md5sum] = "9c1966bea7dffd59bd099a8b2930e2ea"
SRC_URI[sha256sum] = "50c7862428ca90b58672e2475aea66d33a6fc86c6bab1928c0660f3aedf44a37"

PV = "0.3.2"

inherit autotools pkgconfig

# enable tools
PACKAGECONFIG ?= "tools"

PACKAGECONFIG[tests] = "--enable-tests,--disable-tests,kmod udev"
PACKAGECONFIG[tools] = "--enable-tools,--disable-tools,"

PACKAGES += " ${PN}-tools"

FILES_${PN}-tools = "${bindir}/*"
