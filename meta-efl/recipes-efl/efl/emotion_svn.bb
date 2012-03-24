DESCRIPTION = "The Enlightenment multimedia library"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=69f44058245ce5c596d56bb622d5dabd"
# we no longer build the libxine backend, since the gstreamer backend seems more promising
DEPENDS = "eet eeze evas ecore edje gstreamer gst-plugins-base"
PV = "0.2.0+svnr${SRCPV}"
PR = "r2"
SRCREV = "${EFL_SRCREV}"

inherit efl
SRC_URI = "${E_SVN}/trunk;module=${SRCNAME};proto=http;scmdata=keep"
S = "${WORKDIR}/${SRCNAME}"

EXTRA_OECONF = "--disable-xine --enable-gstreamer --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc"

PACKAGES =+ "emotion-backend-gstreamer"
FILES_emotion-backend-gstreamer = "${libdir}/emotion/*.so"
RRECOMMENDS_${PN} = "emotion-backend-gstreamer"
# upgrade path from libemotion0 to libemotion1
RREPLACES_${PN} = "libemotion0"
