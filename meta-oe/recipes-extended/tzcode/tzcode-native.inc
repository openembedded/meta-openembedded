DESCRIPTION = "tzcode, timezone zoneinfo utils -- zic, zdump, tzselect"
INC_PR = "r4"

SRC_URI = " \
        ftp://elsie.nci.nih.gov/pub/tzcode${PV}.tar.gz;name=tzcode-${PV};subdir=${BPN}-${PV} \
        ftp://elsie.nci.nih.gov/pub/tzdata${TZDATA_PV}.tar.gz;name=tzdata-${TZDATA_PV};subdir=${BPN}-${PV} \
	"

inherit native

do_install () {
        install -d ${D}${bindir}
        install -m 755 zic ${D}${bindir}/
        install -m 755 zdump ${D}${bindir}/
        install -m 755 tzselect ${D}${bindir}/
}

NATIVE_INSTALL_WORKS = "1"
