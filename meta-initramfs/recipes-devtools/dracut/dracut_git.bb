SUMMARY = "Initramfs generator using udev"
DESCRIPTION = "Dracut is an event driven initramfs infrastructure. dracut (the tool) is used to create an initramfs image by copying tools and files from an installed system and combining it with the dracut framework, usually found in /usr/lib/dracut/modules.d."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PE = "1"
PV = "038"

# v038 tag
SRCREV = "267a109a81715c8957f14659593deb7b6255d40e"
SRC_URI = "git://git.kernel.org/pub/scm/boot/dracut/dracut.git"

S = "${WORKDIR}/git"

inherit distro_features_check
REQUIRED_DISTRO_FEATURES = "systemd"

EXTRA_OECONF = "--prefix=${prefix} \
                --libdir=${libdir} \
                --datadir=${datadir} \
                --sysconfdir=${sysconfdir} \
                --sbindir=${sbindir} \
                --disable-documentation \
                --bindir=${bindir} \
                --includedir=${includedir} \
                --localstatedir=${localstatedir} \
                --systemdsystemunitdir=${systemd_unitdir}/system"

do_configure() {
    ./configure ${EXTRA_OECONF}
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

PACKAGES =+ "${PN}-bash-completion"

FILES_${PN}-bash-completion = "${datadir}/bash-completion"

FILES_${PN} += " ${libdir}/kernel \
                ${systemd_unitdir} \
               "
CONFFILES_${PN} += "${sysconfdir}/dracut.conf"

RDEPENDS_${PN} = "systemd findutils cpio util-linux-blkid util-linux-getopt bash ldd"
RDEPENDS_${PN}-bash-completion = "bash-completion"

# This could be optimized a bit, but let's avoid non-booting systems :)
RRECOMMENDS_${PN} = " \
                     kernel-modules \
                     busybox \
                     coreutils \ 
                    "
