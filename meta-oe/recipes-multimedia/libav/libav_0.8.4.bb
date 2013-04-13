require libav.inc

EXTRA_OECONF += " \
    --enable-postproc \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/libav-0.8.4:"

SRC_URI_append = " \
    file://0001-configure-enable-pic-for-AArch64.patch \
"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=e344c8fa836c3a41c4cbd79d7bd3a379 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

PV = "0.8.4+git${SRCPV}"
PR = "${INC_PR}.0"

# When bumping SRCREV make sure you bump PR here and in dependant
# recipes (gst-ffmpeg, gnash, omxil, etc) to account for SOVERSION
# changes
SRCREV = "2c8ce46250ff78191fe6565876ddc4bc03fdf519"
SRC_URI = "git://git.libav.org/libav.git;protocol=git;branch=release/0.8"

S = "${WORKDIR}/git"


