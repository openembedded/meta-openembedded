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
  file://link-against-libnl2.patch \
  file://connman \
"

SRC_URI[md5sum] = "9973cb89a11fff6b51fc85b51c13b711"
SRC_URI[sha256sum] = "b15361237f7ec8092fb0e55d4585550ab35491485edaf10ddd032d6e36299db7"

# alg-test doesn't build, so disable that
do_configure_prepend() {
	sed -i 's:tools/alg-test ::g' Makefile.am
}
