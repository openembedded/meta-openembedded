DESCRIPTION = "TinyALSA is a small library to interface with ALSA in \
the Linux kernel. It is a lightweight alternative to libasound."
HOMEPAGE = "https://github.com/tinyalsa/tinyalsa"
SECTION = "libs/multimedia"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://NOTICE;md5=d2918795d9185efcbf430b9ad5cda46d"

PV .= "+git"
SRCREV = "9fab97ca07184371ecad81154d1dadb09d0fa7cf"
SRC_URI = "git://github.com/tinyalsa/tinyalsa;branch=master;protocol=https \
           file://0001-meson-add-option-to-enable-disable-plugin-support.patch \
           file://0002-pcm-fix-pcm_ioctl-to-dispatch-through-plugin-ops.patch"


inherit meson

# tinyalsa is built as a static library. Enable PIC to avoid relocation
# errors like these:
#
#    unresolvable R_AARCH64_ADR_PREL_PG_HI21 relocation against symbol `stderr@@GLIBC_2.17'
CFLAGS += " -fPIC -DPIC "
