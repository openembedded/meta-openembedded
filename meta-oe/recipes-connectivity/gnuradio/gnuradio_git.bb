DESCRIPTION = "GNU Radio"
URL = "http://gnuradio.org"
SECTION =  "apps"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "gsl fftwf python alsa-lib boost cppunit \
           swig-native python-numpy python-cheetah-native orc"

#Available PACKAGECONFIG options are qt grc uhd
PACKAGECONFIG ??= "qtgui grc uhd"

PACKAGECONFIG[uhd] = "-DENABLE_GR_UHD=ON,-DENABLE_GR_UHD=OFF,uhd,"
PACKAGECONFIG[grc] = "-DENABLE_GRC=ON,-DENABLE_GRC=OFF,python-pygtk python-cheetah, "

PACKAGECONFIG[qtgui] = "-DENABLE_GR_QTGUI=ON,-DENABLE_GR_QTGUI=OFF,qt4-x11-free qwt, "


inherit distutils-base cmake pkgconfig

export BUILD_SYS
export HOST_SYS="${MULTIMACH_TARGET_SYS}"

RDEPENDS_${PN} = "python-core python-audio python-threading python-codecs \
                  python-lang python-textutils python-shell python-pickle \
                  python-compiler python-pkgutil python-pydoc python-mmap \
                  python-netclient python-difflib \
                  python-pprint python-numpy  \
"
RDEPENDS_${PN}-grc = "python-pygtk python-lxml python-cheetah python-netserver"

C_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

do_configure_prepend() {
    ${BUILD_CC} ${S}/gr-vocoder/lib/codec2/generate_codebook.c -o ${S}/gr-vocoder/lib/generate_codebook -lm
    echo "ADD_EXECUTABLE(generate_codebook IMPORTED)" >${S}/gr-vocoder/lib/generate_codebook.txt
    echo "SET_PROPERTY(TARGET generate_codebook APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)" >>${S}/gr-vocoder/lib/generate_codebook.txt
    echo 'SET_TARGET_PROPERTIES(generate_codebook PROPERTIES IMPORTED_LOCATION_RELEASE "${S}/gr-vocoder/lib/generate_codebook")' >>${S}/gr-vocoder/lib/generate_codebook.txt
}

do_compile_prepend() {
    cp ${S}/gr-vocoder/lib/codec2/defines.h ${OECMAKE_BUILDPATH}/gr-vocoder/lib/codec2
}

PACKAGES += " \
    ${PN}-grc \
    ${PN}-examples \
"

FILES_${PN}-grc = "${datadir}/gnuradio/grc"
FILES_${PN}-examples = "${datadir}/gnuradio/examples"

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/gnuradio"
FILES_${PN} += "${datadir}/gnuradio/modtool"
# The following needs fixing upstream
FILES_${PN} += "${prefix}/etc/gnuradio"

FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/gnuradio/.debug \
                    ${PYTHON_SITEPACKAGES_DIR}/gnuradio/*/.debug \
                    ${datadir}/gnuradio/examples/*/.debug \
"

PV = "3.6.5"

FILESPATHPKG_prepend = "gnuradio-git:"

SRCREV = "a7b3a9339106aaba72195b693dfeaa98f194e0d5"

# Make it easy to test against developer repos and branches
GIT_REPO = "gnuradio.git"
GIT_BRANCH = "master"

SRC_URI = "git://git.gnuradio.org/${GIT_REPO};branch=${GIT_BRANCH} \
           file://0001-gr-trellis-Kill-docs-hard.patch \
"

S="${WORKDIR}/git"

OECMAKE_BUILDPATH = "${S}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DENABLE_GR_ATSC=FALSE \
                 -DENABLE_GR_FCD=OFF \
                 -DENABLE_GR_WXGUI=OFF \
                 -DENABLE_GR_VIDEO_SDL=OFF \
                 -DENABLE_SPHINX=OFF -DENABLE_DOXYGEN=OFF \
                 -DIMPORT_EXECUTABLES=${S}/gr-vocoder/lib/generate_codebook.txt \
                 -DQT_HEADERS_DIR=${STAGING_INCDIR}/qt4 \
                 -DQT_QTCORE_INCLUDE_DIR=${STAGING_INCDIR}/qt4/QtCore \
                 -DQT_LIBRARY_DIR=${STAGING_LIBDIR} \
                 -DQT_QTCORE_LIBRARY_RELEASE=${STAGING_LIBDIR}/libQtCore.so \
                 -DQT_QTGUI_LIBRARY_RELEASE=${STAGING_LIBDIR}/libQtGui.so \
                 ${@base_contains('TUNE_FEATURES', 'neon', \
                     '-Dhave_mfpu_neon=1', '-Dhave_mfpu_neon=0', d)} \
"

inherit distutils-base cmake pkgconfig

EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"

