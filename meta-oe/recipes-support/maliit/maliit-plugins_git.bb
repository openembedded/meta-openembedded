SUMMARY = "Plugins for a virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f29b21caa8e460097bfad9c026a33621"

inherit autotools qt4x11

PNBLACKLIST[maliit-plugins] ?= "BROKEN: depends on broken maliit-framework"

DEPENDS = "maliit-framework"

RDEPENDS_${PN} += "qt4-plugin-iconengine-svgicon qt4-plugin-imageformat-svg"

SRC_URI = "git://github.com/maliit/plugins.git;branch=master"

SRCREV = "0760e585df494b394df1b887e5138ffef19c481f"
PV = "0.92.3+git${SRCPV}"

EXTRA_QMAKEVARS_PRE = "\
    PREFIX=${prefix} \
    LIBDIR=${libdir} \
    CONFIG+=nodoc \
"

FILES_${PN} += "\
    ${libdir}/maliit \
    ${datadir} \
"

FILES_${PN}-dbg += "${libdir}/maliit/plugins-*/.debug"

S= "${WORKDIR}/git"

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"
