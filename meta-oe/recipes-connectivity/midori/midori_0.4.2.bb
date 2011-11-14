require midori.inc

PR = "r2"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.4/midori-${PV}.tar.bz2;name=midori \
           file://waf \
          "

do_configure_prepend() {
  cp -f ${WORKDIR}/waf ${S}/
}

SRC_URI[midori.md5sum] = "4ec9c3daa332ebe865cba6aa8256ef63"
SRC_URI[midori.sha256sum] = "2e3f104254fb9a28c77e8acf1cc8ae6dd529499711d558b29146ab0fa915f0a7"


