require bluez4.inc

SRC_URI[md5sum] = "3059b7ef5168c84cd0c6a67034ce79f9"
SRC_URI[sha256sum] = "11e9279e2669db996afd464b96d2c68f41f157f6eb9b8842a0bbcad8a4eac18d"

DEPENDS += "libsndfile1"

PR = "${INC_PR}.0"

# Not all distros have a recent enough udev
BTUDEV = " --disable-udevrules"
BTUDEV_angstrom = " --enable-udevrules"
BTUDEV_shr = " --enable-udevrules"

EXTRA_OECONF += "${BTUDEV}"
