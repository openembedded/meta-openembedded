SUMMARY = "GNU All Mobile Managment Utilities"
SECTION = "console/network"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
HOMEPAGE = "https://wammu.eu/"

SRC_URI = "https://github.com/gammu/gammu/releases/download/${PV}/Gammu-${PV}.tar.gz \
    file://gammurc \
    file://gammu-smsdrc \
"

S = "${UNPACKDIR}/Gammu-${PV}"

SRC_URI[sha256sum] = "92478c1c6d8df193f3d5e36648fd05fc3eff26254034f025cad2a0ffba5aec23"

UPSTREAM_CHECK_URI = "https://github.com/gammu/gammu/releases"

DEPENDS = "cmake-native virtual/libiconv libdbi mysql5 glib-2.0 udev libgudev unixodbc"

inherit cmake gettext

do_install:append() {
    # these files seem to only be used by symbian and trigger QA warnings
    rm -rf ${D}/usr/share/gammu
    #install default configuration files
    install -d ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/gammurc ${D}${sysconfdir}/gammurc
    install -m 0644 ${UNPACKDIR}/gammu-smsdrc ${D}${sysconfdir}/gammu-smsdrc
}

EXTRA_OECONF = " \
    --enable-shared \
    --enable-backup \
    --enable-protection \
"

EXTRA_OECMAKE = " \
    -DWITH_CURL=OFF \
    -DWITH_BLUETOOTH=OFF \
    -DWITH_NOKIA_SUPPORT=OFF \
    -DWITH_IRDA=OFF \
    -DWITH_MySQL=ON \
    -DWITH_Postgres=OFF \
"

PACKAGES =+ "${PN}-smsd libgammu libgsmsd"

FILES:${PN} = "${bindir}/gammu ${bindir}/jadmaker ${sysconfdir}/bash_completion.d/gammu \
    ${bindir}/gammu-detect ${sysconfdir}/gammurc"
CONFFILES:${PN} = "${sysconfdir}/gammurc"
FILES:${PN}-smsd = "${bindir}/gammu-smsd* ${sysconfdir}/gammu-smsdrc"
CONFFILES:${PN}-smsd = "${sysconfdir}/gammu-smsdrc"
FILES:${PN}-dev += "${bindir}/gammu-config ${libdir}/*.so"
FILES:${PN}-dbg += "${bindir}/.debug ${libdir}/.debug"
FILES:libgammu = "${libdir}/libGammu.so.*"
FILES:libgsmsd = "${libdir}/libgsmsd.so.*"

RDEPENDS:${PN} += "bash"
RDEPENDS:${PN}-dev += "bash"

# Fails to build with thumb-1 (qemuarm)
# gammu-1.32.0/libgammu/service/sms/gsmems.c:542:1: internal compiler error: in patch_jump_insn, at cfgrtl.c:1275
# |  }
# |  ^
# | Please submit a full bug report,
# | with preprocessed source if appropriate.
# | See <http://gcc.gnu.org/bugs.html> for instructions.
# | make[2]: *** [libgammu/CMakeFiles/libGammu.dir/service/sms/gsmems.o] Error 1
# | make[2]: *** Waiting for unfinished jobs....
ARM_INSTRUCTION_SET = "arm"
