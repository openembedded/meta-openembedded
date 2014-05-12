SUMMARY = "Xorg 100 DPI font set"
LICENSE = "Custom"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "\
	font-adobe-100dpi \
	font-adobe-utopia-100dpi \
	font-bh-100dpi \
	font-bh-lucidatypewriter-100dpi \
	font-bitstream-100dpi \
"
