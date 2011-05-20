HOMEPAGE = "http://www.x.org"
SECTION = "x11/base"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://COPYING;md5=3dd2bbe3563837f80ed8926b06c1c353"

INC_PR = "r19"

# xf86-*-* packages depend on an X server built with the xfree86 DDX
# so we have a virtual to represent that:
PROVIDES = "virtual/xserver-xf86"

# Other packages tend to just care that there is *an* X server:
PROVIDES += "virtual/xserver"

# Config manager for xserver, options are: hal, udev, empty (for configuration in xorg.conf)
DISTRO_XORG_CONFIG_MANAGER ?= "hal"
DISTRO_XORG_CONFIG_MANAGER_shr ?= "udev"

# default none, enabled by DISTRO_XORG_CONFIG_MANAGER setting
CONFIG_MANAGER_OPTION += "${@['--disable-config-hal','--enable-config-hal',''][bb.data.getVar('DISTRO_XORG_CONFIG_MANAGER',d) in ['hal']]}"
CONFIG_MANAGER_OPTION += "${@['--disable-config-udev','--enable-config-udev',''][bb.data.getVar('DISTRO_XORG_CONFIG_MANAGER',d) in ['udev']]}"

DEPENDS = "fontconfig freetype flex-native liblbxutil kbproto \ 
xf86driproto drm glproto randrproto renderproto fixesproto damageproto \
xcmiscproto xextproto xproto xf86miscproto xf86vidmodeproto xf86bigfontproto \
scrnsaverproto bigreqsproto resourceproto fontsproto inputproto \
xf86dgaproto videoproto compositeproto trapproto recordproto dmxproto \
resourceproto xineramaproto xtrans evieext libxkbfile libxfont libxau \
libfontenc libxdmcp libxxf86vm libxaw libxmu libxt libxpm libxext libx11 \
libxkbui libxxf86misc libxi libdmx libxtst libxres virtual/libgl libxcalibrate \
libxv ${DISTRO_XORG_CONFIG_MANAGER}"

RRECOMMENDS_${PN} = "libpciaccess"

XORG_PN = "xorg-server"
SRC_URI = "${XORG_MIRROR}/individual/xserver/${XORG_PN}-${PV}.tar.bz2"

S = "${WORKDIR}/${XORG_PN}-${PV}"

inherit autotools pkgconfig glx-use-tls

EXTRA_OECONF += " --enable-xcalibrate \
                  --enable-kdrive \
                  --enable-xephyr \
                  --disable-xfbdev \
                  --disable-xnest \
                  --disable-kdrive-vesa \
                  --with-default-font-path="/usr/share/fonts/X11/misc" \
                  ac_cv_file__usr_share_sgml_X11_defs_ent=no"

# CONFIG_MANAGER_OPTION is used only in recipes which support those options

EXTRA_OECONF_append_arm = " ac_cv_header_linux_apm_bios_h=no "

PACKAGES =+ "xserver-security-policy"
FILES_xserver-security-policy += "${libdir}/xserver/SecurityPolicy"
RRECOMMENDS_${PN} += "xserver-security-policy xkeyboard-config rgb xserver-xorg-conf"

PACKAGES =+ "${PN}-sdl ${PN}-fbdev ${PN}-xprint ${PN}-xvfb ${PN}-utils ${PN}-xephyr"

RDEPENDS_${PN}-xvfb += "xkeyboard-config"

FILES_${PN}-fbdev = "${bindir}/Xfbdev"
FILES_${PN}-sdl = "${bindir}/Xsdl"
FILES_${PN}-xprint = "${libdir}/X11/xserver/*/print"
FILES_${PN}-xvfb = "${bindir}/Xvfb"
FILES_${PN}-utils = "${bindir}/scanpci ${bindir}/pcitweak ${bindir}/ioport ${bindir}/in[bwl] ${bindir}/out[bwl] ${bindir}/mmap[rw] ${bindir}/gtf ${bindir}/getconfig ${bindir}/getconfig.pl"
FILES_${PN} = "${bindir} ${libdir}/X11/Options ${libdir}/X11/Cards ${libdir}/X11/getconfig ${libdir}/X11/etc ${libdir}/modules/*.so ${libdir}/xorg/modules/*.so /etc/X11 ${libdir}/xorg/protocol.txt ${datadir}/X11/xorg.conf.d"
FILES_${PN}-doc += "${libdir}/X11/doc /usr/share/X11/xkb/compiled/README.compiled"
FILES_${PN}-xephyr = "${bindir}/Xephyr"

FILES_${PN}-dbg += "${libdir}/xorg/modules/.debug \
                    ${libdir}/xorg/modules/*/.debug \
		    ${libdir}/xorg/modules/*/*/.debug \
		    "

# Split out some modules and extensions from the main package
# These aren't needed for basic operations and only take up space:
#  32.0k   libdri.so
#  336.0k  libglx.so
#  1360k   libint10.so
#  180.0k  libwfb.so
#  320.0k  libxaa.so
#  124.0k  libxf1bpp.so
#  84.0k   libxf4bpp.so

PACKAGES =+ "${PN}-extension-dri \
             ${PN}-extension-dri2 \
             ${PN}-extension-glx \
             ${PN}-module-libint10 \
             ${PN}-module-libafb \
             ${PN}-module-libwfb  \
             ${PN}-module-libmfb \
             ${PN}-module-libcfb \
             ${PN}-module-xaa \
             ${PN}-module-libxf1bpp \
             ${PN}-module-libxf4bpp"

FILES_${PN}-extension-dri = "${libdir}/xorg/modules/extensions/libdri.so"
FILES_${PN}-extension-dri2 = "${libdir}/xorg/modules/extensions/libdri2.so"
FILES_${PN}-extension-glx = "${libdir}/xorg/modules/extensions/libglx.so"
FILES_${PN}-module-libint10 = "${libdir}/xorg/modules/libint10.so"
FILES_${PN}-module-libafb = "${libdir}/xorg/modules/libafb.so"
FILES_${PN}-module-libwfb = "${libdir}/xorg/modules/libwfb.so"
FILES_${PN}-module-libmfb = "${libdir}/xorg/modules/libmfb.so"
FILES_${PN}-module-libcfb = "${libdir}/xorg/modules/libcfb.so"
FILES_${PN}-module-xaa = "${libdir}/xorg/modules/libxaa.so"
FILES_${PN}-module-libxf1bpp = "${libdir}/xorg/modules/libxf1bpp.so"
FILES_${PN}-module-libxf4bpp = "${libdir}/xorg/modules/libxf4bpp.so"

PACKAGES =+ "${PN}-multimedia-modules"

FILES_${PN}-multimedia-modules = "${libdir}/xorg/modules/multimedia/*drv*"

do_install_append () {
	ln -sf ${datadir}/fonts/X11 ${D}/${libdir}/X11/fonts
}
