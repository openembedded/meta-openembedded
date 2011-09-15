inherit qt4e

require qwt.inc

SRC_URI[qwt.md5sum] = "4a595b8db0ec3856b117836c1d60cb27"
SRC_URI[qwt.sha256sum] = "e2b8bb755404cb3dc99e61f3e2d7262152193488f5fbe88524eb698e11ac569f"

RPROVIDES_${PN}-dev = "libqwt-dev"