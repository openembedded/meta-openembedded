require ${BPN}.inc

SRCREV = "164189"
PV = "2.3.4+svnr${SRCPV}"

SRCREV_FORMAT = "source"

SRC_URI = "\
    svn://svn.webkit.org/repository/webkit/trunk;module=Source;name=source;protocol=http;subdir=src \
    svn://svn.webkit.org/repository/webkit/trunk/;module=WebKitLibraries;protocol=http;subdir=src \
    svn://svn.webkit.org/repository/webkit/trunk/;module=Tools;protocol=http;subdir=src \
    file://CMakeLists.txt \
    file://0001-WebKitHelpers.cmake-Add-Wno-error-cast-align.patch \
    file://0002-ARMAssembler.h-Don-t-generate-BKPT-and-BLX-for-armv4.patch \
    file://0003-Fix-linking-issue.patch \
"

S = "${WORKDIR}/src"

do_configure_prepend() {
    cp ${WORKDIR}/CMakeLists.txt ${S};
}
