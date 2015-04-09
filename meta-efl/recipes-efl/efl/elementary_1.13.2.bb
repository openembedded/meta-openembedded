require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

# only for target, because configure doesn't default to sane default when
# these paths aren't passed in -native build
SRC_URI_append_class-target = " file://0001-Makefile-Use-elementary_codegen-defined-in-configure.patch"

SRC_URI[md5sum] = "0d32d241af1c96c06da6d130020275f0"
SRC_URI[sha256sum] = "312dd63f3c82c9076f0cfe5125990e650d7767a776186631455911a980f91039"

# autotools-brokensep - configure updates Elementary.h correctly in ${B}, but then build is using Elementary.h from ${S}
# which includes #define ELM_EMAP (instead of #undef ELM_EMAP) and building fails
B = "${S}"
