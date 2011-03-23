DESCRIPTION = "Enjoy music player"
LICENSE = "LGPLv3"
DEPENDS = "evas ecore edje elementary emotion lightmediascanner"
SRCREV = "${EFL_SRCREV}"
PV = "0.0+svnr${SRCPV}"
PR = "r4"

DEPENDS += "gst-plugins-good ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'gst-plugins-ugly', d)}"

#1st needed for all formats
#2nd needed for mp3 playback
#3d needed for ogg playback
#4th needed for flac playback
#5th needed binary to create db
RDEPENDS += "\
	gst-plugin-typefindfunctions gst-plugin-playbin gst-plugin-volume gst-plugin-decodebin2  \
	${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'gst-plugin-mad gst-plugin-id3demux', d)} \
	gst-plugin-ogg gst-plugin-ivorbisdec \
	gst-plugin-flac \
	lightmediascanner-test \
	"

inherit e
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

EXTRA_OECONF = "\
  --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"
EXTRA_OECONF_append_shr = "\
  --enable-fso \
"

pkg_postinst_${PN} () {
	echo "enjoy:	SCAN and LIBRARY MANAGER are not implemeted yet!"
	echo "enjoy:	Meanwhile please run:"
	echo "enjoy:	test-lms -m mono -p id3 -i 5000 -s /path/to/your/music/dir /home/root/.config/enjoy/media.db"
	echo "enjoy:"
	echo "enjoy:	Use test-lms -P to see available formats that can be scanned"
}
