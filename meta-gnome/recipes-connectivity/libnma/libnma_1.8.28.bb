SUMMARY = "NetworkManager GUI library"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "glib-2.0 networkmanager"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gobject-introspection gtk-doc gettext vala

SRC_URI[archive.md5sum] = "094c45d7694b153612cbdc3c713edcb5"
SRC_URI[archive.sha256sum] = "4af69552d131a3b2b8b6a2df584044258bf588448dcdb4bddfa12a07c134b726"

PACKAGECONFIG ?= "gcr iso_codes mobile_broadband_provider_info"
PACKAGECONFIG[gcr] = "-Dgcr=true,-Dgcr=false,gcr"
PACKAGECONFIG[iso_codes] = "-Diso_codes=true,-Diso_codes=false,iso-codes,iso-codes"
PACKAGECONFIG[mobile_broadband_provider_info] = "-Dmobile_broadband_provider_info=true,-Dmobile_broadband_provider_info=false,mobile-broadband-provider-info,mobile-broadband-provider-info"

GTKDOC_MESON_OPTION = "gtk_doc"
