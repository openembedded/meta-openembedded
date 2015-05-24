SUMMARY = "Poppler is a PDF rendering library based on the xpdf-3.0 code base"
LICENSE = "Adobe"
LIC_FILES_CHKSUM = "file://COPYING;md5=4870b98343f0bbb25fa43b9d2ba59448 \
                    file://COPYING.adobe;md5=63c6a8a9df204c00461fa5f163d8a663 \
                    file://COPYING.gpl2;md5=751419260aa954499f7abaabaa882bbe \
"

inherit allarch

SRC_URI = "http://poppler.freedesktop.org/${BP}.tar.gz"
SRC_URI[md5sum] = "636a8f2b9f6df9e7ced8ec0946961eaf"
SRC_URI[sha256sum] = "e752b0d88a7aba54574152143e7bf76436a7ef51977c55d6bd9a48dccde3a7de"

do_compile() {
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${datadir}"
