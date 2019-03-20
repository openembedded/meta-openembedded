DESCRIPTION = "Blueman is a GTK+ Bluetooth Manager"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "bluez5 python3-pygobject python3-cython-native python3-setuptools-native intltool-native"

inherit autotools systemd gsettings python3native gtk-icon-cache

SRC_URI = " \
    https://github.com/blueman-project/blueman/releases/download/${PV}/blueman-${PV}.tar.xz \
    file://0001-Search-for-cython3.patch \
    file://0001-Fix-building-on-musl.patch \
"
SRC_URI[md5sum] = "51dc07d48125ee9600e7f6168d6e67f6"
SRC_URI[sha256sum] = "04afd07b4c900a401f39e711dedbf8b9cf12000ddeda0d8ef3c3327caf34704e"

EXTRA_OECONF = " \
    --disable-runtime-deps-check \
    --disable-schemas-compile \
"

SYSTEMD_SERVICE_${PN} = "${BPN}-mechanism.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

RRECOMENDS_${PN} += "adwaita-icon-theme"
RDEPENDS_${PN} += " \
    python3-dbus \
    packagegroup-tools-bluetooth \
"

PACKAGECONFIG[thunar] = "--enable-thunar-sendto,--disable-thunar-sendto,,thunar"

FILES_${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/Thunar \
    ${systemd_user_unitdir} \
    ${exec_prefix}${systemd_system_unitdir} \
    ${PYTHON_SITEPACKAGES_DIR} \
"

FILES_${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/_blueman.a"
