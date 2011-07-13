DESCRIPTION = "Timezone data"
SECTION = "base"
DEPENDS = "tzcode-native"

INC_PR = "r10"

DEFAULT_TIMEZONE ?= "Europe/London"

RCONFLICTS_${PN} = "timezones timezone-africa timezone-america timezone-antarctica \
             timezone-arctic timezone-asia timezone-atlantic \
             timezone-australia timezone-europe timezone-indian \
             timezone-iso3166.tab timezone-pacific timezone-zone.tab"

SRC_URI = "ftp://elsie.nci.nih.gov/pub/tzdata${PV}.tar.gz;subdir=${BPN}-${PV}"

TZONES= "africa antarctica asia australasia europe northamerica southamerica  \
         factory solar87 solar88 solar89 etcetera backward systemv \
        "
#        pacificnew \

CONFFILES_${PN} = "${sysconfdir}/timezone ${sysconfdir}/localtime"

do_compile () {
        mkdir -p build
        for zone in ${TZONES}; do \
            ${STAGING_BINDIR_NATIVE}/zic -d ${S}/build${datadir}/zoneinfo -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
            ${STAGING_BINDIR_NATIVE}/zic -d ${S}/build${datadir}/zoneinfo/posix -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
            ${STAGING_BINDIR_NATIVE}/zic -d ${S}/build${datadir}/zoneinfo/right -L ${S}/leapseconds \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
        done
}

