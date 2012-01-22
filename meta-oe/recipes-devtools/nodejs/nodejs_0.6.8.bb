DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=672cc6c8dbb3e918277a61ea98f088f6"

DEFAULT_PREFERENCE = "-1"

DEPENDS = "openssl"

SRC_URI = "http://nodejs.org/dist/v${PV}/node-v${PV}.tar.gz"
SRC_URI[md5sum] = "9fd7baa2d27b848c3134e6ae35bb87b2"
SRC_URI[sha256sum] = "e6cbfc5ccdbe10128dbbd4dc7a88c154d80f8a39c3a8477092cf7d25eef78c9c"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

# Node is way too cool to use proper autotools, so we install two wrappers to forcefully inject proper arch cflags to workaround waf+scons
do_configure () {
  echo '#!/bin/sh' > ${WORKDIR}/gcc
  echo '${CC} $@' >> ${WORKDIR}/gcc

  echo '#!/bin/sh' > ${WORKDIR}/g++
  echo '${CXX} $@'>> ${WORKDIR}/g++

  chmod +x ${WORKDIR}/gcc ${WORKDIR}/g++

  sed -i -e s:\'/usr/lib:\'${STAGING_LIBDIR}:g wscript
  sed -i -e s:\'/usr/local/lib:\'${STAGING_LIBDIR}:g wscript

  export PATH=${WORKDIR}:${PATH}
  export CC=gcc
  export CXX=g++

  ./configure --prefix=${prefix} --without-snapshot
}

do_compile () {
  export PATH=${WORKDIR}:${PATH}
  export CC=gcc
  export CXX=g++
  make
}

do_install () {
  DESTDIR=${D} oe_runmake install
}

RDEPENDS_${PN} = "curl python-shell python-datetime python-subprocess python-crypt python-textutils python-netclient "

FILES_${PN} += "${libdir}/node/wafadmin"
BBCLASSEXTEND = "native"
