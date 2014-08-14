DESCRIPTION = "A C-language AMQP client library for use with v2.0+ of the RabbitMQ broker"
HOMEPAGE = "https://github.com/alanxz/rabbitmq-c"
LIC_FILES_CHKSUM = "file://LICENSE-MIT;md5=6b7424f9db80cfb11fdd5c980b583f53"
LICENSE = "MIT"

SRC_URI = "https://github.com/alanxz/${BPN}/releases/download/v${PV}/${BP}.tar.gz"
SRC_URI[md5sum] = "b1f902c658c772cda464754678d8deb6"
SRC_URI[sha256sum] = "53702ea2ab809af0f923e387458e2cad191d9549f50410035fe82ce5e6ccc4fa"

DEPENDS = "popt openssl"

EXTRA_OECONF = "--disable-examples --enable-tools --disable-docs"

inherit autotools pkgconfig

PACKAGE_BEFORE_PN += "${PN}-tools"
FILES_${PN}-tools = "${bindir}"
