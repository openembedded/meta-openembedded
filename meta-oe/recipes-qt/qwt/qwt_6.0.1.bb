inherit qt4x11

require qwt.inc

PR = "r1"

SRC_URI[qwt.md5sum] = "ace68558eab873e2da7e641179c4ef0c"
SRC_URI[qwt.sha256sum] = "3fe19dd5962d705632fc2ef616b009299de6cf1e702538296924dbfdc8003cb2"

RPROVIDES_${PN}-dev = "libqwt-dev"
