SUMMARY = "Collects and summarises system performance statistics"
DESCRIPTION = "collectd is a daemon which collects system performance statistics periodically and provides mechanisms to store the values in a variety of ways, for example in RRD files."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "rrdtool curl mysql5 libpcap libxml2 yajl libgcrypt libtool"

SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2 \
           file://no-gcrypt-badpath.patch \
           file://collectd-version.patch \
           file://collectd.init"

SRC_URI[md5sum] = "29e61411e51845d5ae71ab676078867e"
SRC_URI[sha256sum] = "7b8906d1c8866155b31820ef108be92abcee7fcd278d386bf0d449e704ba4696"

inherit autotools pythonnative update-rc.d

# Floatingpoint layout, architecture dependent
# 'nothing', 'endianflip' or 'intswap'
FPLAYOUT ?= "--with-fp-layout=nothing"

PACKAGECONFIG ??= ""
PACKAGECONFIG[snmp] = "--enable-snmp,--disable-snmp --with-libnetsnmp=no,net-snmp"
PACKAGECONFIG[libmemcached] = "--with-libmemcached,--without-libmemcached,libmemcached"
PACKAGECONFIG[iptables] = "--enable-iptables,--disable-iptables,iptables"
PACKAGECONFIG[postgresql] = "--enable-postgresql --with-libpq=yes, \
        --disable-postgresql --with-libpq=no,postgresql"
PACKAGECONFIG[dbi] = "--enable-dbi,--disable-dbi,libdbi"
PACKAGECONFIG[modbus] = "--enable-modbus,--disable-modbus,libmodbus"
PACKAGECONFIG[libowcapi] = "--with-libowcapi,--without-libowcapi,owfs"
PACKAGECONFIG[sensors] = "--enable-sensors --with-libsensors=yes, \
        --disable-sensors --with-libsensors=no,lmsensors"

EXTRA_OECONF = " \
                ${FPLAYOUT} \
                --disable-perl --with-libperl=no --with-perl-bindings=no \
                --with-libgcrypt=${STAGING_BINDIR_CROSS}/libgcrypt-config \
                --disable-notify_desktop \
"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/collectd.init ${D}${sysconfdir}/init.d/collectd
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${sysconfdir}/init.d/collectd
    sed -i 's!/etc/!${sysconfdir}/!g' ${D}${sysconfdir}/init.d/collectd
    sed -i 's!/var/!${localstatedir}/!g' ${D}${sysconfdir}/init.d/collectd
    sed -i 's!^PATH=.*!PATH=${base_sbindir}:${base_bindir}:${sbindir}:${bindir}!' ${D}${sysconfdir}/init.d/collectd

    # Fix configuration file to allow collectd to start up
    sed -i 's!^#FQDNLookup[ \t]*true!FQDNLookup   false!g' ${D}${sysconfdir}/collectd.conf

    rmdir "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

INITSCRIPT_NAME = "collectd"
INITSCRIPT_PARAMS = "defaults"

