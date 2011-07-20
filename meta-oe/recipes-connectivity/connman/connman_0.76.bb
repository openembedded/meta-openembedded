require connman.inc

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
  file://connman \
"

SRC_URI[md5sum] = "59b4cfd9fa4f736f7a2d88ee0c758fe9"
SRC_URI[sha256sum] = "bf58fa72454bb63033da8f847a4709dbdfe64c000056a93a7504240cb31c1321"

# alg-test doesn't build, so disable that
do_configure_prepend() {
	sed -i 's:tools/alg-test ::g' Makefile.am
}
