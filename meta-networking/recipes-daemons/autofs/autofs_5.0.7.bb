DESCRIPTION = "Kernel based automounter for linux."
SECTION = "base"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

DEPENDS += "openldap libtirpc flex-native bison-native"

inherit autotools systemd

SRC_URI = "${KERNELORG_MIRROR}/linux/daemons/autofs/v5/autofs-${PV}.tar.bz2 \
           file://autofs-5.0.7-fix-nobind-sun-escaped-map-entries.patch \
           file://autofs-5.0.7-fix-use-cache-entry-after-free-mistake.patch \
           file://autofs-5.0.7-fix-ipv6-proximity-calculation.patch \
           file://autofs-5.0.7-fix-parse-buffer-initialization.patch \
           file://autofs-5.0.7-fix-typo-in-automount-8.patch \
           file://autofs-5.0.7-include-usage-in-usage-message.patch \
           file://autofs-5.0.7-dont-wait-forever-to-restart.patch \
           file://autofs-5.0.7-add-timeout-option-description-to-man-page.patch \
           file://autofs-5.0.7-fix-null-map-entry-order-handling.patch \
           file://autofs-5.0.7-make-description-of-default-MOUNT_WAIT-setting-clear.patch \
           file://autofs-5.0.7-configure-in-allow-cross-compilation.patch \
           file://autofs-5.0.7-README-update-mailing-list-subscription-info.patch \
           file://autofs-5.0.7-allow-non-root-user-to-check-status.patch \
           file://autofs-5.0.7-configure-allow-cross-compilation-update.patch \
           file://autofs-5.0.6-fix-recursive-mount-deadlock.patch \
           file://autofs-5.0.6-increase-file-map-read-buffer-size.patch \
           file://autofs-5.0.7-handle-new-location-of-systemd.patch \
           file://Makefile.rules-cross.patch \
           file://no-bash.patch \
           file://cross.patch \
           file://libtirpc.patch \
           file://libtirpc-name-clash-backout.patch \
          "

SRC_URI[md5sum] = "bc46838dece83c02d800ff144ed9f431"
SRC_URI[sha256sum] = "08c4304d8076dc80c14df559bc5fd821b67ef3457b245f61068bd053d8f94ccc"

inherit update-rc.d

INITSCRIPT_NAME = "autofs"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "autofs.service"

# FIXME: modules/Makefile has crappy rules that don't obey LDFLAGS
CFLAGS += "${LDFLAGS}"

EXTRA_OEMAKE = "DONTSTRIP=1"
EXTRA_OECONF += "--with-systemd --disable-mount-locking \
                --enable-ignore-busy --with-openldap=no \
                --with-sasl=no --with-libtirpc=yes \
                --with-path=${STAGING_BINDIR_NATIVE} \
                "
CACHED_CONFIGUREVARS = "ac_cv_path_RANLIB=${RANLIB} \
                        ac_cv_path_RPCGEN=rpcgen \
                       "

do_configure_prepend () {
        if [ ! -e acinclude.m4 ]; then
                cp aclocal.m4 acinclude.m4
        fi
}

INSANE_SKIP_${PN} = "dev-so"
