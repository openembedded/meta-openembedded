SUMMARY = "usb device database."
HOMEPAGE = "https://github.com/usbids/usbids"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

SRC_URI = "git://github.com/usbids/usbids.git;branch=master;protocol=https"

# September 15, 2025
SRCREV = "18b3055a9819a341ac2ff957f0b92f564d0d5d6e"


do_install() {
	install -d ${D}${datadir}
	install -m0644 ${S}/usb.ids ${D}${datadir}
}

FILES:${PN} = "${datadir}"
