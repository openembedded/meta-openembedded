
SUMMARY = "CANopenTerm is a versatile software tool to analyse and \
          configure CANopen devices. \
          "
DESCRIPTION = "CANopenTerm is an open-source software tool designed for the \
              development, testing, and analysis of CANopen CC networks and \
              devices.  It extends its capabilities to support other CAN CC \
              protocols, including SAE J1939 and OBD-II. \
              "
HOMEPAGE = "https://canopenterm.de"
BUGTRACKER = "https://github.com/CANopenTerm/CANopenTerm/issues"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=10e84ea70e8c3a1fbc462f5424806474"

DEPENDS = "libinih libsdl2 lua libsocketcan pocketpy"

SRC_URI = "git://github.com/CANopenTerm/CANopenTerm.git;protocol=https;branch=main \
           file://0001-can_linux-initialize-msghdr-in-a-portable-way.patch \
           "
SRCREV = "5bc04e09351f68e889381e1912b0445c4f18ee32"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += "-DBUILD_YOCTO=ON"

FILES:${PN} += "${bindir}/CANopenTerm ${datadir}"

RDEPENDS:${PN} = "libinih libsdl2 lua libsocketcan pocketpy"
