SUMMARY = "Adds ID3/OGG tag support to the Thunar bulk rename dialog"
HOMEPAGE = "https://docs.xfce.org/xfce/thunar/media-tags"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit thunar-plugin

DEPENDS += "taglib"

SRC_URI[sha256sum] = "105a72d51c7fbcc690c9b9a850f3743accbd9bf5dc51480b5ea283dfde96d61e"
