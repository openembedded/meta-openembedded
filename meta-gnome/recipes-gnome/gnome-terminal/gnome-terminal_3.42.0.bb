SUMMARY = "GNOME terminal"
LICENSE = "GPLv3 & GFDL-1.3"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
    file://COPYING.GFDL;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gnome-help gettext itstool upstream-version-is-even

DEPENDS = " \
    glib-2.0-native \
    intltool-native \
    yelp-tools-native \
    docbook-xsl-stylesheets-native libxslt-native \
    desktop-file-utils-native \
    gtk+3 \
    gsettings-desktop-schemas \
    vte \
    dconf \
    libpcre2 \
"

SRC_URI[archive.sha256sum] = "b50e9e5664230e6ca290bcf38812a5b65e0baac66a486ee210894cf675f72e0b"
SRC_URI += "file://0001-Add-W_EXITCODE-macro-for-non-glibc-systems.patch"

EXTRA_OEMESON += " \
    -Dsearch_provider=false \
    -Dnautilus_extension=false \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/dbus-1 \
    ${systemd_user_unitdir} \
"

RRECOMMENDS:${PN} += "vte-prompt gsettings-desktop-schemas"
