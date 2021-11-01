SUMMARY = "GNOME Shell is the graphical shell of the GNOME desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gettext gobject-introspection features_check bash-completion

REQUIRED_DISTRO_FEATURES = "x11 polkit systemd pam"

DEPENDS = " \
    libxml2-native \
    sassc-native \
    gtk4 \
    mutter \
    evolution-data-server \
    gcr \
    gjs \
    gnome-autoar \
    polkit \
    libcroco \
    startup-notification \
    ibus \
    gsettings-desktop-schemas \
"

GTKDOC_MESON_OPTION = "gtk_doc"

# gobject-introspection is mandatory and cannot be configured
REQUIRED_DISTRO_FEATURES += "gobject-introspection-data"
UNKNOWN_CONFIGURE_WHITELIST:append = " introspection"

SRC_URI[archive.sha256sum] = "52f971e85140e5de74b9369ef6656e49ce95af1f232fc1e0df1f046129ab4f65"
SRC_URI += "file://0001-Introduce-options-gjs_path-to-optionally-set-path-to.patch"

PACKAGECONFIG ??= "bluetooth nm ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[bluetooth] = ",,gnome-bluetooth"
PACKAGECONFIG[nm] = "-Dnetworkmanager=true, -Dnetworkmanager=false, networkmanager"
PACKAGECONFIG[systemd] = "-Dsystemd=true, -Dsystemd=false, systemd"

EXTRA_OEMESON = " \
    -Dgjs_path=${bindir}/gjs \
    -Dextensions-app:gjs_path=${bindir}/gjs \
    -Dtests=false \
    -Dman=false \
"

do_install:append() {
    # fix shebangs
    for tool in `find ${D}${bindir} -name '*-tool'`; do
        sed -i 's:#!${PYTHON}:#!${bindir}/${PYTHON_PN}:' $tool
    done
}

GSETTINGS_PACKAGE = "${PN}-gsettings"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/dbus-1 \
    ${datadir}/gnome-control-center \
    ${datadir}/xdg-desktop-portal \
    ${systemd_user_unitdir} \
"

RDEPENDS:${PN} += "gsettings-desktop-schemas gdm-base librsvg-gtk ${PN}-gsettings"

PACKAGES =+ "${PN}-tools ${PN}-gsettings"
FILES:${PN}-tools = "${bindir}/*-tool"
RDEPENDS:${PN}-tools = "python3-core"

