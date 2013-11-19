require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "3d2c3ca2ff1a072b72ffab40ff598dfb"
SRC_URI[sha256sum] = "7e514c0a972feea1c44c9074a4b58c14821d93dc91ff6f51066528f12ab42fe5"

# Temporary disable until error like the one following are fixed
# efl-native/2_1.8.4-r0/efl-1.8.4/src/lib/eet/.libs/libeet.so: file not recognized: File truncated
# collect2: error: ld returned 1 exit status
# x86_64-linux-libtool: install: error: relink `modules/evas/savers/tiff/module.la' with the above command before installing it
# Makefile:11031: recipe for target 'install-savertiffpkgLTLIBRARIES' failed
PARALLEL_MAKE = ""

SRC_URI += "file://0001-configure.ac-Don-t-check-for-Xprint-extension.patch"
