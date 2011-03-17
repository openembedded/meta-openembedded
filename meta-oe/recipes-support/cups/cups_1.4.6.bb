require cups14.inc

DEPENDS += "virtual/libusb0"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=956e7600195e6139f12de8c2a5bbefa9"

SRC_URI += " \
	file://use_echo_only_in_init.patch \
    file://0001-don-t-try-to-run-generated-binaries.patch \
	"

SRC_URI[md5sum] = "de8fb5a29c36554925c0c6a6e2c0dae1"
SRC_URI[sha256sum] = "f08711702a77b52c7150f96fe1f45482f6151cb95ef601268c528607fe6ad514"

EXTRA_OECONF += " --disable-gssapi --enable-debug --disable-relro --enable-libusb"

CONFFILES_${PN} += "${sysconfdir}/cups/cupsd.conf"
