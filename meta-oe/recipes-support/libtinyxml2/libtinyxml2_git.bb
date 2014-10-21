SUMMARY = "TinyXML-2 is a simple, small, efficient, C++ XML parser that can be easily integrating into other programs."
HOMEPAGE = "http://www.grinninglizard.com/tinyxml2/"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://readme.md;md5=0033b2f8a25283414b21354670bb1334"
SECTION = "libs"

SRCREV = "d211bb13512cf4edb408e2c4badbcf4100ce0fd0"

PV = "2.2.0+git${SRCPV}"

SRC_URI = "git://github.com/leethomason/tinyxml2.git"

S = "${WORKDIR}/git"

EXTRA_CXXFLAGS = "-I. -fPIC"

do_compile() {
    ${CXX} ${CXXFLAGS} ${EXTRA_CXXFLAGS} -c -o tinyxml2.o tinyxml2.cpp
    ${CXX} ${CXXFLAGS} \
            -shared \
            -Wl,-soname,libtinyxml2.so.${PV} \
            -o libtinyxml2.so.${PV} \
            ${LDFLAGS} \
            tinyxml2.o \

}

do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${S}/libtinyxml2.so.${PV} ${D}${libdir}
    ln -sf libtinyxml2.so.${PV} ${D}${libdir}/libtinyxml2.so

    install -d ${D}${includedir}
    install -m 0644 ${S}/tinyxml2.h ${D}${includedir}
}

BBCLASSEXTEND += "native"

