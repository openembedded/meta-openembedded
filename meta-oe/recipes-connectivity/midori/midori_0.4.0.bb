require midori.inc

PR = "r0"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.4/midori-${PV}.tar.bz2;name=midori \
           file://use_dom_api_to_get_selected_text.patch \
          "
SRC_URI[midori.md5sum] = "14aa14ccabf3d003903f1584dab15d7a"
SRC_URI[midori.sha256sum] = "0ac31b008b44e08cf0536a38405a0ecc2da7c28e3d549915325dbba74f74cd75"


