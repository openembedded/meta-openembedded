SUMMARY = "C++/Boost Asio based websocket client/server library."
SECTION = "libs/network"
HOMEPAGE = "https://github.com/zaphoyd/websocketpp"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=caca8c57fc82d4528bfd694b3de9b7cf"

DEPENDS = " ${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'openssl boost zlib', '', d)} "

SRC_URI = "git://github.com/cpprest-lts/websocketpp.git;protocol=https;branch=cpprest-lts \
          "

EXTRA_OECMAKE = "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', '-DBUILD_EXAMPLES=ON -DBUILD_TESTS=ON', '', d)} "

# this is an header only library, do not depend on the main package
RDEPENDS:${PN}-dev = ""

# to add this package to an SDK, since it isn't a reverse-dependency of anything, just use something like this:
# TOOLCHAIN_TARGET_TASK:append = " websocketpp-dev"

# tag 0.8.4+cpprest-lts
SRCREV = "c011de4608e8221412f2ea9be5d755a68a4e5e29"


inherit cmake

PACKAGES =+ "${PN}-examples"

FILES:${PN}-examples = "${docdir}"

do_install:append() {
	install -d ${D}${docdir}/${BPN}
	cp -R ${S}/examples ${D}${docdir}/${BPN}
}
