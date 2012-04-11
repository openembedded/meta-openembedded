DESCRIPTION = "GNU Radio"
URL = "http://gnuradio.org"
SECTION =  "apps"
PRIORITY = "optional"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r0"

DEPENDS = "uhd gsl fftwf python alsa-lib boost cppunit \
           swig-native python-numpy python-pygtk orc qt4-x11-free qwt"

inherit distutils-base cmake pkgconfig

export BUILD_SYS
export HOST_SYS="${MULTIMACH_TARGET_SYS}"

RDEPENDS_${PN} = "python-core python-audio python-threading python-codecs \
                  python-lang python-textutils python-shell python-pickle \
                  python-compiler python-pkgutil python-pydoc python-mmap \
                  python-netclient python-difflib \
                  python-pprint python-numpy python-pygtk python-lxml \
                 "

C_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

#do_configure_append() {
#	find ${S} -name Makefile | xargs sed -i s:'-I${STAGING_INCDIR_NATIVE'::g
#	find ${S} -name Makefile | xargs sed -i s:'-L${STAGING_LIBDIR_NATIVE'::g
#}

do_configure_prepend() {
	${BUILD_CC} ${S}/gr-vocoder/lib/codec2/generate_codebook.c -o ${S}/gr-vocoder/lib/generate_codebook -lm
	echo "ADD_EXECUTABLE(generate_codebook IMPORTED)" >${S}/gr-vocoder/lib/generate_codebook.txt
	echo "SET_PROPERTY(TARGET generate_codebook APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)" >>${S}/gr-vocoder/lib/generate_codebook.txt
	echo 'SET_TARGET_PROPERTIES(generate_codebook PROPERTIES IMPORTED_LOCATION_RELEASE "${S}/gr-vocoder/lib/generate_codebook")' >>${S}/gr-vocoder/lib/generate_codebook.txt
}

do_compile_prepend() {
	cp ${S}/gr-vocoder/lib/codec2/defines.h ${OECMAKE_BUILDPATH}/gr-vocoder/lib/codec2
}

PACKAGES =+ "\
  ${PN}-examples \
  ${PN}-grc \
"

FILES_${PN}-grc = "${datadir}/gnuradio/grc"

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/gnuradio/*"
FILES_${PN} += "${datadir}/gnuradio/*"
# The following needs fixing upstream
FILES_${PN} += "${prefix}/etc/gnuradio/*"

FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/gnuradio/.debug \
                    ${PYTHON_SITEPACKAGES_DIR}/gnuradio/*/.debug \
		   "
FILES_${PN}-examples = "${datadir}/gnuradio/examples"

PV = "3.5.3"

FILESPATHPKG_prepend = "gnuradio-git:"

SRCREV = "f621a52f779381cfe550b4278b83e701a747944c"

# Make it easy to test against developer repos and branches
GIT_REPO = "gnuradio.git"
GIT_BRANCH = "master"

SRC_URI = "git://gnuradio.org/git/${GIT_REPO};branch=${GIT_BRANCH};protocol=http \
"

S="${WORKDIR}/git"

OECMAKE_BUILDPATH = "${S}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DENABLE_GR_ATSC=FALSE -DENABLE_GR_QTGUI=ON -DENABLE_GR_WXGUI=OFF -DENABLE_GR_VIDEO_SDL=OFF -DQT_HEADERS_DIR=${STAGING_INCDIR}/qt4 -DQT_QTCORE_INCLUDE_DIR=${STAGING_INCDIR}/qt4/QtCore -DQT_LIBRARY_DIR=${STAGING_LIBDIR} -DQT_QTCORE_LIBRARY_RELEASE=${STAGING_LIBDIR}/libQtCore.so -DQT_QTGUI_LIBRARY_RELEASE=${STAGING_LIBDIR}/libQtGui.so -DENABLE_GR_FCD=OFF -DIMPORT_EXECUTABLES=${S}/gr-vocoder/lib/generate_codebook.txt"

EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"

