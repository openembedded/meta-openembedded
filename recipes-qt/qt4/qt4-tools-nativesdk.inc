DESCRIPTION = "SDK tools for Qt/[X11|Mac|Embedded] version 4.x"
DEPENDS = "zlib-nativesdk dbus-nativesdk libx11-nativesdk qt4-tools-native"
SECTION = "libs"
HOMEPAGE = "http://qt.nokia.com"
PRIORITY = "optional"
LICENSE = "LGPLv2.1 | GPLv3"

INC_PR = "r3"

FILESPATHPKG =. "qt-${PV}:"
inherit nativesdk qmake2

SRC_URI = "http://get.qt.nokia.com/qt/source/qt-everywhere-opensource-src-${PV}.tar.gz \
           file://configure-lflags.patch \
           file://compile.test-lflags.patch \
           file://qt-config.patch \
           file://qmake-exists-check.patch \
           file://configure-paths.patch \
           file://g++.conf \
           file://linux.conf"

S = "${WORKDIR}/qt-everywhere-opensource-src-${PV}"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=fbc093901857fcd118f065f900982c24 \
                    file://LICENSE.GPL3;md5=babc5b6b77441da277f5c06b2e547720 \
                    file://LGPL_EXCEPTION.txt;md5=411080a56ff917a5a1aa08c98acae354"

# FIXME: make it work with "${STAGING_BINDIR_NATIVE}/pkg-config --cflags dbus-1"
EXTRA_OECONF = "-prefix ${prefix} \
                -qt-libjpeg -qt-gif -system-zlib \
                -no-libjpeg -no-libpng -no-libmng -no-libtiff \
                -no-accessibility \
                -no-cups \
                -no-exceptions  \
                -no-nas-sound \
                -no-nis -no-openssl \
                -verbose -release -fast -static \
                -platform ${TARGET_OS}-oe-g++ \
                -xplatform ${TARGET_OS}-oe-g++ \
                -embedded -no-freetype -no-glib -no-iconv \
                -qt3support \
                -I${STAGING_DIR_HOST}${SDKPATHNATIVE}/usr/include \
                -I${STAGING_DIR_HOST}${SDKPATHNATIVE}/usr/include/freetype2 \
                -I${STAGING_DIR_HOST}${SDKPATHNATIVE}/usr/include/dbus-1.0 \
                -I${STAGING_DIR_HOST}${SDKPATHNATIVE}/usr/lib/dbus-1.0/include \
                -I${STAGING_DIR_HOST}${SDKPATHNATIVE}/usr/include/c++ \
                -I${STAGING_DIR_HOST}${SDKPATHNATIVE}/usr/include/c++/${TARGET_SYS} \
                -L${STAGING_DIR_HOST}${SDKPATHNATIVE}/lib \
                -L${STAGING_DIR_HOST}${SDKPATHNATIVE}/usr/lib"

# yank default -e, otherwise we get the following error:
# moc_qbuffer.cpp: No such file or directory
EXTRA_OEMAKE = " "

do_configure() {
    if [ ! -e bin/qmake ]; then
        ln -sf ${STAGING_BINDIR_NATIVE}/qmake2 bin/qmake
    fi

    if [ ! -e mkspecs/${TARGET_OS}-oe-g++ ]; then
        ln -sf linux-g++ mkspecs/${TARGET_OS}-oe-g++
    fi

    cp ../g++.conf mkspecs/common
    cp ../linux.conf mkspecs/common

    (echo o; echo yes) | ./configure ${EXTRA_OECONF} || die "Configuring qt failed. EXTRA_OECONF was ${EXTRA_OECONF}"
}

TOBUILD = "\
  src/tools/bootstrap \
  src/tools/moc \
  src/corelib \
  src/sql \
  src/dbus \
  src/qt3support \
  src/xml \
  src/tools/uic \
  src/tools/rcc \
  src/network \
  src/gui \
  src/tools/uic3 \
  tools/linguist/lrelease \
  tools/linguist/lupdate \
  tools/qdbus \
"

do_compile() {
    for i in ${TOBUILD}; do
        cd ${S}/$i && oe_runmake CC="${CC}" CXX="${CXX}"
    done
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 bin/qmake ${D}${bindir}/qmake2
    for i in moc uic uic3 rcc lrelease lupdate qdbuscpp2xml qdbusxml2cpp; do
        install -m 0755 bin/${i} ${D}${bindir}/${i}4
    done

    (cd ${D}${bindir}; \
    ln -s qmake2 qmake; \
    for i in moc uic uic3 rcc lrelease lupdate qdbuscpp2xml qdbusxml2cpp; do \
        ln -s ${i}4 ${i}; \
    done)

    # make a symbolic link to mkspecs for compatibility with Nokia's SDK
    # and QTCreator
    (cd ${D}${bindir}/..; ln -s ${TARGET_SYS}/usr/share/qtopia/mkspecs mkspecs;)
}

