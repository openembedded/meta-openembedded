DESCRIPTION = "TigerVNC remote display system"
HOMEPAGE = "http://www.tigervnc.com/"
LICENSE = "GPL-2.0-or-later"
SECTION = "x11/utils"
DEPENDS = "gettext-native xserver-xorg gnutls nettle jpeg pixman libxtst fltk libpam libx11 libxdamage libxfixes libxrandr"
RDEPENDS:${PN} = "coreutils hicolor-icon-theme perl bash xkbcomp"

LIC_FILES_CHKSUM = "file://LICENCE.TXT;md5=75b02c2872421380bbd47781d2bd75d3"

S = "${WORKDIR}/git"

inherit autotools cmake features_check pkgconfig systemd

REQUIRED_DISTRO_FEATURES = "x11 pam"

# For ease we do in-tree builds right now. It should be possible to do
# out-of-tree builds.
B = "${S}"

SRCREV = "1b4af5c586eb7a30a38c82fd088c1fa47a83e72f"

SRC_URI = "git://github.com/TigerVNC/tigervnc.git;branch=1.14-branch;protocol=https \
           file://0001-do-not-build-tests-sub-directory.patch \
           file://0002-add-missing-dynamic-library-to-FLTK_LIBRARIES.patch \
           file://0003-tigervnc-add-fPIC-option-to-COMPILE_FLAGS.patch \
"

# Keep sync with xorg-server in oe-core
XORG_PN ?= "xorg-server"
XORG_PV ?= "21.1.15"
SRC_URI += "${XORG_MIRROR}/individual/xserver/${XORG_PN}-${XORG_PV}.tar.xz;name=xorg"
XORG_S = "${UNPACKDIR}/${XORG_PN}-${XORG_PV}"
SRC_URI[xorg.sha256sum] = "841c82901282902725762df03adbbcd68153d4cdfb0d61df0cfd73ad677ae089"

# It is the directory containing the Xorg source for the
# machine on which you are building TigerVNC.
XSERVER_SOURCE_DIR="${S}/unix/xserver"
AUTOTOOLS_SCRIPT_PATH = "${XSERVER_SOURCE_DIR}"

do_patch[postfuncs] += "do_patch_xserver"
do_patch_xserver () {
    # Put the xserver source in the right place in the tigervnc source tree
    cp -rfl ${XORG_S}/* ${XSERVER_SOURCE_DIR}
    # Apply the patch to integrate the vnc server
    patch -p1 -b --suffix .vnc --directory ${XSERVER_SOURCE_DIR} <${S}/unix/xserver21.patch
}

EXTRA_OECONF = "--disable-xorg --disable-xnest --disable-xvfb \
        --disable-xwin --disable-xephyr --disable-kdrive --with-pic \
        --disable-static --disable-xinerama \
        --with-xkb-output=${localstatedir}/lib/xkb \
        --disable-glx --disable-dri --disable-dri2 \
        --disable-config-hal \
        --disable-config-udev \
        --without-dtrace \
        --disable-unit-tests \
        --disable-devel-docs \
        --disable-selective-werror \
        --disable-xshmfence \
        --disable-config-udev \
        --disable-dri3 \
        --disable-libunwind \
        --without-xmlto \
        --enable-systemd-logind=no \
        --disable-xinerama \
"

EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DCMAKE_INSTALL_UNITDIR=${systemd_system_unitdir}', '-DINSTALL_SYSTEMD_UNITS=OFF', d)}"

do_configure:append () {
    autotools_do_configure
}

do_compile:append () {
    oe_runmake
}

do_install:append() {
    oe_runmake -C ${B}/hw/vnc 'DESTDIR=${D}' install
}

FILES:${PN} += " \
    ${libdir}/xorg/modules/extensions \
    ${datadir}/icons \
    ${datadir}/metainfo \
"

SYSTEMD_SERVICE:${PN} = "vncserver@.service"
