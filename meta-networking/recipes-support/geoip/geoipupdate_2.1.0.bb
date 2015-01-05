SUMMARY = "Crontab entry to provide weekly updates of the GeoIP free databases."
DESCRIPTION = "update databases for GeoIP"

HOMEPAGE = "http://dev.maxmind.com/geoip/"
SECTION = "Development"

DEPENDS = "zlib curl"

SRC_URI = "https://github.com/maxmind/geoipupdate/releases/download/v2.1.0/geoipupdate-2.1.0.tar.gz \
           file://GeoIP.conf \
           file://geoipupdate.cron \
          "

SRC_URI[md5sum] = "9107d082f15c8e482f4b6f1080e7deaf"
SRC_URI[sha256sum] = "7388c46f6c483ae609e5f5333a2585bc9713d56bb522da5c11b09d41c87aa5fb"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "\
file://ChangeLog.md;md5=25f3500fad03ad7e6bf135b29c9c7c2d \
"

inherit autotools

do_install_append() {
    install -d ${D}/${sysconfdir}
    install -d ${D}/${sysconfdir}/cron.d
    install ${WORKDIR}/GeoIP.conf ${D}/${sysconfdir}/
    install ${WORKDIR}/geoipupdate.cron ${D}/${sysconfdir}/cron.d/
}
