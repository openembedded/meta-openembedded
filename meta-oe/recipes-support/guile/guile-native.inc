SECTION = "unknown"
LICENSE = "GPL"
DEPENDS = "gettext-native gmp-native"

inherit autotools native

S="${WORKDIR}/guile-${PV}"

do_configure_append() {
        find ${S} -name Makefile | xargs sed -i s:'-Werror':'':g
}

