SUMMARY = "Tool Command Language ToolKit Extension"
HOMEPAGE = "http://tcl.sourceforge.net"
SECTION = "devel/tcltk"

# http://www.tcl.tk/software/tcltk/license.html
LICENSE = "tcl"
LIC_FILES_CHKSUM = "file://${S}/../license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../compat/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../doc/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../library/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../macosx/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../tests/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../unix/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../win/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
    file://${S}/../xlib/license.terms;md5=c88f99decec11afa967ad33d314f87fe \
"

DEPENDS = "tcl virtual/libx11 libxt"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/tcl/${BPN}${PV}-src.tar.gz \
    file://confsearch.diff;striplevel=2 \
    file://non-linux.diff;striplevel=2 \
    file://tklibrary.diff;striplevel=2 \
    file://tkprivate.diff;striplevel=2 \
    file://fix-xft.diff \
    file://configure.use.fontconfig.with.xft.patch \
"
SRC_URI[md5sum] = "e3cf6290999ee30651d75864eccfec63"
SRC_URI[sha256sum] = "d3f9161e8ba0f107fe8d4df1f6d3a14c30cc3512dfc12a795daa367a27660dac"

S = "${WORKDIR}/${BPN}${PV}/unix"

# Short version format: "8.6"
VER = "${@os.path.splitext(d.getVar('PV'))[0]}"

LDFLAGS += "-Wl,-rpath,${libdir}/tcltk/${PV}/lib"
inherit autotools distro_features_check
# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"

EXTRA_OECONF = "\
    --enable-threads \
    --with-x \
    --with-tcl=${STAGING_BINDIR}/crossscripts \
    --libdir=${libdir} \
"
export TK_LIBRARY='${libdir}/tk${VER}'
do_install_append() {
    ln -sf libtk${VER}.so ${D}${libdir}/libtk${VER}.so.0
    oe_libinstall -so libtk${VER} ${D}${libdir}
    ln -sf wish${VER} ${D}${bindir}/wish
}

PACKAGECONFIG ??= "xft"
PACKAGECONFIG[xft] = "--enable-xft,--disable-xft,xft"
PACKAGECONFIG[xss] = "--enable-xss,--disable-xss,libxscrnsaver libxext"

PACKAGES =+ "${PN}-lib"

FILES_${PN}-lib = "${libdir}/libtk${VER}.so*"
FILES_${PN} += "${libdir}/tk*"

# isn't getting picked up by shlibs code
RDEPENDS_${PN} += "tk-lib"
RDEPENDS_${PN}_class-native = ""

BBCLASSEXTEND = "native nativesdk"

# Fix the path in sstate
SSTATE_SCAN_FILES += "*Config.sh"
