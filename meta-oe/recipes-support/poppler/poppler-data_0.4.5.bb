DESCRIPTION = "Poppler is a PDF rendering library based on the xpdf-3.0 code base."
LICENSE = "Adobe"
LIC_FILES_CHKSUM = "file://COPYING;md5=4870b98343f0bbb25fa43b9d2ba59448 \
                    file://COPYING.adobe;md5=63c6a8a9df204c00461fa5f163d8a663 \
                    file://COPYING.gpl2;md5=751419260aa954499f7abaabaa882bbe \
"

SRC_URI = "http://poppler.freedesktop.org/${PN}-${PV}.tar.gz"

do_compile() {
}

do_install() {
	oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${datadir}"
inherit allarch

SRC_URI[md5sum] = "448dd7c5077570e340340706cef931aa"
SRC_URI[sha256sum] = "3190bc457bafe4b158f79a08e8a3f1824031ec12acefc359e68e0f04da0f70fd"
