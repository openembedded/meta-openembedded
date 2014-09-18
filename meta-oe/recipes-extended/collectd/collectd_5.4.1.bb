SUMMARY = "Collects and summarises system performance statistics"
DESCRIPTION = "collectd is a daemon which collects system performance statistics periodically and provides mechanisms to store the values in a variety of ways, for example in RRD files."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "rrdtool curl mysql5 libpcap libxml2 yajl libgcrypt libtool lvm2 libmnl"

SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2 \
           file://no-gcrypt-badpath.patch \
           file://collectd-version.patch \
           file://glibc-2.20-compatiblity.patch \
           file://collectd.init \
           file://collectd.service"
SRC_URI[md5sum] = "6f56c71c96573a7f4f7fb3bfab185974"
SRC_URI[sha256sum] = "75452129f271cb0aad28e57f12a49070618bbb7b6a9d64cf869e8766fa2f66e0"

inherit autotools pythonnative update-rc.d pkgconfig systemd

SYSTEMD_SERVICE_${PN} = "collectd.service"

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
PACKAGECONFIG[amqp] = "--enable-amqp --with-librabbitmq=yes, \
        --disable-amqp --with-librabbitmq=no,rabbitmq-c"
# protobuf-c that is currently only available in meta-virtualization layer
PACKAGECONFIG[pinba] = "--enable-pinba,--disable-pinba,protobuf-c-native protobuf-c"

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

    # Install systemd unit files
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/collectd.service ${D}${systemd_unitdir}/system
    sed -i -e 's,@SBINDIR@,${sbindir},g' \
        ${D}${systemd_unitdir}/system/collectd.service
}

INITSCRIPT_NAME = "collectd"
INITSCRIPT_PARAMS = "defaults"

# threshold.so load.so are also provided by gegl
# disk.so is also provided by libgphoto2-camlibs
PRIVATE_LIBS = "threshold.so load.so disk.so"
