require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "102acebaf453346d745b0974f736a5e6"
SRC_URI[sha256sum] = "46da18ce5ec6ded559dbf461ff58113f82937efb6e26a098a82b2f360e8faf88"

# Temporary disable until error like the one following are fixed
# efl-native/2_1.8.4-r0/efl-1.8.4/src/lib/eet/.libs/libeet.so: file not recognized: File truncated
# collect2: error: ld returned 1 exit status
# x86_64-linux-libtool: install: error: relink `modules/evas/savers/tiff/module.la' with the above command before installing it
# Makefile:11031: recipe for target 'install-savertiffpkgLTLIBRARIES' failed
PARALLEL_MAKE_class-native = ""

SRC_URI += "file://0001-configure.ac-Don-t-check-for-Xprint-extension.patch"
