DESCRIPTION = "Monitors where the system spends its time at start, creating a graph of all processes, disk utilization, and wait time."
HOMEPAGE = "http://meego.gitorious.org/meego-developer-tools/bootchart"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=fcb02dc552a041dee27e4b85c7396067"

PV = "1.16"
PR = "r1"
PE = "1"

SRC_URI = "git://gitorious.org/meego-developer-tools/bootchart.git \
           file://0001-svg-add-rudimentary-support-for-ARM-cpuinfo.patch \
           file://0002-svg-open-etc-os-release-and-use-PRETTY_NAME-for-the-.patch \
"

SRCREV = "872fb107028e377eef3c0c7c8a6e0f7c32b8ebb8"

S = "${WORKDIR}/git"

inherit autotools

