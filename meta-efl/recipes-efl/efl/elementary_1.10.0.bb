require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

# only for target, because configure doesn't default to sane default when
# these paths aren't passed in -native build
SRC_URI_append_class-target = " file://0001-Makefile-Use-elementary_codegen-defined-in-configure.patch"

SRC_URI[md5sum] = "81d4072c728a41518f59f3d115c1556b"
SRC_URI[sha256sum] = "01c9127f0b29c1a07328199f51fb6d72d7f250be31e8afb26914566fb1c7e612"

# autotools-brokensep - configure updates Elementary.h correctly in ${B}, but then build is using Elementary.h from ${S}
# which includes #define ELM_EMAP (instead of #undef ELM_EMAP) and building fails
B = "${S}"
