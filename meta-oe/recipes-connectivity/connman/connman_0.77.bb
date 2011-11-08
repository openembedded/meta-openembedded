require connman.inc
PR = "r4"

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

SRC_URI[md5sum] = "2e95edb0ead4fff3e84458f7fa192a64"
SRC_URI[sha256sum] = "df6e926c4b0ccbab26d6e21b9935ab72fa5adf275eb7ac2b48540117ae84cbcc"

# alg-test doesn't build, so disable that and test for if_alg.h as this header is only in 2.6.39
do_configure_prepend() {
	sed -i 's:tools/alg-test ::g' Makefile.am
	sed -i 's:AC_CHECK_HEADERS(linux/if_alg.h, dummy=yes,::g;
	        s:AC_MSG_ERROR(User-space algorithm header files are required))::g' configure.ac
}
