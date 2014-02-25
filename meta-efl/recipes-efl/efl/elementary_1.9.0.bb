require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

# only for target, because configure doesn't default to sane default when
# these paths aren't passed in -native build
SRC_URI_append_class-target = " file://0001-Makefile-Use-elementary_codegen-defined-in-configure.patch"

SRC_URI[md5sum] = "408b61a080f51731eeed81968d3cc7f5"
SRC_URI[sha256sum] = "95cb9ade6c1f135b673555d927d13b5e06986353047178125346abdf6c2b4d82"
