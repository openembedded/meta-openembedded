require eglibc_${PV}.bb
require recipes-core/eglibc/eglibc-initial.inc

do_configure_prepend () {
        unset CFLAGS
}
