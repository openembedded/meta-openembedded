SUMMARY = "Fast data collector for Embedded Linux"
HOMEPAGE = "http://fluentbit.io"
BUGTRACKER = "https://github.com/fluent/fluent-bit/issues"

SRC_URI = "http://fluentbit.io/releases/0.11/fluent-bit-${PV}.tar.gz \
           file://0001-flb-plugin-proxy-go-Add-missing-dependency-on-jemall.patch \
           file://0002-msgpack-Add-comment-for-intended-fallthrough.patch \
           "
SRC_URI[md5sum] = "7bce8091c41fb6412b7fe0185b3cb8d6"
SRC_URI[sha256sum] = "93b1bdd14db20f2823cd31c6f1a2f3fcb7c94ec3e0c8daefabf130310b7fc4ed"

S = "${WORKDIR}/fluent-bit-${PV}"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

DEPENDS = "zlib"
INSANE_SKIP_${PN}-dev += "dev-elf"

inherit cmake systemd

EXTRA_OECMAKE = "-DGNU_HOST=${HOST_SYS} -DFLB_ALL=ON -DFLB_TD=1"

SYSTEMD_SERVICE_${PN} = "fluent-bit.service"

TARGET_CC_ARCH_append = " ${SELECTED_OPTIMIZATION}"
