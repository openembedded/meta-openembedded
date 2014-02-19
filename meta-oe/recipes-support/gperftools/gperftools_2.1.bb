SUMMARY = "Fast, multi-threaded malloc() and nifty performance analysis tools"
HOMEPAGE = "http://code.google.com/p/gperftools/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=762732742c73dc6c7fbe8632f06c059a"
DEPENDS = "libunwind"

SRC_URI = "${DEBIAN_MIRROR}/main/g/google-perftools/google-perftools_${PV}.orig.tar.gz"

SRC_URI[md5sum] = "5e5a981caf9baa9b4afe90a82dcf9882"
SRC_URI[sha256sum] = "f3ade29924f89409d8279ab39e00af7420593baa4941c318db42e70ead7e494f"

inherit autotools
