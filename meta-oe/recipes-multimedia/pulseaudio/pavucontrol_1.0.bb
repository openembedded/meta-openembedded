DESCRIPTION = "PulseAudio Volume Control (pavucontrol) is a simple GTK based volume control tool ("mixer") for the PulseAudio sound server."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "intltool gtkmm libcanberra pulseaudio"

inherit gnome

SRC_URI = "http://freedesktop.org/software/pulseaudio/${BPN}/${BP}.tar.xz"
SRC_URI[md5sum] = "0ab6b13542af9b4417438527d2524316"
SRC_URI[sha256sum] = "3dbe2865b6e3a195a8951967d9234f62cd254c30b08bdea1ecf8997b58f68cde"

EXTRA_OECONF = " --disable-gtk3 --disable-lynx "
