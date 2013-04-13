DESCRIPTION = "Opencv : The Open Computer Vision Library"
HOMEPAGE = "http://opencv.willowgarage.com/wiki/"
SECTION = "libs"
LICENSE = "BSD"

DEPENDS = "opencv"

LIC_FILES_CHKSUM = "file://include/opencv2/opencv.hpp;endline=41;md5=6d690d8488a6fca7a2c192932466bb14 \
"

SRC_URI = "${SOURCEFORGE_MIRROR}/opencvlibrary/opencv-unix/${PV}/OpenCV-${PV}.tar.bz2 \
"

SRC_URI[md5sum] = "c0a5af4ff9d0d540684c0bf00ef35dbe"
SRC_URI[sha256sum] = "f8fbe985978d4eae73e8c3b526ed40a37d4761d2029a5b035233f58146f6f59b"

S = "${WORKDIR}/OpenCV-${PV}"

do_install() {
    cd samples/c
    install -d ${D}/${bindir}
    install -d ${D}/${datadir}/opencv/samples

    cp * ${D}/${datadir}/opencv/samples || true

    for i in *.c; do
        echo "compiling $i"
        ${CXX} ${CFLAGS} ${LDFLAGS} -ggdb `pkg-config --cflags opencv` -o `basename $i .c` $i `pkg-config --libs opencv` || true
        install -m 0755 `basename $i .c` ${D}/${bindir} || true
        rm ${D}/${datadir}/opencv/samples/`basename $i .c` || true
    done
    for i in *.cpp; do
        echo "compiling $i"
        ${CXX} ${CFLAGS} ${LDFLAGS} -ggdb `pkg-config --cflags opencv` -o `basename $i .cpp` $i `pkg-config --libs opencv` || true
        install -m 0755 `basename $i .cpp` ${D}/${bindir} || true
        rm ${D}/${datadir}/opencv/samples/`basename $i .cpp` || true
    done
}

FILES_${PN}-dev += "${datadir}/opencv/samples/*.c* ${datadir}/opencv/samples/*.vcp* ${datadir}/opencv/samples/build*" 
FILES_${PN} += "${bindir} ${datadir}/opencv"
