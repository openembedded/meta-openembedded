require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "f5d0c61db951cb6a5434a30672975dd1"
SRC_URI[sha256sum] = "fd4703edb56c01b713f92849fb15f6ca3d866227ce4abff2d90b08244cd99a99"

# Temporary disable until error like the one following are fixed
# efl-native/2_1.8.4-r0/efl-1.8.4/src/lib/eet/.libs/libeet.so: file not recognized: File truncated
# collect2: error: ld returned 1 exit status
# x86_64-linux-libtool: install: error: relink `modules/evas/savers/tiff/module.la' with the above command before installing it
# Makefile:11031: recipe for target 'install-savertiffpkgLTLIBRARIES' failed
PARALLEL_MAKE_class-native = ""

SRC_URI += "file://0001-configure.ac-Don-t-check-for-Xprint-extension.patch"
