SUMMARY = "lm_sensors"
DESCRIPTION = "Hardware health monitoring applications"
HOMEPAGE = "http://www.lm-sensors.org/"
LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "sysfsutils virtual/libiconv bison-native flex-native rrdtool"

SRC_URI = "http://dl.lm-sensors.org/lm-sensors/releases/lm_sensors-${PV}.tar.bz2 \
           file://fancontrol \
           file://fancontrol.sh \
           file://sensors.conf \
           file://sensord.conf \
           file://sensord.sh \
           file://sensord.cgi \
"

SRC_URI[md5sum] = "f357ba00b080ab102a170f7bf8bb2578"
SRC_URI[sha256sum] = "f13dd885406841a7352ccfb8b9ccb23c4c057abe3de4258da5444c149a9e3ae1"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}-fancontrol ${PN}-sensord"
INITSCRIPT_NAME_${PN}-fancontrol = "fancontrol.sh"
INITSCRIPT_NAME_${PN}-sensord = "sensord.sh"
INITSCRIPT_PARAMS_${PN}-fancontrol = "defaults 66"
INITSCRIPT_PARAMS_${PN}-sensord = "defaults 67"

S = "${WORKDIR}/lm_sensors-${PV}"

EXTRA_OEMAKE = 'LINUX=${STAGING_KERNEL_DIR} EXLDFLAGS="${LDFLAGS}" \
		MACHINE=${TARGET_ARCH} PREFIX=${prefix} CC="${CC}" AR="${AR}"'

do_compile() {
    oe_runmake user PROG_EXTRA="sensors sensord"
}

do_install() {
    oe_runmake user_install DESTDIR=${D}
    install -m 0755 ${S}/prog/sensord/sensord ${D}${bindir}
    install -m 0644 ${S}/prog/sensord/sensord.8 ${D}/usr/man/man8

    # move manuals into proper place
    install -d ${D}${mandir}
    rm -rf ${D}${mandir}/*
    mv ${D}/usr/man/* ${D}${mandir}
    rmdir ${D}/usr/man

    # Install directories
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/sensors.d

    # Install sensors.conf
    install -m 0644 ${WORKDIR}/sensors.conf ${D}${sysconfdir}/sensors.d

    # Install fancontrol config file
    install -m 0644 ${WORKDIR}/fancontrol ${D}${sysconfdir}

    # Install fancontrol init script
    install -m 0755 ${WORKDIR}/fancontrol.sh ${D}${sysconfdir}/init.d

    # Install sensord init script
    install -m 0755 ${WORKDIR}/sensord.sh ${D}${sysconfdir}/init.d
    install -m 0644 ${WORKDIR}/sensord.conf ${D}${sysconfdir}

    # Install sensord.cgi script and create world-writable
    # web-accessible sensord directory.
    install -d ${D}/www/pages/cgi-bin
    install -m 0755 ${WORKDIR}/sensord.cgi ${D}/www/pages/cgi-bin
    install -d -m a=rwxs ${D}/www/pages/sensord
}

# libsensors packages
PACKAGES =+ "${PN}-libsensors ${PN}-libsensors-dbg ${PN}-libsensors-dev ${PN}-libsensors-staticdev ${PN}-libsensors-doc"

# sensors command packages
PACKAGES =+ "${PN}-sensors ${PN}-sensors-dbg ${PN}-sensors-doc"

# sensord logging daemon
PACKAGES =+ "${PN}-sensord ${PN}-sensord-dbg ${PN}-sensord-doc"

# fancontrol script
PACKAGES =+ "${PN}-fancontrol ${PN}-fancontrol-doc"

# sensors-detect script
PACKAGES =+ "${PN}-sensorsdetect ${PN}-sensorsdetect-doc"

# sensors-conf-convert script
PACKAGES =+ "${PN}-sensorsconfconvert"

# pwmconfig script
PACKAGES =+ "${PN}-pwmconfig ${PN}-pwmconfig-doc"

# isadump and isaset helper program
PACKAGES =+ "${PN}-isatools ${PN}-isatools-dbg ${PN}-isatools-doc"

# libsensors files
FILES_${PN}-libsensors = "${libdir}/libsensors.so.* ${sysconfdir}/sensors3.conf ${sysconfdir}/sensors.d/sensors.conf"
FILES_${PN}-libsensors-dbg = "${libdir}/.debug ${prefix}/src/debug"
FILES_${PN}-libsensors-dev = "${libdir}/libsensors.so ${includedir}"
FILES_${PN}-libsensors-staticdev = "${libdir}/libsensors.a"
FILES_${PN}-libsensors-doc = "${mandir}/man3"

# sensors command files
FILES_${PN}-sensors = "${bindir}/sensors"
FILES_${PN}-sensors-dbg = "${bindir}/.debug/sensors"
FILES_${PN}-sensors-doc = "${mandir}/man1 ${mandir}/man5"
RDEPENDS_${PN}-sensors = "${PN}-libsensors"

# sensord logging daemon
FILES_${PN}-sensord = "${bindir}/sensord ${sysconfdir}/sensord.conf ${sysconfdir}/init.d/sensord.sh /www/*"
FILES_${PN}-sensord-dbg = "${bindir}/.debug/sensord"
FILES_${PN}-sensord-doc = "${mandir}/man8/sensord.8"
RDEPENDS_${PN}-sensord = "${PN}-sensors rrdtool lighttpd lighttpd-module-cgi"

# fancontrol script files
FILES_${PN}-fancontrol = "${sbindir}/fancontrol ${sysconfdir}/fancontrol ${sysconfdir}/init.d/fancontrol.sh"
FILES_${PN}-fancontrol-doc = "${mandir}/man8/fancontrol.8"
RDEPENDS_${PN}-fancontrol = "bash"

# sensors-detect script files
FILES_${PN}-sensorsdetect = "${sbindir}/sensors-detect"
FILES_${PN}-sensorsdetect-doc = "${mandir}/man8/sensors-detect.8"
RDEPENDS_${PN}-sensorsdetect = "${PN}-sensors perl perl-modules"

# sensors-conf-convert script files
FILES_${PN}-sensorsconfconvert = "${bindir}/sensors-conf-convert"
RDEPENDS_${PN}-sensorsconfconvert = "${PN}-sensors perl perl-modules"

# pwmconfig script files
FILES_${PN}-pwmconfig = "${sbindir}/pwmconfig"
FILES_${PN}-pwmconfig-doc = "${mandir}/man8/pwmconfig.8"
RDEPENDS_${PN}-pwmconfig = "${PN}-fancontrol"

# isadump and isaset helper program files
FILES_${PN}-isatools = "${sbindir}/isa*"
FILES_${PN}-isatools-dbg = "${sbindir}/.debug/isa*"
FILES_${PN}-isatools-doc = "${mandir}/man8/isa*"
