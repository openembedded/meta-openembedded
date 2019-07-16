SUMMARY = "Automatic management of removable drives and media for thunar"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo libnotify libgudev"

inherit xfce distro_features_check

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI[md5sum] = "699a0534a0899e31366018a9e9e2aeb0"
SRC_URI[sha256sum] = "29309091db8b0f08c5a727625cc470673134977d7966cae0392f131300f9d5e9"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"

RDEPENDS_${PN} = "eject"
