SUMMARY = "Library tasked with managing, extracting and handling media art caches"

LICENSE = "LGPLv2+ & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c \
                    file://libmediaart/extract.c;endline=18;md5=dff2b6328ab067b5baadc135f9876c36 \
                    file://tests/mediaarttest.c;endline=18;md5=067106eaa1f7a9d918759a096667f18e"

DEPENDS = "glib-2.0 gdk-pixbuf"

GNOME_COMPRESS_TYPE = "xz"
SRC_URI[archive.md5sum] = "1a44933d4cd0064e3c76d8d0ddacddc9"
SRC_URI[archive.sha256sum] = "3a9dffcad862aed7c0921579b93080d694b8a66f3676bfee8037867f653a1cd3"

inherit gnomebase gtk-doc

EXTRA_OECONF = "--disable-introspection --disable-qt --enable-gdkpixbuf"
