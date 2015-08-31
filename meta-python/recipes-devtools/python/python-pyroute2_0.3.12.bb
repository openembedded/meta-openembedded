DESCRIPTION = "A pure Python netlink and Linux network configuration library"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=270323035ef766597dabd91747c36e3d"

SRC_URI[md5sum] = "5d83ec82acb54ab88c98f0d25d13308b"
SRC_URI[sha256sum] = "c0e1637a75e099104f14d9e03fd5a698dfcc923a22fbfac4a19bd7e94d1fcaa5"

SRC_URI += "file://import-simplejson-as-json.patch"

inherit pypi

RDEPENDS_${PN} = "python-distutils \
                  python-simplejson \
                  "
