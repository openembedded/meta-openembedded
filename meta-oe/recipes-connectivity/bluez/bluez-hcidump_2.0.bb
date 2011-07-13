SUMMARY = "Linux Bluetooth Stack HCI Debugger Tool."
DESCRIPTION = "The hcidump tool reads raw HCI data coming from and going to a Bluetooth device \
and displays the commands, events and data in a human-readable form."

SECTION = "console"
DEPENDS = "bluez4"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a \
                    file://src/hcidump.c;beginline=1;endline=23;md5=3bee3a162dff43a5be7470710b99fbcf"
PR = "r0"

SRC_URI = "${KERNELORG_MIRROR}/linux/bluetooth/${P}.tar.gz \
           file://remove.ntoh64.definition.patch"

SRC_URI[md5sum] = "5c2e3ef0a68b2845047867ba51ff8ac9"
SRC_URI[sha256sum] = "b3b64fd0b18301df07d3aaf34c037c1e4808b4aaf702294822d62b5424f617fd"

EXTRA_OECONF = "--with-bluez-libs=${STAGING_LIBDIR} --with-bluez-includes=${STAGING_INCDIR}"

inherit autotools
