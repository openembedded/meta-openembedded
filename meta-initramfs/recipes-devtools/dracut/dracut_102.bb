SUMMARY = "Initramfs generator using udev"
HOMEPAGE = "https://dracut.wiki.kernel.org/index.php/Main_Page"
DESCRIPTION = "Dracut is an event driven initramfs infrastructure. dracut (the tool) is used to create an initramfs image by copying tools and files from an installed system and combining it with the dracut framework, usually found in /usr/lib/dracut/modules.d."

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PE = "1"

SRCREV = "1a8ee6e00bbe017717a5ef9e9bcfefb3b88f629e"
SRC_URI = "git://github.com/dracut-ng/dracut-ng.git;protocol=http;branch=main \
           file://0001-util.h-include-sys-reg.h-when-libc-glibc.patch \
           file://0001-feat-dracut-install-split-ldd-command-arguments-for-.patch \
           "

DEPENDS += "kmod"
DEPENDS:append:libc-musl = " fts"

inherit bash-completion pkgconfig

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
PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "--with-systemdsystemunitdir=${systemd_unitdir}/system/,,,systemd"

EXTRA_OEMAKE += 'libdir=${prefix}/lib LDLIBS="${LDLIBS}"'

CFLAGS:append = " -fPIC"
LDLIBS:append:libc-musl = " -lfts"

do_configure() {
    ./configure ${EXTRA_OECONF}
}

do_install() {
    oe_runmake install DESTDIR=${D}
    # Its Makefile uses cp -arx to install modules.d, so fix the owner
    # to root:root
    chown -R root:root ${D}/${prefix}/lib/dracut/modules.d
}

FILES:${PN} += "${prefix}/lib/kernel \
                ${prefix}/lib/dracut \
                ${systemd_unitdir} \
               "
FILES:${PN}-dbg += "${prefix}/lib/dracut/.debug"

CONFFILES:${PN} += "${sysconfdir}/dracut.conf"

RDEPENDS:${PN} = "findutils cpio util-linux-blkid util-linux-getopt util-linux bash ldd"

# This could be optimized a bit, but let's avoid non-booting systems :)
RRECOMMENDS:${PN} = " \
                     kernel-modules \
                     coreutils \
                    "

CVE_STATUS[CVE-2010-4176] = "not-applicable-platform: Applies only to Fedora"
