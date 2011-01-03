require directfb.inc

RV = "1.4-5"
PR = "r2"

DEPENDS += "sysfsutils"

SRC_URI = " \
    http://directfb.org/downloads/Core/DirectFB-1.4/DirectFB-${PV}.tar.gz \
    file://directfb-1.2.x-fix-pkgconfig-cflags.patch \
    file://mkdfiff.patch \
    file://dont-use-linux-config.patch \
   "

EXTRA_OECONF = "\
  --enable-freetype=yes \
  --enable-zlib \
  --with-gfxdrivers=none \
  --disable-sdl \
  --disable-vnc \
  --disable-x11 \
"

LEAD_SONAME = "libdirectfb-1.4.so.5"

SRC_URI[md5sum] = "dbe1957932f81790742b58b94c1c7b84"
SRC_URI[sha256sum] = "b18121e60e9a084ee347037a53a46cfaea1c09505bd99d673c2e65eca285e69b"
