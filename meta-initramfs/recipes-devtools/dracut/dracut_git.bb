SUMMARY = "Initramfs generator using udev"
DESCRIPTION = "Dracut is an event driven initramfs infrastructure. dracut (the tool) is used to create an initramfs image by copying tools and files from an installed system and combining it with the dracut framework, usually found in /usr/lib/dracut/modules.d."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PE = "1"
PV = "043+git${SRCREV}"

# v043 tag
SRCREV = "eab03540cd695d940062af5b55c02e8829aaa526"
SRC_URI = "git://git.kernel.org/pub/scm/boot/dracut/dracut.git"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--prefix=${prefix} \
                --libdir=${prefix}/lib \
                --datadir=${datadir} \
                --sysconfdir=${sysconfdir} \
                --sbindir=${sbindir} \
                --disable-documentation \
                --bindir=${bindir} \
                --includedir=${includedir} \
                --localstatedir=${localstatedir} \
               "

# RDEPEND on systemd optionally
PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"
PACKAGECONFIG[systemd] = "--with-systemdsystemunitdir=${systemd_unitdir}/system/,,,systemd"

EXTRA_OEMAKE += 'libdir=${prefix}/lib'

do_configure() {
    ./configure ${EXTRA_OECONF}
}

do_install() {
    oe_runmake install DESTDIR=${D}
    # Its Makefile uses cp -arx to install modules.d, so fix the owner
    # to root:root
    chown -R root:root ${D}/${prefix}/lib/dracut/modules.d
}

PACKAGES =+ "${PN}-bash-completion"

FILES_${PN}-bash-completion = "${datadir}/bash-completion"

FILES_${PN} += "${prefix}/lib/kernel \
                ${prefix}/lib/dracut \
                ${systemd_unitdir} \
               "
FILES_${PN}-dbg += "${prefix}/lib/dracut/.debug"

CONFFILES_${PN} += "${sysconfdir}/dracut.conf"

RDEPENDS_${PN} = "findutils cpio util-linux-blkid util-linux-getopt util-linux bash ldd"
RDEPENDS_${PN}-bash-completion = "bash-completion"

# This could be optimized a bit, but let's avoid non-booting systems :)
RRECOMMENDS_${PN} = " \
                     kernel-modules \
                     busybox \
                     coreutils \ 
                    "
