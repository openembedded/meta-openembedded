require midori.inc

PR = "r2"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.4/midori-${PV}.tar.bz2;name=midori \
           file://waf \
          "
SRC_URI[midori.md5sum] = "900037557b82818d79d2dd1c5a7d7fd2"
SRC_URI[midori.sha256sum] = "fca4a0eab03358f20d7700069dbf2faaf8fa5c11aaad97f4208aea608f4bed9f"

do_configure_prepend() {
  cp -f ${WORKDIR}/waf ${S}/
}
