require midori.inc

PR = "r2"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.4/midori-${PV}.tar.bz2;name=midori \
           file://waf \
          "

do_configure_prepend() {
  cp -f ${WORKDIR}/waf ${S}/
}

SRC_URI[midori.md5sum] = "33dde203cd71ae2b1d2adcc7f5739f65"
SRC_URI[midori.sha256sum] = "b3c77ddb6f562a0ea3d2e6fbb756ca0dc158ad8410f4b60f8e5998c2f7305f07"


