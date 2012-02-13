DESCRIPTION = "libgpewidget contains a collection of widgets and other common code shared by many GPE applications."
SECTION = "gpe/libs"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=d8045f3b8f929c1cb29a1e3fd737b499"

DEPENDS = "gtk+ cairo libxinerama libxcomposite libxrender gtk-doc"
PR = "r1"

SRC_URI += "file://pkgconfig.patch;striplevel=0"
SRC_URI[md5sum] = "b85a839264a35d0faf9a1a38c486e189"
SRC_URI[sha256sum] = "f96d30c09b0395ea4e146730fd52d9ea303b619bb139051d9f12d3f868a9e18c"

GPE_TARBALL_SUFFIX = "bz2"

inherit gpe pkgconfig autotools

EXTRA_OECONF = "--enable-cairo"
LDFLAGS += " -L${STAGING_LIBDIR}"

PACKAGES =+ "libgpewidget-bin"
FILES_libgpewidget-bin = "${bindir}/*"

