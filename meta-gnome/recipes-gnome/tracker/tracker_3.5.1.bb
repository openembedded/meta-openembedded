SUMMARY = "Tracker is a file search engine"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://COPYING.GPL;md5=ee31012bf90e7b8c108c69f197f3e3a4 \
    file://COPYING.LGPL;md5=2d5025d4aa3495befef8f17206a5b0a1 \
"

DEPENDS = " \
    dbus-native \
    python3-pygobject-native \
    glib-2.0 \
    sqlite3 \
    libarchive \
    dbus \
    icu \
    json-glib \
    libsoup-3.0 \
    libstemmer \
"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gobject-introspection vala gtk-doc manpages bash-completion features_check python3native

SRC_URI += "file://0001-meson-Do-not-define-TOP_SRCDIR.patch"
SRC_URI[archive.sha256sum] = "f972d50ac7bafdccf113b1eb99dcae35404685a99e55bfef16f3ac83b4de974d"

# gobject-introspection is mandatory and cannot be configured
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"
GIR_MESON_OPTION = ""

# text search is not an option anymore and requires sqlite3 build with
# PACKAGECONFIG[fts5] set (default)

# set required cross property sqlite3_has_fts5
do_write_config[vardeps] += "PACKAGECONFIG"
do_write_config:append() {
    echo "[properties]" > ${WORKDIR}/meson-tracker.cross
    echo "sqlite3_has_fts5 = 'true'" >> ${WORKDIR}/meson-tracker.cross
}

EXTRA_OEMESON = " \
    --cross-file ${WORKDIR}/meson-tracker.cross \
    -Dman=false \
    -Dsystemd_user_services=${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)} \
    -Dsystemd_user_services_dir=${systemd_user_unitdir} \
    -Dsoup=soup3 \
"

FILES:${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/tracker3 \
    ${libdir}/tracker-3.0 \
    ${systemd_user_unitdir} \
"
