DESCRIPTION = "OpenH264 is a codec library which supports H.264 encoding and \
decoding. It is suitable for use in real time applications such as WebRTC."
HOMEPAGE = "http://www.openh264.org/"
SECTION = "libs/multimedia"

DEPENDS = "${@oe.utils.conditional('TARGET_ARCH', 'i386', 'nasm-native', \
              oe.utils.conditional('TARGET_ARCH', 'x86_64', 'nasm-native', \
             '', d), d)}"

LICENSE = "BSD-2-Clause"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bb6d3771da6a07d33fd50d4d9aa73bcf"

SRC_URI = "https://github.com/cisco/openh264/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "93da4e76cfda7ede8fd2df51b0021efd"
SRC_URI[sha256sum] = "9c07c38d7de00046c9c52b12c76a2af7648b70d05bd5460c8b67f6895738653f"

COMPATIBLE_MACHINE_armv7a = "(.*)"
COMPATIBLE_MACHINE_aarch64 = "(.*)"
COMPATIBLE_MACHINE_i386 = "(.*)"
COMPATIBLE_MACHINE_x86-64 = "(.*)"
COMPATIBLE_MACHINE_mips = "(.*)"
COMPATIBLE_MACHINE_mips64 = "(.*)"

EXTRA_OEMAKE_armv7a = "ARCH=arm"
EXTRA_OEMAKE_aarch64 = "ARCH=arm64"
EXTRA_OEMAKE_i386 = "ARCH=i386"
EXTRA_OEMAKE_x86-64 = "ARCH=x86_64"
EXTRA_OEMAKE_mips = "ARCH=mips"
EXTRA_OEMAKE_mips64 = "ARCH=mips64"

do_configure() {
    :
}

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${prefix}
}
