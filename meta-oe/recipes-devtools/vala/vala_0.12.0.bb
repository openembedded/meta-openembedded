require vala.inc
SRC_URI = "ftp://ftp.gnome.org/pub/GNOME/sources/vala/0.12/vala-0.12.0.tar.bz2"

FILES_${PN} += "${datadir}/vala-0.12/vapi"

SRC_URI[md5sum] = "b11fafaa705085342156312e356b6ff2"
SRC_URI[sha256sum] = "9a398e16fba2c78c9bcadb05e489c9bc318e34901d43451ac5d2ce4bc46b1225"
