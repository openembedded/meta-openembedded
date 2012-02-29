PR = "${INC_PR}.0"

KLIBC_UTILS_VARIANT = "static"
KLIBC_UTILS_PKGNAME = "klibc-static-utils"

FILESPATH =. "${FILE_DIRNAME}/klibc-${PV}:"

do_install() {
        :
}

PACKAGES_${PN} = "${PN}"
FILES_${PN} = ""

require klibc-utils.inc
require klibc.inc
