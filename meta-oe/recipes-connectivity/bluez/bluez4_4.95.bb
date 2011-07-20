SUMMARY = "Linux Bluetooth Stack Userland V4"
DESCRIPTION = "Linux Bluetooth stack V4 userland components.  These include a system configurations, daemons, tools and system libraries."
HOMEPAGE = "http://www.bluez.org"
SECTION = "libs"
LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
                    file://COPYING.LIB;md5=fb504b67c50331fc78734fed90fb0e09 \
                    file://src/main.c;beginline=1;endline=24;md5=9bc54b93cd7e17bf03f52513f39f926e \
                    file://sbc/sbc.c;beginline=1;endline=25;md5=1a40781ed30d50d8639323a184aeb191"
DEPENDS = "udev gst-plugins-base alsa-lib libusb dbus-glib usbpath"
RDEPENDS_${PN}-dev = "bluez-hcidump"

ASNEEDED = ""

PR = "r0"

# to find bluez patches from oe-core
FILESPATH =. "${COREBASE}/meta/recipes-connectivity/bluez/bluez4-4.82/:"
SRC_URI = "\
  ${KERNELORG_MIRROR}/linux/bluetooth/bluez-${PV}.tar.gz \
  file://fix-dfutool-usb-declaration-mismatch.patch \
  file://bluetooth.conf \
"
SRC_URI[md5sum] = "341294b2849a04a4afff5c96bfbf30b2"
SRC_URI[sha256sum] = "d6ea9de410fc2bcd2620d709c2202893b218e2e6a55c3c0ce6bebd27fa4120f6"

S = "${WORKDIR}/bluez-${PV}"

inherit autotools

EXTRA_OECONF = "\
  --enable-gstreamer \
  --enable-alsa \
  --enable-usb \
  --enable-tools \
  --enable-bccmd \
  --enable-hid2hci \
  --enable-dfutool \
  --enable-hidd \
  --enable-pand \
  --enable-dund \
  --disable-cups \
  --enable-test \
  --enable-configfiles \
"

do_install_append() {
	install -m 0644 ${S}/audio/audio.conf ${D}/${sysconfdir}/bluetooth/
	install -m 0644 ${S}/network/network.conf ${D}/${sysconfdir}/bluetooth/
	install -m 0644 ${S}/input/input.conf ${D}/${sysconfdir}/bluetooth/
	# at_console doesn't really work with the current state of OE, so punch some more holes so people can actually use BT
	install -m 0644 ${WORKDIR}/bluetooth.conf ${D}/${sysconfdir}/dbus-1/system.d/
}

PACKAGES =+ "gst-plugin-bluez libasound-module-bluez"

FILES_gst-plugin-bluez = "${libdir}/gstreamer-0.10/lib*.so"
FILES_libasound-module-bluez = "${libdir}/alsa-lib/lib*.so ${datadir}/alsa"
FILES_${PN} += "${libdir}/bluetooth/plugins/*.so ${base_libdir}/udev/ ${base_libdir}/systemd/"
FILES_${PN}-dev += "\
  ${libdir}/bluetooth/plugins/*.la \
  ${libdir}/alsa-lib/*.la \
  ${libdir}/gstreamer-0.10/*.la \
"

FILES_${PN}-dbg += "\
  ${libdir}/bluetooth/plugins/.debug \
  ${libdir}/*/.debug \
  ${base_libdir}/udev/.debug \
"
