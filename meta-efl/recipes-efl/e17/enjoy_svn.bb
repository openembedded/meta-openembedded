DESCRIPTION = "Enjoy music player"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=0f507c68d42d1cc0fcb507d007112bf2"
DEPENDS = "evas ecore edje elementary emotion lightmediascanner"
SRCREV = "${EFL_SRCREV}"
PV = "0.0+svnr${SRCPV}"

DEPENDS += "gst-plugins-good ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'gst-plugins-ugly', d)}"

#1st needed for all formats
#2nd needed for mp3 playback
#3d needed for ogg playback
#4th needed for flac playback
#5th needed binary to create db
RDEPENDS += "\
       gst-plugins-base-typefindfunctions gst-plugins-base-playbin gst-plugins-base-volume gst-plugins-base-decodebin2 gst-plugins-good-autodetect \
       ${@base_conditional('ENTERPRISE_DISTRO', '1', '', 'gst-plugins-ugly-mad gst-plugins-good-id3demux', d)} \
       gst-plugins-base-ogg gst-plugins-base-ivorbisdec \
       gst-plugins-good-flac \
       lightmediascanner-test \
       "

inherit e gettext
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

FILES_${PN} += "${datadir}/icons/"

EXTRA_OECONF = "\
  --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"

do_configure_prepend() {
       autopoint || touch config.rpath
}

pkg_postinst_${PN} () {
	echo "enjoy:	SCAN and LIBRARY MANAGER are not implemeted yet!"
	echo "enjoy:	Meanwhile please run:"
	echo "enjoy:	test-lms -m mono -p id3 -i 5000 -s /path/to/your/music/dir /home/root/.config/enjoy/media.db"
	echo "enjoy:"
	echo "enjoy:	Use test-lms -P to see available formats that can be scanned"
}
