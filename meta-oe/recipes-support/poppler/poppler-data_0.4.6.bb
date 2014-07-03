SUMMARY = "Poppler is a PDF rendering library based on the xpdf-3.0 code base"
LICENSE = "Adobe"
LIC_FILES_CHKSUM = "file://COPYING;md5=4870b98343f0bbb25fa43b9d2ba59448 \
                    file://COPYING.adobe;md5=63c6a8a9df204c00461fa5f163d8a663 \
                    file://COPYING.gpl2;md5=751419260aa954499f7abaabaa882bbe \
"

inherit allarch

SRC_URI = "http://poppler.freedesktop.org/${BP}.tar.gz"
SRC_URI[md5sum] = "a8a7ca808827dd674faba6e4fc73b471"
SRC_URI[sha256sum] = "f306901dfa5bda90cd6663d4eedb1c773c3c709de78018c79f1282b2c8f90afa"

do_compile() {
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${datadir}"
