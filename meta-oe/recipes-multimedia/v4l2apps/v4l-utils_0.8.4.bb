DESCRIPTION = "v4l2 and IR applications"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=48da9957849056017dc568bbc43d8975 \
                    file://COPYING.LIB;md5=d749e86a105281d7a44c2328acebc4b0"

DEPENDS = "jpeg"

# libv4l was absorbed into this, let OE know that
PROVIDES = "libv4l"

PR = "r1"

SRC_URI = "git://linuxtv.org/v4l-utils.git;protocol=git"
SRCREV = "v4l-utils-${PV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "PREFIX=${prefix} DESTDIR=${D}"

do_compile() {
	# fix up some ASNEEDED things
	for i in $(find ${S} -name "Makefile") ; do
		sed -i 's:-lrt:-lrt -lpthread:g' $i
	done

	oe_runmake
}

do_install() {
	oe_runmake install
}

PACKAGES =+ "rc-keymaps libv4l libv4l-dbg libv4l-dev"

FILES_rc-keymaps = "${sysconfdir}/rc*"
FILES_${PN} = "${bindir} ${sbindir} ${base_libdir}/udev/rules.d/70-infrared.rules"
FILES_libv4l += "${libdir}/libv4l/* ${libdir}/*.so.*"
FILES_libv4l-dbg += "${libdir}/libv4l/.debug"
FILES_libv4l-dev += "${libdir}/*.so ${includedir}/lib* ${libdir}/pkgconfig/lib*"

