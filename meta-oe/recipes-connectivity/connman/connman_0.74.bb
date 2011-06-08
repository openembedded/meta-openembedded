require connman.inc

PR = "r1"

EXTRA_OECONF += "\
  --disable-gtk-doc \
  --enable-debug \
  --enable-threads \
  --enable-loopback \
  --enable-ethernet \
  --enable-wifi \
  --enable-bluetooth \
  --enable-ofono \
  --enable-tools \
  --disable-polkit \
  --enable-client \
  --enable-fake \
  --enable-ntpd \
  --with-ntpd=${bindir}/ntpd \
"

SRC_URI  = "\
  http://www.kernel.org/pub/linux/network/connman/connman-${PV}.tar.gz \
  file://link-against-libnl2.patch \
  file://connman \
"

SRC_URI[md5sum] = "3feb49e7c6dea22de83647cb1790a1b3"
SRC_URI[sha256sum] = "a283b590bd2ed5c1cd08630cbc7880d9906a3ca763cad121a66c9a5ddfc2882d"
