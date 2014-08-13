require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

# only for target, because configure doesn't default to sane default when
# these paths aren't passed in -native build
SRC_URI_append_class-target = " file://0001-Makefile-Use-elementary_codegen-defined-in-configure.patch"

SRC_URI[md5sum] = "cfcf3b0620cbb6e86021286631274d9a"
SRC_URI[sha256sum] = "47ee79f7fc4e355557e6809e2001468bc8e40939562e3a96f689969ab67c17b4"

# autotools-brokensep - configure updates Elementary.h correctly in ${B}, but then build is using Elementary.h from ${S}
# which includes #define ELM_EMAP (instead of #undef ELM_EMAP) and building fails
B = "${S}"
