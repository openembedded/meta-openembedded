require connman.inc
# connman requires libXtables now
DEPENDS += "iptables"
PR = "r0"

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

SRC_URI[md5sum] = "800f9356e0471c88819eee7184713a1f"
SRC_URI[sha256sum] = "9c8ad312573683fc9f50d5042d4a87ddc8e0700b27ac1b0fb8dc2e8b7424a60f"
