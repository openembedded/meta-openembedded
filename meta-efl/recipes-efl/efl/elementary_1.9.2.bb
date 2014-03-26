require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

# only for target, because configure doesn't default to sane default when
# these paths aren't passed in -native build
SRC_URI_append_class-target = " file://0001-Makefile-Use-elementary_codegen-defined-in-configure.patch"

SRC_URI[md5sum] = "48a4b155ced187db87c503d4f376aa7a"
SRC_URI[sha256sum] = "c31644109cd29675bc65b220d19c633c8d089feb955c439988b2e19f026b3376"
