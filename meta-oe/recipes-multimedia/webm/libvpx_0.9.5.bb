require libvpx.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=6e8dee932c26f2dab503abf70c96d8bb"

PR = "${INC_PR}.0"

SRC_URI += "file://libvpx-configure-support-blank-prefix.patch \
            file://CVE-2010-4203.patch \
            "

SRC_URI[md5sum] = "4bf2f2c76700202c1fe9201fcb0680e3"
SRC_URI[sha256sum] = "2e93968afcded113a7e218de047feecf6659a089058803a9e40fb687de5f9bfa"

CONFIGUREOPTS += " \
        --prefix=${prefix} \
        --libdir=${libdir} \
"

