SUMMARY = "Interactive process viewer"
HOMEPAGE = "https://htop.dev"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4099d367cd5e59b6d4fc1ee33accb891"

DEPENDS = "ncurses"

SRC_URI = "git://github.com/htop-dev/htop.git \
           file://0001-Use-pkg-config.patch \
           "
SRCREV = "0b989ee38ce37eb4a50265faa11df2bd7ed8e5c3"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGECONFIG ??= " \
    unicode \
    linux-affinity \
    delayacct \
"
PACKAGECONFIG[openvz] = "--enable-openvz,--disable-openvz"
PACKAGECONFIG[vserver] = "--enable-vserver,--disable-vserver"
PACKAGECONFIG[ancient-vserver] = "--enable-ancient-vserver,--disable-ancient-vserver"
PACKAGECONFIG[unicode] = "--enable-unicode,--disable-unicode"
PACKAGECONFIG[hwloc] = "--enable-hwloc,--disable-hwloc,hwloc"
PACKAGECONFIG[linux-affinity] = "--enable-linux-affinity,--disable-linux-affinity"
PACKAGECONFIG[setuid] = "--enable-setuid,--disable-setuid"
PACKAGECONFIG[delayacct] = "--enable-delayacct,--disable-delayacct,libnl"
PACKAGECONFIG[sensors] = "--with-sensors,--without-sensors,lmsensors,lmsensors-libsensors"

FILES_${PN} += "${datadir}/icons/hicolor/scalable/apps/htop.svg"
