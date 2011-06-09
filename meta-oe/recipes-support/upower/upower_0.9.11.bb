DESCRIPTION = "UPower is an abstraction for enumerating power devices, listening to device events and querying history and statistics. "
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0de8fbf1d97a140d1d93b9f14dcfbf08"

DEPENDS = "libusb1 udev glib-2.0 dbus-glib polkit"

SRC_URI = "http://upower.freedesktop.org/releases/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "8e7bcb33570d1a4edc5c3b0daf3fe5ae"
SRC_URI[sha256sum] = "5c2cd224527d8d32a23d802864b9ad80c407028a07e74329c05da36dea4104ea"

inherit autotools pkgconfig

EXTRA_OECONF = " --with-backend=linux"

do_configure_prepend() {
	sed -i -e s:-nonet:\:g ${S}/doc/man/Makefile.am
	sed -i -e 's: doc : :g' ${S}/Makefile.am
}	

FILES_${PN} += "${datadir}/dbus-1/ \
                ${datadir}/polkit-1/ \
                ${base_libdir}/udev/* \
               "

FILES_${PN}-dbg += "${base_libdir}/udev/.debug"



