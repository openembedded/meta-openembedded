DESCRIPTION = "gvfs is a userspace virtual filesystem"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

inherit gnome

INC_PR = "r6"

SRC_URI[archive.md5sum] = "402f94b187b197b403d25c85caeb9562"
SRC_URI[archive.sha256sum] = "0895ac8f6d416e1b15433b6b6b68eb119c6e8b04fdb66db665d684355ef89345"

EXTRA_OECONF = "--disable-samba \
                --with-samba-includes=${STAGING_INCDIR} \
                --with-samba-libs=${STAGING_LIBDIR} \
                --with-archive-includes=${STAGING_INCDIR} \
               "

FILES_${PN} += "${datadir}/dbus-1/services/*"

