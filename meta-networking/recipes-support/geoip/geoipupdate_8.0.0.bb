SUMMARY = "Crontab entry to provide weekly updates of the GeoIP free databases."
DESCRIPTION = "update databases for GeoIP"

HOMEPAGE = "http://dev.maxmind.com/geoip/"
SECTION = "net"

LICENSE = "(Apache-2.0 OR MIT)"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE-APACHE;md5=4e168cce331e5c827d4c2b68a6200e1b \
                    file://src/${GO_IMPORT}/LICENSE-MIT;md5=995598bc9de2b4c987c2cb87fc24f341"
require ${BPN}-licenses.inc

SRC_URI = "git://github.com/maxmind/geoipupdate.git;protocol=https;branch=main;tag=v${PV};destsuffix=${GO_SRCURI_DESTSUFFIX} \
           file://GeoIP.conf \
           file://geoipupdate.cron \
           "
SRCREV = "07618e0e7cacbe7aaebbc26cafb54c2a9f2f3bc3"
require ${BPN}-go-mods.inc

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

GO_IMPORT = "github.com/maxmind/geoipupdate/v8"
GO_INSTALL = "${GO_IMPORT}/cmd/geoipupdate"
GO_LINKSHARED = ""
GO_EXTRA_LDFLAGS = "-X main.version=${PV} \
                    -X main.defaultConfigFile=${sysconfdir}/GeoIP.conf \
                    -X main.defaultDatabaseDirectory=${datadir}/GeoIP"

inherit go-mod go-mod-update-modules

do_install:append() {
    install -d ${D}${sysconfdir}/cron.d
    install -m 0644 ${UNPACKDIR}/GeoIP.conf ${D}${sysconfdir}/
    install -m 0644 ${UNPACKDIR}/geoipupdate.cron ${D}${sysconfdir}/cron.d/
    install -d ${D}${datadir}/GeoIP
    # release and docker helper scripts would pull bash and perl into ${PN}-dev
    rm -rf ${D}${libdir}/go/src/${GO_IMPORT}/dev-bin ${D}${libdir}/go/src/${GO_IMPORT}/docker
}

FILES:${PN} += "${datadir}/GeoIP"
