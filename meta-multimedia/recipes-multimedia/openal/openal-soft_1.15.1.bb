DESCRIPTION = "OpenAL is a cross-platform 3D audio API"
HOMEPAGE = "http://kcat.strangesoft.net/openal.html"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=facc3a8f452930083bbb95d82b989c35"

inherit cmake

DEPENDS = "${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa-lib', '', d)} \
           ${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)} \
"

SRC_URI = "http://kcat.strangesoft.net/openal-releases/${PN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "ea83dec3b9655a27d28e7bc7cae9cd71"
SRC_URI[sha256sum] = "0e29a162f0841ccb4135ce76e92e8a704589b680a85eddf76f898de5236eb056"

EXTRA_OECMAKE = "-DALSA=${@base_contains('DISTRO_FEATURES', 'alsa', 'TRUE', 'FALSE', d)} \
                 -DPULSEAUDIO=${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'TRUE', 'FALSE', d)} \
"

FILES_${PN} += "${datadir}/openal"
