DESCRIPTION = "Kernel based automounter for linux."
SECTION = "base"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

PR = "r6"

DEPENDS += "libtirpc flex-native bison-native"

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
           file://autofs-5.0.7-fix-map-entry-duplicate-offset-detection.patch \
           file://autofs-5.0.7-allow-nsswitch_conf-to-not-contain-automount-lines.patch \
           file://autofs-5.0.7-fix-nobind-man-page-description.patch \
           file://autofs-5.0.7-fix-submount-offset-delete.patch \
           file://autofs-5.0.7-fix-init-script-status-return.patch \
           file://autofs-5.0.7-fix-use-get_proximity-without-libtirpc.patch \
           file://autofs-5.0.7-dont-use-dirent-d_type-to-filter-out-files-in-scandir.patch \
           file://autofs-5.0.7-dont-schedule-new-alarms-after-readmap.patch \
           file://autofs-5.0.7-use-numeric-protocol-ids-instead-of-protoent-structs.patch \
           file://autofs-5.0.7-lib-defaults-use-WITH_LDAP-conditional-around-LDAP-types.patch \
           file://autofs-5.0.7-make-yellow-pages-support-optional.patch \
           file://autofs-5.0.7-modules-replicated-use-sin6.addr-s6_addr32.patch \
           file://autofs-5.0.7-workaround-missing-GNU-versionsort-extension.patch \
           file://autofs-5.0.7-dont-fail-on-master-map-self-include.patch \
           file://autofs-5.0.7-fix-wildcard-multi-map-regression.patch \
           file://autofs-5.0.7-fix-file-descriptor-leak-when-reloading-the-daemon.patch \
           file://autofs-5.0.7-depricate-nosymlink-pseudo-option.patch \
           file://autofs-5.0.7-add-symlink-pseudo-option.patch \
           file://autofs-5.0.7-update-kernel-include-files.patch \
           file://autofs-5.0.7-fix-requires-in-spec-file.patch \
           file://autofs-5.0.7-fix-libtirpc-build-option.patch \
           file://autofs-5.0.7-fix-systemd-unidir-in-spec-file.patch \
           file://autofs-5.0.7-document-browse-option-in-man-page.patch \
           file://autofs-5.0.7-fix-automounter-support-on-parisc.patch \
           file://autofs-5.0.7-include-linux-nfs.h-directly-in-rpc_sub.patch \
           file://Makefile.rules-cross.patch \
           file://no-bash.patch \
           file://cross.patch \
           file://libtirpc.patch \
           file://libtirpc-name-clash-backout.patch \
           file://autofs-5.0.7-do-not-check-for-modprobe.patch \
           file://fix_disable_ldap.patch \
"

SRC_URI[md5sum] = "bc46838dece83c02d800ff144ed9f431"
SRC_URI[sha256sum] = "08c4304d8076dc80c14df559bc5fd821b67ef3457b245f61068bd053d8f94ccc"

inherit update-rc.d pkgconfig

INITSCRIPT_NAME = "autofs"
INITSCRIPT_PARAMS = "defaults"

# FIXME: modules/Makefile has crappy rules that don't obey LDFLAGS
CFLAGS += "${LDFLAGS}"

PACKAGECONFIG[systemd] = "--with-systemd,--without-systemd,systemd"

PACKAGECONFIG ?= "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

EXTRA_OEMAKE = "DONTSTRIP=1"
EXTRA_OECONF += "--disable-mount-locking \
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

do_install_append () {
    test -d ${D}/run && rmdir ${D}/run
}

INSANE_SKIP_${PN} = "dev-so"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "autofs.service"
