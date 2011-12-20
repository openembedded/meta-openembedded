DESCRIPTION = "Video player and streamer - davinci edition"
HOMEPAGE = "http://www.videolan.org"
LICENSE = "GPLv2"
SECTION = "multimedia"

DEPENDS = "libfribidi libtool libgcrypt gst-plugins-bad virtual/libsdl qt4-x11-free dbus libxml2 gnutls tremor faad2 ffmpeg flac libxpm \
           ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'libmad libid3tag liba52 mpeg2dec', d)}"

SRC_URI = "http://download.videolan.org/pub/videolan/vlc/${PV}/vlc-${PV}.tar.bz2"

inherit autotools gettext

ARM_INSTRUCTION_SET = "arm"

DEPENDS += "libdvdcss libdvdread lua5.1-native lua5.1"

LEAD_SONAME = "libvlc.so.5"

PACKAGES =+ "libvlc-dbg libvlc-dev libvlc"

FILES_libvlc-dev = "${libdir}/lib*.so"

FILES_libvlc-dbg = "\
	${libdir}/.debug \
	${libdir}/vlc/meta_engine/.debug \
	${libdir}/vlc/audio_filter/.debug ${libdir}/vlc/audio_output/.debug \
	${libdir}/vlc/demux/.debug ${libdir}/vlc/control/.debug \
	${libdir}/vlc/gui/.debug ${libdir}/vlc/packetizer/.debug \
	${libdir}/vlc/audio_mixer/.debug ${libdir}/vlc/stream_out/.debug \
	${libdir}/vlc/mux/.debug ${libdir}/vlc/access/.debug \
	${libdir}/vlc/visualization/.debug ${libdir}/vlc/access_filter/.debug \
	${libdir}/vlc/access_output/.debug ${libdir}/vlc/video_output/.debug \
	${libdir}/vlc/services_discovery/.debug ${libdir}/vlc/video_chroma/.debug \
	${libdir}/vlc/video_codec/.debug ${libdir}/vlc/video_filter/.debug \
	${libdir}/vlc/misc/.debug ${libdir}/vlc/codec/.debug \
	"

FILES_libvlc = "${libdir}/lib*.so.*"

FILES_${PN} += "${bindir}/vlc \
	${datadir}/applications \
	${datadir}/vlc/ \
	${datadir}/icons \
	"

FILES_${PN}-dbg	+= "${libdir}/vlc/*/.debug ${libdir}/vlc/plugins/*/.debug"

EXTRA_OECONF = "\
	--enable-dvdread \
	--enable-libtool \
	--with-contrib \
	--enable-run-as-root \
	--disable-wxwidgets \
	--enable-x11 --enable-xvideo \ 
	--disable-screen --disable-caca \
	--enable-httpd --enable-vlm \
	--enable-freetype \
	--enable-sdl \ 
	--enable-png \
	--enable-live555 --enable-tremor \
	--enable-v4l2 --enable-v4l --disable-aa --enable-wma --disable-faad \
	--enable-dbus \
	--disable-hal \	
	--without-contrib \
	--disable-opengl --disable-glx \
	--enable-realrtsp \
	ac_cv_path_MOC=${STAGING_BINDIR_NATIVE}/moc4 \
	ac_cv_path_RCC=${STAGING_BINDIR_NATIVE}/rcc4 \
	ac_cv_path_UIC=${STAGING_BINDIR_NATIVE}/uic4 \
"


do_configure() {
	cp ${STAGING_DATADIR}/aclocal/libgcrypt.m4 ${S}/m4/ 
	./bootstrap	
	gnu-configize --force
	libtoolize --force
	#autoreconf --force -i
	cp ${STAGING_DATADIR}/libtool/config.* ${S}/autotools/ || true
	oe_runconf
	rm config.log
	sed -i -e s:-L/usr/lib:-L${STAGING_LIBDIR}/:g vlc-config
	sed -i -e s:'$(MOC) $(DEFS) $(CPPFLAGS)':'$(MOC) $(DEFS)'\ -I${S}/include\ -DSYS_LINUX:g ${S}/modules/gui/qt4/Makefile
}

