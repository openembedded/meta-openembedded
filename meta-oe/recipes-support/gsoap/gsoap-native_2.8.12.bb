require gsoap_${PV}.inc

DEPENDS = ""
EXTRA_OEMAKE = ""

inherit native

do_install() {
    oe_runmake DESTDIR=${D} BINDIR=${D}${bindir} install
}


SRC_URI[md5sum] = "5700d26fc6fe3073d038349e19c3640d"
SRC_URI[sha256sum] = "51eef118544fa846f4d2dea2eedf91c84c46a1abeafc5eee3dcff783f4015a00"
