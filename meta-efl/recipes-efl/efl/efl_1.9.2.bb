require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "5520ed809cdccd8b2a3e376f56f9ae61"
SRC_URI[sha256sum] = "530cec25c7f41c188599a1e239bef72142850f6a5f67c1dd4184354f756181ad"

# Temporary disable until error like the one following are fixed
# efl-native/2_1.8.4-r0/efl-1.8.4/src/lib/eet/.libs/libeet.so: file not recognized: File truncated
# collect2: error: ld returned 1 exit status
# x86_64-linux-libtool: install: error: relink `modules/evas/savers/tiff/module.la' with the above command before installing it
# Makefile:11031: recipe for target 'install-savertiffpkgLTLIBRARIES' failed
PARALLEL_MAKE_class-native = ""

SRC_URI += "file://0001-configure.ac-Don-t-check-for-Xprint-extension.patch"
