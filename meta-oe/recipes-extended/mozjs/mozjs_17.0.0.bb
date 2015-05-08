SUMMARY = "SpiderMonkey is Mozilla's JavaScript engine written in C/C++"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
    http://ftp.mozilla.org/pub/mozilla.org/js/${BPN}${PV}.tar.gz \
    file://0001-mozjs17.0.0-fix-the-compile-bug-of-powerpc.patch \
    file://0001-js.pc.in-do-not-include-RequiredDefines.h-for-depend.patch \
    file://0002-Move-JS_BYTES_PER_WORD-out-of-config.h.patch;patchdir=../../ \
    file://0003-Add-AArch64-support.patch;patchdir=../../ \
    file://0004-mozbug746112-no-decommit-on-large-pages.patch;patchdir=../../ \
    file://0005-aarch64-64k-page.patch;patchdir=../../ \
    file://0001-regenerate-configure.patch;patchdir=../../ \ 
    file://fix-the-compile-error-of-powerpc64.patch;patchdir=../../ \
  "

SRC_URI[md5sum] = "20b6f8f1140ef6e47daa3b16965c9202"
SRC_URI[sha256sum] = "321e964fe9386785d3bf80870640f2fa1c683e32fe988eeb201b04471c172fba"

S = "${WORKDIR}/${BPN}${PV}/js/src"

inherit autotools pkgconfig perlnative

DEPENDS += "nspr zlib"

# nspr's package-config is ignored so set libs manually
EXTRA_OECONF = " \
    --target=${TARGET_SYS} \
    --host=${BUILD_SYS} \
    --build=${BUILD_SYS} \
    --prefix=${prefix} \
    --libdir=${libdir} \
    --with-nspr-libs='-lplds4 -lplc4 -lnspr4' \
    --enable-threadsafe \
    --disable-static \
"

# mozjs requires autoreconf 2.13
do_configure() {
    ( cd ${S} 
      gnu-configize --force
      mv config.guess config.sub build/autoconf )
    ${S}/configure ${EXTRA_OECONF}
}

# patch.bbclass will try to apply the patches already present and fail, so clean them out
do_sourceclean() {
    (
    cd ${WORKDIR}/${BPN}${PV}/patches
    for i in $(cat series | awk '{print $1}') ; do
        rm -f $i
    done
    rm -f series
    )
}

addtask sourceclean before do_patch after do_unpack

PACKAGES =+ "lib${PN}"
FILES_lib${PN} += "${libdir}/lib*.so"
FILES_${PN}-dev += "${bindir}/js17-config"
