SUMMARY = "LibGTop2"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://copyright.txt;md5=dbc839bf158d19a20e661db14db7a58c"

inherit gnomebase lib_package gtk-doc
SRC_URI[archive.md5sum] = "ed44d736efd97d062b77621de6aff439"
SRC_URI[archive.sha256sum] = "49958d7da1f76b257bfd0d557d8ed2b218a5ab0d31b59fed1c32ddf2a1529f5d"

DEPENDS = "glib-2.0 intltool-native libxau"
