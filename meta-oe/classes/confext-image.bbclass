#
# Copyright OpenEmbedded Contributors
#
# SPDX-License-Identifier: MIT
#

# Configuration extension images may - dynamically at runtime - extend the
# /etc/ directory hierarchy with additional files. This is particularly
# useful on immutable system images where /etc/ resides on a read-only
# file system, or is only writable via an overlay, and shall be extended
# at runtime without making any persistent modifications to the base OS.
# This is the /etc/ counterpart of the sysext-image class, which extends
# /usr/ and /opt/.

## Example usage:
# confext-image-example.bb
#SUMMARY = "An example image to showcase a configuration extension image."
#LICENSE = "MIT"
#inherit confext-image
#IMAGE_FSTYPES = "squashfs"
#IMAGE_FEATURES = ""
#IMAGE_LINGUAS = ""
#IMAGE_INSTALL = "my-config-package"
#
## After building, the resulting 'confext-image-example*.confext.squashfs'
# can be deployed to an embedded system and 'merged' into the OS by
# following steps:
## 1. place a symlink into the systemd-confext image search path:
# $> mkdir /run/confexts
# $> ln -s /tmp/confext-image-example.confext.squashfs /run/confexts/example.raw
## 2. list all available extensions:
# $> systemd-confext list
## 3. and enable the found extensions:
# $> SYSTEMD_LOG_LEVEL=debug systemd-confext merge
#
# Any image type usable by systemd-confext can be selected through
# IMAGE_FSTYPES, e.g. "erofs", or "ddi" with 'inherit discoverable-disk-image'
# and REPART_DDI_TYPE = "confext".
# Note: squashfs/erofs need xattr support in the kernel
# (CONFIG_SQUASHFS_XATTR / CONFIG_EROFS_FS_XATTR) for the
# 'user.extension-release.strict' attribute set below to be preserved.

# Note: PACKAGECONFIG:pn-systemd needs to include 'sysext', which also
# provides systemd-confext.

# systemd-confext [1] has a simple mechanism for version compatibility:
# the extension to be loaded has to contain a file named
# /etc/extension-release.d/extension-release.NAME
# with "NAME" part *exactly* matching the filename of the extensions
# raw-device filename.
#
# From the extension-release file the "ID" and "VERSION_ID" fields are
# matched against same fields present in `os-release` and the extension
# is "merged" only if values in both fields from both files are an
# exact match.
#
# Link: https://www.freedesktop.org/software/systemd/man/latest/systemd-confext.html

inherit image

# Include '.confext' in the deployed image filename and symlink
IMAGE_NAME = "${IMAGE_BASENAME}${IMAGE_MACHINE_SUFFIX}${IMAGE_VERSION_SUFFIX}.confext"
IMAGE_LINK_NAME = "${IMAGE_BASENAME}${IMAGE_MACHINE_SUFFIX}.confext"
EXTENSION_NAME = "${IMAGE_LINK_NAME}.${IMAGE_FSTYPES}"

# Base extension identification fields
EXTENSION_ID_FIELD ?= "${DISTRO}"
EXTENSION_VERSION_FIELD ?= "${DISTRO_VERSION}"

confext_image_add_version_identifier_file() {
    # Use matching based on Distro name and version
    echo 'ID=${EXTENSION_ID_FIELD}' > ${WORKDIR}/extension-release.base
    # os-release.bb does "sanitise_value(ver)", which needs to be done here too
    echo 'VERSION_ID=${EXTENSION_VERSION_FIELD}' \
        | sed 's,+,-,g;s, ,_,g' \
        >> ${WORKDIR}/extension-release.base

    # Instruct `systemd-confext` to perform re-load once extension image is verified
    echo 'EXTENSION_RELOAD_MANAGER=1' >> ${WORKDIR}/extension-release.base

    install -d ${IMAGE_ROOTFS}${sysconfdir}/extension-release.d
    install -m 0644 ${WORKDIR}/extension-release.base \
        ${IMAGE_ROOTFS}${sysconfdir}/extension-release.d/extension-release.${EXTENSION_NAME}

    # systemd-confext expects an extension-release file of the exact same name as the image;
    # by setting a xattr we allow renaming of the extension image file.
    # (Kernel: this requires xattr support in the used filesystem)
    setfattr -n user.extension-release.strict -v false \
        ${IMAGE_ROOTFS}${sysconfdir}/extension-release.d/extension-release.${EXTENSION_NAME}
}

ROOTFS_POSTPROCESS_COMMAND += "confext_image_add_version_identifier_file"
