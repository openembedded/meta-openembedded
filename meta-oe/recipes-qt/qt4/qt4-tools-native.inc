DESCRIPTION = "Native tools for Qt/[X11|Mac|Embedded] version 4.x"
DEPENDS = "zlib-native dbus-native"
SECTION = "libs"
HOMEPAGE = "http://qt.nokia.com"
PRIORITY = "optional"

LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=fbc093901857fcd118f065f900982c24 \
                    file://LICENSE.GPL3;md5=babc5b6b77441da277f5c06b2e547720 \
                    file://LGPL_EXCEPTION.txt;md5=411080a56ff917a5a1aa08c98acae354"

INC_PR = "r11"

inherit native

SRC_URI = "http://get.qt.nokia.com/qt/source/qt-everywhere-opensource-src-${PV}.tar.gz \
           file://qt-config.patch \
           file://g++.conf \
           file://linux.conf"
S = "${WORKDIR}/qt-everywhere-opensource-src-${PV}"

EXTRA_OECONF = "-prefix ${prefix} \
                -L ${STAGING_LIBDIR_NATIVE} \
                -I ${STAGING_INCDIR_NATIVE} \
                -qt-libjpeg -qt-gif -system-zlib \
                -no-libjpeg -no-libpng -no-libmng -no-libtiff \
                -no-accessibility \
                -no-cups \
                -no-exceptions  \
                -no-nas-sound \
                -no-nis -no-openssl \
                -verbose -release -static \
                -embedded -no-freetype -no-glib -no-iconv \
                -qt3support"

# yank default -e, otherwise we get the following error:
# moc_qbuffer.cpp: No such file or directory
EXTRA_OEMAKE = " "

do_configure() {
   (echo o; echo yes) | ./configure ${EXTRA_OECONF} || die "Configuring qt failed. EXTRA_OECONF was ${EXTRA_OECONF}"
}

TOBUILD = "\
  src/tools/moc \
  src/corelib \
  src/sql \
  src/dbus \
  src/qt3support \
  src/xml \
  src/tools/uic \
  src/tools/rcc \
  src/network \
  tools/linguist/lrelease \
  tools/linguist/lupdate \
"

do_compile() {
    for i in ${TOBUILD}; do
        cd ${S}/$i && oe_runmake CC="${CC}" CXX="${CXX}"
    done
}

do_install() {
    install -d ${D}${bindir}/
    install -m 0755 bin/qmake ${D}${bindir}/qmake2
    for i in moc uic rcc lrelease lupdate; do
        install -m 0755 bin/${i} ${D}${bindir}/${i}4
    done
    
    install -d ${D}${datadir}/qt4/
    cp -PfR mkspecs ${D}${datadir}/qt4/
    ln -sf linux-g++ ${D}${datadir}/qt4/mkspecs/${BUILD_OS}-oe-g++
    cp -f ${WORKDIR}/g++.conf ${WORKDIR}/linux.conf ${D}${datadir}/qt4/mkspecs/common/

    install -m 0644 tools/porting/src/q3porting.xml ${D}${datadir}/qt4/
}
