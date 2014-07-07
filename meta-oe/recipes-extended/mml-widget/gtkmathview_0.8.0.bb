HOMEPAGE = "http://helm.cs.unibo.it/mml-widget/"
DEPENDS = "t1lib gtk+ popt libxslt libxml2"

LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

PR = "r3"

SRC_URI = "http://helm.cs.unibo.it/mml-widget/sources/${BP}.tar.gz \
           file://mathview-gcc43x.diff \
           file://mathview-gcc47x.diff \
           file://qualify-lookup.diff \
"
SRC_URI[md5sum] = "b53564e553728d4b69f7d366dfeb5299"
SRC_URI[sha256sum] = "1dc30175da6a3c560a7d62d1abe1c2f9829d988e6f1a7c5e766544575c558c43"

inherit autotools-brokensep pkgconfig

do_configure_prepend() {
    sed -i -e s:AM_BINRELOC::g ${S}/configure.ac
}
