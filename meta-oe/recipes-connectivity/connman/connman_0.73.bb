require connman.inc
PR = "r3"

EXTRA_OECONF += "\
  --disable-gtk-doc \
  --enable-debug \
  --enable-threads \
  --enable-loopback \
  --enable-ethernet \
  --enable-wifi \
  --disable-wimax \
  --enable-bluetooth \
  --enable-ofono \
  --enable-resolvconf \
  --enable-dnsproxy \
  --enable-tools \
  --disable-polkit \
  --enable-client \
  --enable-fake \
"

SRC_URI  = "\
  http://www.kernel.org/pub/linux/network/connman/connman-${PV}.tar.gz \
  file://link-against-libnl2.patch \
  file://connman \
"

SRC_URI[md5sum] = "01085b2ed8f7b11a41724f1528720cc7"
SRC_URI[sha256sum] = "b71ff2c3925ea82a7ebf818685f809e595d5c47d767005dfb149c6264b098417"
