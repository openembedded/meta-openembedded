DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT && zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6237f3a840aef5b7880fb4e49eecfe5"

DEPENDS = "openssl"

SRC_URI = " \
  http://nodejs.org/dist/node-v${PV}.tar.gz \
  file://libev-cross-cc_${PV}.patch \
"
SRC_URI[md5sum] = "e525b8b99d949bf2f031bc262c138e96"
SRC_URI[sha256sum] = "f231ea6d19ea9ea4c7f8e7ff5061e7d301f1635bec7ed0ff1eef2512576ea442"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

do_configure () {
  sed -i -e 's:/usr/lib:${STAGING_LIBDIR}:g' wscript
  sed -i -e 's:/usr/local/lib:${STAGING_LIBDIR}:g' wscript
  ./configure --prefix=${prefix} --without-snapshot
}

do_compile () {
  make
}

do_install () {
  DESTDIR=${D} oe_runmake install
}

RDEPENDS_${PN} = "curl python-shell python-datetime python-subprocess python-crypt python-textutils python-netclient "

FILES_${PN} += "${libdir}/node/wafadmin"
BBCLASSEXTEND = "native"
