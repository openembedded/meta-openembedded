DESCRIPTION = "SIP is a C++/Python Wrapper Generator"
AUTHOR = "Phil Thompson"
HOMEPAGE = "http://www.riverbankcomputing.co.uk/sip"
SECTION = "devel"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://sipgen.sbf;endline=15;md5=88e887a386c18a01240d8d055da4e441"

SRC_URI = "http://www.riverbankcomputing.co.uk/static/Downloads/sip4/sip-${PV}.tar.gz"
SRC_URI[md5sum] = "76192829cc42ec558db46e4f9e1d8ba9"
SRC_URI[sha256sum] = "ec295f71ef339c5b98db5650865f2c6c1200c4085b7a3f33f284111e1f534ac1"
S = "${WORKDIR}/sip-${PV}/sipgen"
PR = "r1"

inherit qmake2 native python-dir

EXTRA_QMAKEVARS_POST += "DESTDIR=${S} CONFIG=console"

export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

do_configure_prepend() {
        cat sipgen.sbf | sed s,target,TARGET, | sed s,sources,SOURCES, | sed s,headers,HEADERS, > sipgen.pro
}
do_install() {
        install -d ${D}${bindir}
        install -m 0755 sip ${D}${bindir}/sip
        # python-pyqt expects sip4
        ln -sf sip ${D}${bindir}/sip4
        cd ${WORKDIR}/sip-${PV} && python configure.py
        install -d ${D}${PYTHON_SITEPACKAGES_DIR}
        install -m 0755 sip*.py ${D}${PYTHON_SITEPACKAGES_DIR}
}
