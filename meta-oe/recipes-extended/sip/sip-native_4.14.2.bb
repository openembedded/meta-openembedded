DESCRIPTION = "SIP is a C++/Python Wrapper Generator"
AUTHOR = "Phil Thompson"
HOMEPAGE = "http://www.riverbankcomputing.co.uk/sip"
SECTION = "devel"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://sipgen.sbf;endline=15;md5=61b2ce7ddd624968411804d2fa9d776c"

# riverbankcomputing is upstream, but keeps only latest version, sf usually have few older
#SRC_URI = "http://www.riverbankcomputing.com/static/Downloads/sip4/sip-${PV}.tar.gz"
SRC_URI = "${SOURCEFORGE_MIRROR}/project/pyqt/sip/sip-${PV}/sip-${PV}.tar.gz"
SRC_URI[md5sum] = "b93442e745b3be2fad89de0686a76ce9"
SRC_URI[sha256sum] = "1a9d3bf26c821f369c175f8e68946b79bc994da4f96e8f5ecff06e6ee7ac0528"
S = "${WORKDIR}/sip-${PV}/sipgen"

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
