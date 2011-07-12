DESCRIPTION = "ulseAudio Volume Control (pavucontrol) is a simple GTK based volume control tool ("mixer") for the PulseAudio sound server."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "intltool gtkmm libcanberra pulseaudio"

inherit gnome

PV = "0.9.10+git"

SRCREV = "50efee1180a785f297ce6dc38b349ee7e28c8129"
SRC_URI = "git://anongit.freedesktop.org/pulseaudio/pavucontrol;protocol=git"
S = "${WORKDIR}/git"

EXTRA_OECONF = " --disable-gtk3 "


