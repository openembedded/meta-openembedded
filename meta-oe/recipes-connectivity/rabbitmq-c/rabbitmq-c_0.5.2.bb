DESCRIPTION = "A C-language AMQP client library for use with v2.0+ of the RabbitMQ broker"
HOMEPAGE = "https://github.com/alanxz/rabbitmq-c"
LIC_FILES_CHKSUM = "file://LICENSE-MIT;md5=6b7424f9db80cfb11fdd5c980b583f53"
LICENSE = "MIT"

SRC_URI = "https://github.com/alanxz/${BPN}/releases/download/v${PV}/${BP}.tar.gz"
SRC_URI[md5sum] = "aa8d4d0b949f508c0da25a9c20bd7da7"
SRC_URI[sha256sum] = "1ec3c1141593b91c57bf1051657a1d64fb87da38760c7128a9622b107990f2f2"

DEPENDS = "popt openssl"

EXTRA_OECONF = "--disable-examples --enable-tools --disable-docs"

inherit autotools pkgconfig

PACKAGE_BEFORE_PN += "${PN}-tools"
FILES_${PN}-tools = "${bindir}"
