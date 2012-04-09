DESCRIPTION = "XBMC Media Center"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=6eb631b6da7fdb01508a80213ffc35ff"

DEPENDS = "libusb1 libcec libplist expat yajl gperf-native libxmu fribidi mpeg2dec ffmpeg samba fontconfig curl python libass libmodplug libmicrohttpd wavpack libmms cmake-native libsdl-image libsdl-mixer virtual/egl mysql5 sqlite3 libmms faad2 libcdio libpcre boost lzo enca avahi libsamplerate0 libxinerama libxrandr libxtst bzip2 virtual/libsdl jasper zip-native zlib"
#require recipes/egl/egl.inc

SRCREV = "1d975a04c6686b9a82f63a4c605a5c5c5f84d0e8"

PV = "11.0"
PR = "r13"
PR_append = "+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;branch=eden;protocol=git \
           file://0001-configure-don-t-run-python-distutils-to-find-STAGING.patch \
           file://0002-Revert-fixed-ios-Add-memory-barriers-to-atomic-Add-S.patch \
           file://0003-Revert-fixed-ios-Add-memory-barriers-to-cas-assembly.patch \
          "

inherit autotools gettext python-dir

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
 --disable-rpath \
 --enable-gles \
 --enable-libusb \
 --enable-airplay \
 --disable-optical-drive \
 --enable-external-libraries \
"

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export PYTHON_DIR

do_configure() {
	sh bootstrap
	oe_runconf
}

PARALLEL_MAKE = ""

do_compile_prepend() {

	sed -i -e 's:-rpath \$(libdir)::g' lib/libid3tag/libid3tag/Makefile

	for i in $(find . -name "Makefile") ; do
		sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir):-rpath ${STAGING_LIBDIR}:g' $i
	done

	for i in $(find . -name "*.mak*") ; do
		sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir)::g' $i
	done
	sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir)::g' ${S}/Makefile	
}

# on ARM architectures xbmc will use GLES which will make the regular wrapper fail, so start it directly
do_install_append_arm() {
	sed -i -e 's:Exec=xbmc:Exec=${libdir}/xbmc/xbmc.bin:g' ${D}${datadir}/applications/xbmc.desktop
}

FILES_${PN} += "${datadir}/xsessions ${datadir}/icons"
FILES_${PN}-dbg += "${libdir}/xbmc/.debug ${libdir}/xbmc/*/.debug ${libdir}/xbmc/*/*/.debug ${libdir}/xbmc/*/*/*/.debug"

# xbmc uses some kind of dlopen() method for libcec so we need to add it manually
RRECOMMENDS_${PN}_append = " libcec \
                             python \
                             python-lang \
                             python-re \
                             "
RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-ibm850 glibc-gconv-ibm850"


