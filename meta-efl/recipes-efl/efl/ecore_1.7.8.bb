DESCRIPTION = "Ecore is the Enlightenment application framework library"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=d6ff2c3c85de2faf5fd7dcd9ccfc8886"
DEPENDS = "virtual/libiconv tslib curl eet evas glib-2.0 gnutls \
           libxtst libxcomposite libxinerama libxscrnsaver libxdamage libxrandr libxcursor libxfixes"
DEPENDS_virtclass-native = "eet-native evas-native gettext-native"
# optional
# DEPENDS += "directfb virtual/libsdl openssl virtual/libiconv"

inherit efl gettext

BBCLASSEXTEND = "native"

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

FILES_${PN} = "${libdir}/libecore*.so.* \
    ${bindir} \
"
FILES_${PN}-con = "${libdir}/libecore_con*.so.*"
FILES_${PN}-config = "${libdir}/libecore_config*.so.*"
FILES_${PN}-desktop = "${libdir}/libecore_desktop*.so.*"
FILES_${PN}-directfb = "${libdir}/libecore_directfb*.so.*"
FILES_${PN}-sdl = "${libdir}/libecore_sdl*.so.*"
FILES_${PN}-evas = "${libdir}/libecore_evas*.so.*"
FILES_${PN}-fb = "${libdir}/libecore_fb*.so.*"
FILES_${PN}-file = "${libdir}/libecore_file*.so.*"
FILES_${PN}-imf = "${libdir}/libecore_imf*.so.* \
    ${libdir}/ecore/immodules/*.so \
"
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
    --disable-ecore-x-xprint \
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
                 --disable-ecore-x-composite \
                 --disable-ecore-x-damage \
                 --disable-ecore-x-dpms \
                 --disable-ecore-x-randr \
                 --disable-ecore-x-render \
                 --disable-ecore-x-screensaver \
                 --disable-ecore-x-shape \
                 --disable-ecore-x-sync \
                 --disable-ecore-x-xfixes \
                 --disable-ecore-x-xinerama \
                 --disable-ecore-x-xprint \
                 --disable-ecore-x-xtest \
                 --disable-ecore-x-cursor \
                 --disable-ecore-x-input \
                 --disable-ecore-x-dri \
"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://fix-ecore-fb-initialization.patch \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[wayland] = "--enable-ecore-wayland,--disable-ecore-wayland,wayland xkbcommon"

SRC_URI[md5sum] = "5a8ca096d8c15647b2493d4664a4b895"
SRC_URI[sha256sum] = "26cb1dc02213a221fdec32ef4ea4ece608e5239bdbd19c9d62b09cf931863738"
