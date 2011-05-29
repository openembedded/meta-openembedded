DESCRIPTION = "Navit is a car navigation system with routing engine."
LICENSE = "GPLv2"
# taken from main navit package
LIC_FILES_CHKSUM = "file://${WORKDIR}/GPL-2;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "x11/applications"
DEPENDS = "glib-2.0 gtk+ imagemagick-native librsvg-native"

#only icons present in the package
PACKAGE_ARCH = "all"

PV = "0.1.0+svnr${SRCPV}"
PR = "r2"

EXTRA_OECONF = "--disable-binding-python --disable-gui-sdl --disable-samplemap --enable-avoid-float --enable-avoid-unaligned  --enable-svg2png-scaling-flag=32 --disable-speech-speech-dispatcher"

S = "${WORKDIR}/xpm"

inherit autotools

FILES_${PN} = " /usr/share/navit/xpm/"

SRCREV = "3915"

#use different URL than navit_svn does, to prevent upgrade/downgrade cycle in downloads dir
SRC_URI = "svn://anonymous@navit.svn.sourceforge.net/svnroot/navit/trunk/navit/navit;module=xpm;proto=https \
           file://GPL-2 \
           file://configure.in"

do_configure_prepend() {
  cp ${WORKDIR}/configure.in ${S}/
  # replace include with just xpmdir variable
  sed -i 's#.*Makefile.inc.*#xpmdir=$(pkgdatadir)/xpm#g' ${S}/Makefile.am
  # don't install desktopfile and icons
  sed -i 's/^\(EXTRADIST.*\) $(DESKTOPFILE_DATA) $(ICON128_DATA) $(ICON22_DATA) \(.*\)$/\1\2/g' ${S}/Makefile.am
}
