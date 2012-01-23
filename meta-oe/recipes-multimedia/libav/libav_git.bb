require libav.inc

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=e344c8fa836c3a41c4cbd79d7bd3a379 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

# When bumping SRCREV make sure you bump PR here and in dependant recipes (gst-ffmpeg, gnash, omxil, etc) to account for SOVERSION changes
SRCREV = "62c473934822afd317dfef27754a0ff71f58ce2a"

PV = "0.6.5"
PR = "${INC_PR}.0"

SRC_URI = "git://git.libav.org/libav.git;protocol=git;branch=release/0.6"

S = "${WORKDIR}/git"


