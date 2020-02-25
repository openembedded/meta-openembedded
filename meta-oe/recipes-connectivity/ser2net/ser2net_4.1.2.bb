SUMMARY = "A serial to network proxy"
SECTION = "console/network"
HOMEPAGE = "http://sourceforge.net/projects/ser2net/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bae3019b4c6dc4138c217864bd04331f"

DEPENDS = "gensio libyaml"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/ser2net/ser2net/ser2net-${PV}.tar.gz"

SRC_URI[md5sum] = "1a42e9605342fd3d6fa41b48be7f564a"
SRC_URI[sha256sum] = "9bdc33476834bbbdcbfbb77ff8f1b1952fe2e7e19dde7e6f7932cea0cec958c7"

inherit autotools pkgconfig

BBCLASSEXTEND = "native nativesdk"
