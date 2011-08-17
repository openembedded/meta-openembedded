require connman.inc

EXTRA_OECONF += "\
  --disable-gtk-doc \
  --enable-debug \
  --enable-threads \
  --enable-loopback \
  --enable-ethernet \
  ${@base_contains('DISTRO_FEATURES', 'wifi','--enable-wifi', '--disable-wifi', d)} \
  ${@base_contains('DISTRO_FEATURES', 'bluetooth','--enable-bluetooth', '--disable-bluetooth', d)} \
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

# alg-test doesn't build, so disable that and test for if_alg.h as this header is only in 2.6.39
do_configure_prepend() {
	sed -i 's:tools/alg-test ::g' Makefile.am
	sed -i 's:AC_CHECK_HEADERS(linux/if_alg.h, dummy=yes,::g;
	        s:AC_MSG_ERROR(User-space algorithm header files are required))::g' configure.ac
}
