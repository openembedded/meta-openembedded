SUMMARY = "Intel(R) Enclosure LED Utilities"

DESCRIPTION = "The utilities are designed primarily to be used on storage servers \
 utilizing MD devices (aka Linux Software RAID) for RAID arrays.\
"
HOMEPAGE = "https://github.com/intel/ledmon"

LICENSE = "GPL-2.0-only AND LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "autoconf-archive-native sg3-utils udev pciutils"

inherit autotools systemd pkgconfig

SYSTEMD_SERVICE:${PN} = "ledmon.service"

SRC_URI = "git://github.com/intel/ledmon;branch=master;protocol=https;tag=v${PV} \
	   file://0001-include-sys-select.h-and-sys-types.h.patch"

SRCREV = "f70201d255b667de00f15880e99732699df6dce0"

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"
COMPATIBLE_HOST:libc-musl = "null"


EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--enable-systemd', '', d)}"

EXTRA_OEMAKE = "CC='${CC}' LDFLAGS='${LDFLAGS}' CFLAGS='${CFLAGS}'"

# The ledmon sources include headers in ${S}/config to build but not in CFLAGS.
# We need to add this include path in CFLAGS.
CFLAGS += "-I${S}/config"