do_install () {
        install -d ${D}${prefix} ${D}${datadir}/zoneinfo
        cp -pPR ${S}/build${prefix}/* ${D}${prefix}
        # Only eglibc is removing zoneinfo files from package
        if [ "${LIBC}"x = "eglibc"x ] ; then
          cp -pP "${S}/zone.tab" ${D}${datadir}/zoneinfo
          cp -pP "${S}/iso3166.tab" ${D}${datadir}/zoneinfo
        fi

        # Install a sane default for timezones
        install -d ${D}${sysconfdir}
        echo ${DEFAULT_TIMEZONE} > ${D}${sysconfdir}/timezone
        cp -pPR ${S}/build${datadir}/zoneinfo/${DEFAULT_TIMEZONE} ${D}${sysconfdir}/localtime
}

inherit allarch
# Packages primarily organized by directory with a major city
# in most time zones in the base package

PACKAGES = "${PN}-dbg tzdata tzdata-misc tzdata-posix tzdata-right tzdata-africa \
    tzdata-americas tzdata-antarctica tzdata-arctic tzdata-asia \
    tzdata-atlantic tzdata-australia tzdata-europe tzdata-pacific"

ALLOW_EMPTY_${PN}-dbg = "1"

FILES_tzdata-africa += "${datadir}/zoneinfo/Africa/*"
RPROVIDES_tzdata-africa = "tzdata-africa"

FILES_tzdata-americas += "${datadir}/zoneinfo/America/*  \
                ${datadir}/zoneinfo/US/*                \
                ${datadir}/zoneinfo/Brazil/*            \
                ${datadir}/zoneinfo/Canada/*            \
                ${datadir}/zoneinfo/Mexico/*            \
                ${datadir}/zoneinfo/Chile/*"
RPROVIDES_tzdata-americas = "tzdata-americas"

FILES_tzdata-antarctica += "${datadir}/zoneinfo/Antarctica/*"
RPROVIDES_tzdata-antarctica = "tzdata-antarctica"

FILES_tzdata-arctic += "${datadir}/zoneinfo/Arctic/*"
RPROVIDES_tzdata-arctic = "tzdata-arctic"

FILES_tzdata-asia += "${datadir}/zoneinfo/Asia/*        \
                ${datadir}/zoneinfo/Indian/*            \
                ${datadir}/zoneinfo/Mideast/*"
RPROVIDES_tzdata-asia = "tzdata-asia"

FILES_tzdata-atlantic += "${datadir}/zoneinfo/Atlantic/*"
RPROVIDES_tzdata-atlantic = "tzdata-atlantic"

FILES_tzdata-australia += "${datadir}/zoneinfo/Australia/*"
RPROVIDES_tzdata-australia = "tzdata-australia"

FILES_tzdata-europe += "${datadir}/zoneinfo/Europe/*"
RPROVIDES_tzdata-europe = "tzdata-europe"

FILES_tzdata-pacific += "${datadir}/zoneinfo/Pacific/*"
RPROVIDES_tzdata-pacific = "tzdata-pacific"

FILES_tzdata-posix += "${datadir}/zoneinfo/posix/*"
RPROVIDES_tzdata-posix = "tzdata-posix"

FILES_tzdata-right += "${datadir}/zoneinfo/right/*"
RPROVIDES_tzdata-right = "tzdata-right"


FILES_tzdata-misc += "${datadir}/zoneinfo/Cuba           \
                ${datadir}/zoneinfo/Egypt                \
                ${datadir}/zoneinfo/Eire                 \
                ${datadir}/zoneinfo/Factory              \
                ${datadir}/zoneinfo/GB-Eire              \
                ${datadir}/zoneinfo/Hongkong             \
                ${datadir}/zoneinfo/Iceland              \
                ${datadir}/zoneinfo/Iran                 \
                ${datadir}/zoneinfo/Israel               \
                ${datadir}/zoneinfo/Jamaica              \
                ${datadir}/zoneinfo/Japan                \
                ${datadir}/zoneinfo/Kwajalein            \
                ${datadir}/zoneinfo/Libya                \
                ${datadir}/zoneinfo/Navajo               \
                ${datadir}/zoneinfo/Poland               \
                ${datadir}/zoneinfo/Portugal             \
                ${datadir}/zoneinfo/Singapore            \
                ${datadir}/zoneinfo/Turkey"
RPROVIDES_tzdata-misc = "tzdata-misc"


FILES_${PN} += "${datadir}/zoneinfo/Pacific/Honolulu     \
                ${datadir}/zoneinfo/America/Anchorage    \
                ${datadir}/zoneinfo/America/Los_Angeles  \
                ${datadir}/zoneinfo/America/Denver       \
                ${datadir}/zoneinfo/America/Chicago      \
                ${datadir}/zoneinfo/America/New_York     \
                ${datadir}/zoneinfo/America/Caracas      \
                ${datadir}/zoneinfo/America/Sao_Paulo    \
                ${datadir}/zoneinfo/Europe/London        \
                ${datadir}/zoneinfo/Europe/Paris         \
                ${datadir}/zoneinfo/Africa/Cairo         \
                ${datadir}/zoneinfo/Europe/Moscow        \
                ${datadir}/zoneinfo/Asia/Dubai           \
                ${datadir}/zoneinfo/Asia/Karachi         \
                ${datadir}/zoneinfo/Asia/Dhaka           \
                ${datadir}/zoneinfo/Asia/Bankok          \
                ${datadir}/zoneinfo/Asia/Hong_Kong       \
                ${datadir}/zoneinfo/Asia/Tokyo           \
                ${datadir}/zoneinfo/Australia/Perth      \
                ${datadir}/zoneinfo/Australia/Darwin     \
                ${datadir}/zoneinfo/Australia/Adelaide   \
                ${datadir}/zoneinfo/Australia/Brisbane   \
                ${datadir}/zoneinfo/Australia/Sydney     \
                ${datadir}/zoneinfo/Pacific/Noumea       \
                ${datadir}/zoneinfo/CET                  \
                ${datadir}/zoneinfo/CST6CDT              \
                ${datadir}/zoneinfo/EET                  \
                ${datadir}/zoneinfo/EST                  \
                ${datadir}/zoneinfo/EST5EDT              \
                ${datadir}/zoneinfo/GB                   \
                ${datadir}/zoneinfo/GMT                  \
                ${datadir}/zoneinfo/GMT+0                \
                ${datadir}/zoneinfo/GMT-0                \
                ${datadir}/zoneinfo/GMT0                 \
                ${datadir}/zoneinfo/Greenwich            \
                ${datadir}/zoneinfo/HST                  \
                ${datadir}/zoneinfo/MET                  \
                ${datadir}/zoneinfo/MST                  \
                ${datadir}/zoneinfo/MST7MDT              \
                ${datadir}/zoneinfo/NZ                   \
                ${datadir}/zoneinfo/NZ-CHAT              \
                ${datadir}/zoneinfo/PRC                  \
                ${datadir}/zoneinfo/PST8PDT              \
                ${datadir}/zoneinfo/ROC                  \
                ${datadir}/zoneinfo/ROK                  \
                ${datadir}/zoneinfo/UCT                  \
                ${datadir}/zoneinfo/UTC                  \
                ${datadir}/zoneinfo/Universal            \
                ${datadir}/zoneinfo/W-SU                 \
                ${datadir}/zoneinfo/WET                  \
                ${datadir}/zoneinfo/Zulu                 \
                ${datadir}/zoneinfo/Etc/*                \
                ${datadir}/zoneinfo/iso3166.tab          \
                ${datadir}/zoneinfo/zone.tab             \
                ${sysconfdir}/localtime                  \
                ${sysconfdir}/timezone                   "
