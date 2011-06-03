DESCRIPTION = "Enlightened Sound Daemon"
SECTION = "gpe/base"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=55ca817ccb7d5b5b66355690e9abc605"
DEPENDS = "audiofile"

inherit gnome binconfig

SRC_URI = "ftp://ftp.gnome.org/pub/GNOME/sources/esound/0.2/esound-0.2.36.tar.bz2;name=archive \
           https://launchpad.net/ubuntu/breezy/+source/esound/0.2.36-1ubuntu5/+files/esound_0.2.36-1ubuntu5.diff.gz;name=patch \
           file://no-docs.patch \
           file://configure-fix.patch"

SRC_URI[archive.md5sum] = "3facb5aa0115cc1c31771b9ad454ae76"
SRC_URI[archive.sha256sum] = "68bf399fcbd45c5e9ba99cd13a3a479e4ef2bc5dc52e540ffa00aef1e1b19a76"
SRC_URI[patch.md5sum] = "44b4d713387fb3f18666d289f88ae00c"
SRC_URI[patch.sha256sum] = "9bd7db657b8e9622910b9d3188096e37ee627ac0651c78ddce58e205e7487932

EXTRA_OECONF = " \
    --disable-alsa \
    --disable-arts \
    --disable-artstest \
"
do_configure_prepend() {
	sed -i -e 's:/usr/include/mme:${STAGING_INCDIR}/mme:g' configure.ac
}

PACKAGES =+ "esddsp esd esd-utils"

FILES_esddsp = "${bindir}/esddsp ${libdir}/libesddsp.so.*"
FILES_esd = "${bindir}/esd"
FILES_esd-utils = "${bindir}/*"

