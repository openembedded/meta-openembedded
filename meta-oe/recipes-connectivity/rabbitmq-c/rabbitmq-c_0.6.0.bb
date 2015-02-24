DESCRIPTION = "A C-language AMQP client library for use with v2.0+ of the RabbitMQ broker"
HOMEPAGE = "https://github.com/alanxz/rabbitmq-c"
LIC_FILES_CHKSUM = "file://LICENSE-MIT;md5=6b7424f9db80cfb11fdd5c980b583f53"
LICENSE = "MIT"

SRC_URI = "https://github.com/alanxz/${BPN}/releases/download/v${PV}/${BP}.tar.gz"
SRC_URI[md5sum] = "80b350ce0de3f44cbd7b501bbbd8daf3"
SRC_URI[sha256sum] = "3a89349880582c24d81d52ee42a93a0633dc366a4cc96174512af88c32509dc2"

DEPENDS = "popt openssl"

EXTRA_OECONF = "--disable-examples --enable-tools --disable-docs"

inherit autotools pkgconfig

PACKAGE_BEFORE_PN += "${PN}-tools"
FILES_${PN}-tools = "${bindir}"
