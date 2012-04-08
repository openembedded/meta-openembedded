DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d66c1f33ba9b89616e45f588e7606ee6"

DEPENDS = "openssl"

SRC_URI = "http://nodejs.org/dist/v${PV}/node-v${PV}.tar.gz"
SRC_URI[md5sum] = "3033a866e230cca64e212ee8f2af27dd"
SRC_URI[sha256sum] = "e41922308155c5197c2d048948ca9cd76ea5f9a51f977e1591bd93fe17d4cf1f"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

do_configure_virtclass-native () {
  sed -i -e s:\'/usr/lib:\'${STAGING_LIBDIR}:g wscript
  sed -i -e s:\'/usr/local/lib:\'${STAGING_LIBDIR}:g wscript

  ./configure --prefix=${prefix} --without-snapshot
}

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

do_compile_virtclass-native () {
  make
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
RDEPENDS_${PN}_virtclass-native = "curl-native python-native"

FILES_${PN} += "${libdir}/node/wafadmin ${libdir}/node_modules"
BBCLASSEXTEND = "native"
