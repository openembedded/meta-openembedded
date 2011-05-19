DESCRIPTION = "Poppler is a PDF rendering library based on the xpdf-3.0 code base."
LICENSE = "Adobe"
LIC_FILES_CHKSUM = "file://COPYING;md5=4870b98343f0bbb25fa43b9d2ba59448 \
                    file://COPYING.adobe;md5=63c6a8a9df204c00461fa5f163d8a663 \
                    file://COPYING.gpl2;md5=751419260aa954499f7abaabaa882bbe \
"

PR = "r2"

SRC_URI = "http://poppler.freedesktop.org/${PN}-${PV}.tar.gz"

do_compile() {
}

do_install() {
	oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${datadir}"
inherit allarch

SRC_URI[md5sum] = "6975bf8e9ea2cfb42b9ecdbcc257cf57"
SRC_URI[sha256sum] = "5caf7e10b7b0c6a4e1e753af09be52224e88bb8fbcb47794ad72b99b9e24b109"
