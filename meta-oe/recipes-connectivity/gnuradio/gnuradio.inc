DESCRIPTION = "GNU Radio"
URL = "http://gnuradio.org"
SECTION =  "apps"
PRIORITY = "optional"
LICENSE = "GPLv3"
DEPENDS = "uhd gsl guile-native fftwf python alsa-lib boost cppunit swig-native python-numpy"
INC_PR = "r8"

inherit distutils-base autotools pkgconfig

export BUILD_SYS
export HOST_SYS=${MULTIMACH_TARGET_SYS}

EXTRA_OECONF = " \
  --program-prefix= \
  --disable-gr-atsc \
  --enable-gr-audio-alsa  \
  --disable-gr-audio-jack \
  --disable-html-docs \
  --with-boost=${STAGING_DIR_TARGET}/usr \
  --disable-usrp1 \
  --disable-usrp2 \
  --with-pythondir=${PYTHON_SITEPACKAGES_DIR} \
  PYTHON_CPPFLAGS=-I${STAGING_INCDIR}/${PYTHON_DIR} \
"

RDEPENDS_${PN} = "python-core python-audio python-threading python-codecs python-lang python-textutils \
                  python-shell python-pickle python-compiler python-pkgutil python-pydoc python-mmap \
                  python-netclient python-unittest python-difflib python-pprint python-numpy \
"

PACKAGES =+ "\
  ${PN}-examples \
  ${PN}-grc \
  ${PN}-conf \
"

FILES_${PN}-examples = "${datadir}/gnuradio/examples"
FILES_${PN}-grc = "${datadir}/gnuradio/grc"
FILES_${PN}-conf = "${sysconfdir}/gnuradio"

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/gnuradio/*"
FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/gnuradio/.debug \
                    ${PYTHON_SITEPACKAGES_DIR}/gnuradio/*/.debug \
		   "

