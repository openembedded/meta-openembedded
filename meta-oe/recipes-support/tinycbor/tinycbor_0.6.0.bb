DESCRIPTION = "The TinyCBOR library is a small CBOR encoder and decoder library, optimized for very fast operation with very small footprint."
HOMEPAGE = "https://github.com/intel/tinycbor"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6c1ac30774dd6476b42b5b020cd2aa5f"

SRC_URI = "git://github.com/intel/tinycbor.git;protocol=https;branch=main"
SRCREV = "d393c16f3eb30d0c47e6f9d92db62272f0ec4dc7"

S = "${WORKDIR}/git"

do_configure() {
  sed -i -e 's,@prefix@,${prefix},g' \
         -e 's,@exec_prefix@,${exec_prefix},g' \
         -e 's,@libdir@,${libdir},g' \
         -e 's,@includedir@,${includedir},g' \
         -e 's,@version@,0.6.0,g' ${S}/tinycbor.pc.in
}

do_compile() {
  oe_runmake
}

do_install() {
  oe_runmake install DESTDIR=${D} prefix=${prefix}
}
