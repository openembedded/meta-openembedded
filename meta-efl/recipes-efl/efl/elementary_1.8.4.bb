require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

# only for target, because configure doesn't default to sane default when
# these paths aren't passed in -native build
SRC_URI_append_class-target = " file://0001-Makefile-Use-elm_prefs_cc-and-elementary_codegen-def.patch"

SRC_URI[md5sum] = "898d0ee0e18078640948fa5d04d56efe"
SRC_URI[sha256sum] = "129b8a015c34df3e8633b5b65aa3bcdd77ed101992948cb096389fed3f3a4244"
