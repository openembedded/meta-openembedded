DESCRIPTION = "Ecore is the Enlightenment application framework library"
LICENSE = "MIT BSD"
DEPENDS = "gettext-native virtual/libiconv tslib curl eet evas glib-2.0 \
           libxtst libxcomposite libxinerama libxscrnsaver libxdamage libxrandr libxcursor libxprintutil"
DEPENDS_virtclass-native = "eet-native evas-native"	   
# optional
# DEPENDS += "directfb virtual/libsdl openssl virtual/libiconv"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

do_configure_prepend() {
	touch ${S}/po/Makefile.in.in || true
	sed -i -e 's: po::g' ${S}/Makefile.am
}

FILESPATHPKG =. "${BPN}-${PV}:${BPN}:"

PACKAGES =+ "\
    ${PN}-con \
    ${PN}-config \
    ${PN}-desktop \
    ${PN}-directfb \
    ${PN}-sdl \
    ${PN}-evas \
    ${PN}-fb \
    ${PN}-file \
    ${PN}-imf-evas \
    ${PN}-imf \
    ${PN}-ipc \
    ${PN}-job \
    ${PN}-txt \
    ${PN}-x \
    ${PN}-input \
    "
# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

RREPLACES_${PN} = "libecore-ver-pre-svn-00-0 libecore-ver-pre-svn-01-0"
REPLACES_${PN}-tests = "libecore-ver-pre-svn-01-tests"
RREPLACES_${PN}-con = "libecore-ver-pre-svn-01-con libecore-con-ver-pre-svn-01-0"
RREPLACES_${PN}-config = "libecore-config-ver-pre-svn-01-0"
RREPLACES_${PN}-desktop = "libecore-desktop-ver-pre-svn-01-0"
RREPLACES_${PN}-directfb = "libecore-directfb-ver-pre-svn-01-0"
RREPLACES_${PN}-sdl = "libecore-sdl-ver-pre-svn-01-0"
RREPLACES_${PN}-evas = "libecore-evas-ver-pre-svn-01-0"
RREPLACES_${PN}-fb = "libecore-fb-ver-pre-svn-01-0"
RREPLACES_${PN}-file = "libecore-file-ver-pre-svn-01-0"
RREPLACES_${PN}-imf-evas = "libecore-imf-evas-ver-pre-svn-01-0"
RREPLACES_${PN}-imf = "libecore-imf-ver-pre-svn-01-0"
RREPLACES_${PN}-ipc = "libecore-ipc-ver-pre-svn-01-0"
RREPLACES_${PN}-job = "libecore-job-ver-pre-svn-01-0"
RREPLACES_${PN}-txt = "libecore-txt-ver-pre-svn-01-0"
RREPLACES_${PN}-x = "libecore-x-ver-pre-svn-01-0"
RREPLACES_${PN}-input = "libecore-input-ver-pre-svn-01-0"

FILES_${PN} = "${libdir}/libecore*.so.*"
FILES_${PN}-con = "${libdir}/libecore_con*.so.*"
FILES_${PN}-config = "${libdir}/libecore_config*.so.*"
FILES_${PN}-desktop = "${libdir}/libecore_desktop*.so.*"
FILES_${PN}-directfb = "${libdir}/libecore_directfb*.so.*"
FILES_${PN}-sdl = "${libdir}/libecore_sdl*.so.*"
FILES_${PN}-evas = "${libdir}/libecore_evas*.so.*"
FILES_${PN}-fb = "${libdir}/libecore_fb*.so.*"
FILES_${PN}-file = "${libdir}/libecore_file*.so.*"
FILES_${PN}-imf = "${libdir}/libecore_imf*.so.*"
FILES_${PN}-imf-evas = "${libdir}/libecore_imf_evas*.so.*"
FILES_${PN}-ipc = "${libdir}/libecore_ipc*.so.*"
FILES_${PN}-job = "${libdir}/libecore_job*.so.*"
FILES_${PN}-txt = "${libdir}/libecore_txt*.so.*"
FILES_${PN}-x = "${libdir}/libecore_x*.so.*"
FILES_${PN}-input = "${libdir}/libecore_input*.so.*"

ECORE_OECONF = "\
  --x-includes=${STAGING_INCDIR}/X11 \
  --x-libraries=${STAGING_LIBDIR} \
  --enable-simple-x11 \
  --enable-ecore-config \
  --enable-ecore-x \
  --enable-ecore-job \
  --enable-ecore-fb \
  --enable-ecore-evas \
  --enable-ecore-evas-software-16-x11 \
  --enable-ecore-evas-xrender \
  --enable-abstract-sockets \
  --enable-ecore-con \
  --enable-ecore-ipc \
  --enable-ecore-file \
  --enable-inotify \
  --disable-ecore-desktop \
  --disable-ecore-x-xcb \
  --disable-ecore-directfb \
  --disable-ecore-sdl \
  --enable-ecore-evas-opengl-x11 \
  --disable-ecore-evas-dfb \
  --disable-ecore-evas-sdl \
  --disable-openssl \
  --disable-poll \
  --enable-xim \
"

EXTRA_OECONF = "${ECORE_OECONF} \
                 --enable-curl \
"

EXTRA_OECONF_virtclass-native = "\
                 ${ECORE_OECONF} \
                 --disable-curl \
"
