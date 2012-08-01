DESCRIPTION = "Runtime helper for sip-generated python wrapper libraries"
SECTION = "devel/python"
HOMEPAGE = "http://www.riverbankcomputing.co.uk/sip"
AUTHOR = "Phil Thompson"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://siplib.sbf.in;endline=15;md5=e0e7b5d0c4656666df09826ea1d2071c"
DEPENDS = "python"
RDEPENDS_${PN} = "python-core"

SRC_URI = "http://www.riverbankcomputing.com/static/Downloads/sip4/sip-${PV}.tar.gz"

S = "${WORKDIR}/sip-${PV}/siplib"

inherit qt4x11 distutils-base

EXTRA_QMAKEVARS_POST += " TEMPLATE=lib \
                         CONFIG=console \
                         DESTDIR= \
                         VERSION=1.0.0 \
                         TARGET=sip \
                         DEFINES=SIP_QT_SUPPORT \
                         INCLUDEPATH+=. \
                         INCLUDEPATH+=${STAGING_INCDIR}/${PYTHON_DIR} \
                         INCLUDEPATH+=${STAGING_INCDIR}"


do_configure_prepend() {
	cat siplib.sbf.in | sed s,target,TARGET, | sed s,sources,SOURCES, | sed s,headers,HEADERS, | sed s,@CFG_MODULE_BASENAME@,sip, > siplib.pro
	cat siplib.c.in | sed s,@CFG_MODULE_BASENAME@,sip, > siplib.c
	cat sip.h.in | sed -e s,@CFG_MODULE_NAME@,sip,g > sip.h
}

do_install() {
	install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/
	install -m 0755 libsip.so.1.0.0 ${D}${libdir}/${PYTHON_DIR}/site-packages/sip.so
	# sipconfig.py sipdistutils.py
	install -d ${D}${includedir}
	install -m 0644 ../siplib/sip.h ${D}${includedir}/sip.h
}

FILES_${PN} = "${libdir}/${PYTHON_DIR}/site-packages/sip.so"

