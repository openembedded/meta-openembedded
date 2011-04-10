DESCRIPTION = "Linux Bluetooth Stack Userland V4"
HOMEPAGE = "http://www.bluez.org"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "GPLv2/LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
                    file://COPYING.LIB;md5=fb504b67c50331fc78734fed90fb0e09 \
"
DEPENDS = "gst-plugins-base alsa-lib virtual/libusb0 dbus-glib"
INC_PR = "r8"

# temporary solution until bug 5176 is properly fixed
PROVIDES += "bluez-utils bluez-libs bluez-utils-dbus"
RPROVIDES_bluez4 += "bluez-utils bluez-libs bluez-utils-dbus"
RPROVIDES_bluez4-dev = "bluez-libs-dev"

SRC_URI = "\
  http://www.kernel.org/pub/linux/bluetooth/bluez-${PV}.tar.gz \
  file://fix-dfutool-usb-declaration-mismatch.patch \
  file://bluetooth.conf \
"
S = "${WORKDIR}/bluez-${PV}"

inherit autotools update-rc.d

EXTRA_OECONF = "\
  --enable-gstreamer \
  --enable-alsa \
  --enable-usb \
  --enable-netlink \
  --enable-tools \
  --enable-bccmd \
  --enable-hid2hci \
  --enable-dfutool \
  --enable-hidd \
  --enable-pand \
  --enable-dund \
  --disable-cups \
  --enable-test \
  --enable-manpages \
  --enable-configfiles \
  --enable-initscripts \
  --disable-pcmciarules \
"

do_install_append() {
        install -m 0644 ${S}/audio/audio.conf ${D}/${sysconfdir}/bluetooth/
        install -m 0644 ${S}/network/network.conf ${D}/${sysconfdir}/bluetooth/
        install -m 0644 ${S}/input/input.conf ${D}/${sysconfdir}/bluetooth/
        # at_console doesn't really work with the current state of OE, so punch some more holes so people can actually use BT
        install -m 0644 ${WORKDIR}/bluetooth.conf ${D}/${sysconfdir}/dbus-1/system.d/
}

INITSCRIPT_NAME = "bluetooth"
INITSCRIPT_PARAMS = "defaults 23 19"

PACKAGES =+ "gst-plugin-bluez libasound-module-bluez"

FILES_gst-plugin-bluez = "${libdir}/gstreamer-0.10/lib*.so"
FILES_libasound-module-bluez = "${libdir}/alsa-lib/lib*.so ${datadir}/alsa/bluetooth.conf"
FILES_${PN} += "${libdir}/bluetooth/plugins/*.so ${base_libdir}/udev"
FILES_${PN}-dev += "\
  ${libdir}/bluetooth/plugins/*.la \
  ${libdir}/alsa-lib/*.la \
  ${libdir}/gstreamer-0.10/*.la \
"

FILES_${PN}-dbg += "\
  ${libdir}/bluetooth/plugins/.debug \
  ${libdir}/*/.debug \
"
