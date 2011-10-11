SECTION = "unknown"
DEPENDS = "gettext-native gmp-native"

LICENSE = "LGPLv2.1"

inherit autotools native

S="${WORKDIR}/guile-${PV}"

do_configure_append() {
        find ${S} -name Makefile | xargs sed -i s:'-Werror':'':g
}

