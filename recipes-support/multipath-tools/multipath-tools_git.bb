DESCRIPTION = "The upstream project used to drive the Device Mapper multipathing driver."

DEPENDS = "lvm2 libaio readline"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7be2873b6270e45abacc503abbe2aa3d"


SRC_URI = "git://git.opensvc.com/multipath-tools/.git;protocol=http"
SRCREV = "15fc23ffb2bd1bac6990eda3e23c3e8980e6cfa5"

S = "${WORKDIR}/git"

PV = "0.4.9+git"

EXTRA_OEMAKE = "MULTIPATH_VERSION=${PV} DESTDIR=${D} syslibdir=${base_libdir} libdir=${base_libdir}/multipath"

do_install() {
	oe_runmake install
}

FILES_${PN}-dbg += "${base_libdir}/multipath/.debug"
FILES_${PN} += "${base_libdir}/multipath \
                ${base_libdir}/systemd"

PACKAGES =+ "kpartx"
FILES_kpartx = "${base_sbindir}/kpartx \
                ${base_libdir}/udev/kpartx_id"

RDEPENDS_${PN} += "kpartx"
