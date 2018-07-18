require oprofile.inc

DEPENDS += "virtual/kernel"
DEPENDS_append_powerpc64 = " libpfm4"

SRC_URI[md5sum] = "4fcd3920984dcb607314b2e225086c3a"
SRC_URI[sha256sum] = "eb95f98c40b7d0ee29b45fba3565d9f8381747528aa6b6159e37d4fa0c8ca57d"

S = "${WORKDIR}/oprofile-${PV}"

