SUMMARY = "Initramfs generator using udev"
DESCRIPTION = "Dracut is an event driven initramfs infrastructure. dracut (the tool) is used to create an initramfs image by copying tools and files from an installed system and combining it with the dracut framework, usually found in /usr/lib/dracut/modules.d."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "bb1ba3a1ca9dd6284f6319eb197de4a757a7f99d"
SRC_URI = "git://git.kernel.org/pub/scm/boot/dracut/dracut.git \
           file://0001-Use-builtin-xz-lzma-option-to-use-all-cores-for-mult.patch \
          "

S = "${WORKDIR}/git"

do_configure() {
    ./configure --prefix=${prefix} \
                --libdir=${libdir} \
                --datadir=${datadir} \
                --sysconfdir=${sysconfdir} \
                --sbindir=${sbindir} \
                --disable-documentation \
                --bindir=${bindir} \
                --includedir=${includedir} \
                --localstatedir=${localstatedir} \
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${datadir}/bash-completion \ 
                ${libdir}/kernel \
               "

RDEPENDS_${PN} = "systemd findutils cpio util-linux-blkid bash ldd"
# This could be optimized a bit, but let's avoid non-booting systems :)
RRECOMMENDS_${PN} = " \
                     kernel-modules \ 
                    "
