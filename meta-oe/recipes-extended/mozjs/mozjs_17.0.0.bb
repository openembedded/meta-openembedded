SUMMARY = "SpiderMonkey is Mozilla's JavaScript engine written in C/C++"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
    http://ftp.mozilla.org/pub/mozilla.org/js/${BPN}${PV}.tar.gz \
    file://0001-js.pc.in-do-not-include-RequiredDefines.h-for-depend.patch \
"
SRC_URI[md5sum] = "20b6f8f1140ef6e47daa3b16965c9202"
SRC_URI[sha256sum] = "321e964fe9386785d3bf80870640f2fa1c683e32fe988eeb201b04471c172fba"

S = "${WORKDIR}/${PN}${PV}/js/src"

inherit autotools pkgconfig perlnative

DEPENDS += "nspr zlib"

# nspr's package-config is ignored so set libs manually
EXTRA_OECONF = " \
    --target=${TARGET_SYS} \
    --host=${BUILD_SYS} \
    --build=${BUILD_SYS} \
    --prefix=${prefix} \
    --with-nspr-libs='-lplds4 -lplc4 -lnspr4' \
    --enable-threadsafe \
    --libdir=${libdir} \
"

# mozjs requires autoreconf 2.13
do_configure() {
    ${S}/configure ${EXTRA_OECONF}
}

PACKAGES =+ "lib${PN}"
FILES_lib${PN} += "${libdir}/lib*.so"
FILES_${PN}-dev += "${bindir}/js17-config"
