FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://pulseaudio-2.0-udev-symbols.patch"

PRINC := "${@int(PRINC) + 1}"
