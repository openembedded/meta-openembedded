require cups14.inc

SRC_URI += " \
	file://use_echo_only_in_init.patch \
	file://skip_tools.patch \
	file://configure.patch \
	"

SRC_URI[md5sum] = "8776403ad60fea9e85eab9c04d88560d"
SRC_URI[sha256sum] = "d25ffa35add3abeeec0eba60be2cffc89425b649c64ef3a73dfc724683a59aa3"

PR = "r2"

DEFAULT_PREFERENCE = "-1"
DEPENDS += "virtual/libusb0"
EXTRA_OECONF += " --disable-gssapi --disable-largefile --enable-debug --disable-relro --enable-libusb"
CONFFILES_${PN} += "${sysconfdir}/cups/cupsd.conf"
