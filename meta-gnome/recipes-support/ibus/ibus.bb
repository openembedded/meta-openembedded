require ${BPN}.inc

DEPENDS += " \
    glib-2.0-native \
    glib-2.0 \
    dbus \
    iso-codes \
"

inherit gtk-icon-cache bash-completion features_check

# for unicode-ucd
EXTRA_OECONF += "--with-ucd-dir=${STAGING_DATADIR}/unicode/ucd"

PACKAGECONFIG ??= " \
    dconf vala \
    ${@bb.utils.contains_any('DISTRO_FEATURES', [ 'wayland', 'x11' ], 'gtk3 gtk4', '', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd wayland x11', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libnotify', '', d)} \
"

REQUIRED_DISTRO_FEATURES = "${@bb.utils.contains('PACKAGECONFIG', 'gtk4', 'opengl', '', d)}"

do_compile:prepend() {
    export GIR_EXTRA_LIBS_PATH="${B}/src/.libs"
}

FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/GConf \
    ${datadir}/glib-2.0 \
    ${libdir}/gtk-2.0 \
    ${libdir}/gtk-3.0 \
    ${libdir}/gtk-4.0 \
    ${systemd_user_unitdir} \
"

FILES:${PN}-dev += " \
    ${datadir}/gettext \
"
RDEPENDS:${PN} += "python3-core"
