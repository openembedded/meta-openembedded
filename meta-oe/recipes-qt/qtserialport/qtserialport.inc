DESCRIPTION = "Qt Serial Port Library"
SECTION = "qt/libs"
HOMEPAGE = "http://qt-project.org/wiki/QtSerialPort"

LICENSE = "LGPL FDL GPL "
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=3801d7932fdc07fd9efe89f9854a6caa \
    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.LGPL;md5=4fbd65380cdd255951079008b364516c \
    "

inherit qt4e pkgconfig

SRC_URI = " \
    git://gitorious.org/qt/qtserialport.git \
"

S = "${WORKDIR}/git"
B = "${WORKDIR}/${BPN}-${PV}"

QMAKE_PROFILES="${S}/qtserialport.pro"
EXTRA_QMAKEVARS_PRE += "PREFIX=/usr"
EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

do_compile_append() {
 	BOGUS_PREFIX=`qmake2 -query QT_INSTALL_PREFIX`
	for f in `find ${B} -name "Makefile"` ;do 
		sed -i -e "s|\(\$(INSTALL_ROOT)\)${BOGUS_PREFIX}|\1${prefix}|g" ${f}
	done
}

do_install() {
    oe_runmake install DESTDIR=${D} INCLUDEDIR=${includedir}
}
