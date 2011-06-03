require vala.inc
SRC_URI = "ftp://ftp.gnome.org/pub/GNOME/sources/vala/0.12/vala-${PV}.tar.bz2"

FILES_${PN} += "${datadir}/vala-0.12/vapi"

SRC_URI[md5sum] = "bf35262cc611de447147d01cbac33767"
SRC_URI[sha256sum] = "d9a2be21bb8ab88eab838e7f3a4d0aaa2a1fddf35615dca32bef6a0051f58ea8"
