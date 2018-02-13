require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=b602636d46a61c0ac0432bbf5c078fe4"

SRC_URI += "file://change-AC_TRY_RUN-to-AC_TRY_LINK.patch \
            file://pthread-check-threads-m4.patch \
            file://0001-Add-lpthread-to-link.patch \
           "
SRC_URI[md5sum] = "1ba84d9881521065f6398e665786f9e2"
SRC_URI[sha256sum] = "07f696a9761dcd839e2045c95c3a4d2ffb52c54417477cca9d30a14975b831cc"
