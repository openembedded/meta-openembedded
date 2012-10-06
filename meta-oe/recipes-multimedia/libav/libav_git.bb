require libav.inc

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS += "libpostproc"
PV = "0.8.99+git${SRCPV}"
PR = "${INC_PR}.0"

DEFAULT_PREFERENCE = "-1"

# When bumping SRCREV make sure you bump PR here and in dependant
# recipes (gst-ffmpeg, gnash, omxil, etc) to account for SOVERSION
# changes
SRCREV = "4673a5a761f329af0aa56fc84f34ed898188bfa2"
SRC_URI = "git://git.libav.org/libav.git;protocol=git"

S = "${WORKDIR}/git"

# postproc has been split
EXTRA_OECONF := "${@oe_filter_out('--enable-postproc', '${EXTRA_OECONF}', d)}"
