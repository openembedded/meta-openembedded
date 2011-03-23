SECTION = "unknown"
DEPENDS = "gettext-native gmp-native"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9ba0d76ca3ef2a1d15a2ac839ef01fa"

inherit autotools native

S="${WORKDIR}/guile-${PV}"

do_configure_append() {
        find ${S} -name Makefile | xargs sed -i s:'-Werror':'':g
}

