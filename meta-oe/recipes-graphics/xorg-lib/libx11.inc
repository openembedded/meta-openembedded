require xorg-lib-common.inc

DESCRIPTION = "Base X libs."
COMMON_DEPENDS = "util-macros xtrans libxdmcp libxau \
                  bigreqsproto xproto xextproto xcmiscproto \
                  xf86bigfontproto kbproto inputproto xproto-native"

DEPENDS = "libxcb ${COMMON_DEPENDS}"		  
DEPENDS_virtclass-native = "${COMMON_DEPENDS}"
DEPENDS_virtclass-nativesdk = "${COMMON_DEPENDS}"

FILESPATHPKG .= ":libx11-${PV}:libx11"
BBCLASSEXTEND = "native nativesdk"
PROVIDES = "virtual/libx11"
PE = "1"

PACKAGES =+ "${PN}-xcb"

FILES_${PN} += "${datadir}/X11/XKeysymDB ${datadir}/X11/XErrorDB ${libdir}/X11/Xcms.txt"
FILES_${PN}-xcb += "${libdir}/libX11-xcb.so.*"
FILES_${PN}-locale += "${datadir}/X11/locale ${libdir}/X11/locale"

XORG_PN = "libX11"

EXTRA_OECONF += " --with-xcb --with-keysymdefdir=${STAGING_INCDIR}/X11 --with-groff=no --with-ps2pdf=no --with-fop=no"
EXTRA_OECONF_virtclass-native = " --without-xcb --with-groff=no --with-ps2pdf=no --with-fop=no"
EXTRA_OECONF_virtclass-nativesdk = " --without-xcb --with-groff=no --with-ps2pdf=no --with-fop=no"

# Below option is added to overcome the GCC bug on ARM
# see http://gcc.gnu.org/PR42981 for further details.
# We could potentially take it off when its fixed in gcc 4.5

CFLAGS_append_arm = " -fforward-propagate "

SRC_URI += " file://keysymdef_include.patch \
             file://x11_disable_makekeys.patch \
"

do_compile_prepend() {
	(
		unset CC LD CXX CCLD CFLAGS CPPFLAGS LDFLAGS CXXFLAGS
		cd src/util; 
		mv makekeys.c.orig makekeys.c || true
		# MIN_REHASH 10 is only in 1.0.1
		sed -i -e 's:MIN_REHASH 10:MIN_REHASH 16:g' makekeys.c
		sed -i -e 's:MIN_REHASH 15:MIN_REHASH 16:g' makekeys.c
		touch makekeys-makekeys.o ; ${BUILD_CC} ${BUILD_CFLAGS} -I${STAGING_INCDIR_NATIVE} makekeys.c -o makekeys
		# mv to stop it getting rebuilt
		mv makekeys.c makekeys.c.orig
		cd ../../
	) || exit 1
}
