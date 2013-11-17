DESCRIPTION = "HTML5 (plugin-free) web-based terminal emulator and SSH client"
LICENSE = "AGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ee5b168fc7de89a0cadc49e27830aa2c"

PR = "r13"

PV = "1.1"
SRCREV = "ea5db3dcb3bbbe445ae6d1a5611c1f8d547c57b9"
SRC_URI = "git://github.com/liftoff/GateOne.git \
           file://gateone-avahi.service \
           file://server.conf \
           file://gateone.service \
           file://gateone-init \
"

S = "${WORKDIR}/git"

inherit distutils python-dir systemd update-rc.d

export prefix = "${localstatedir}/lib"

do_install_append() {
    install -d ${D}${localstatedir}/log/${BPN}

    install -m 0755 -d ${D}${sysconfdir}/avahi/services/
    install -m 0644 ${WORKDIR}/gateone-avahi.service ${D}${sysconfdir}/avahi/services/

    install -m 0644 ${WORKDIR}/server.conf ${D}/var/lib/gateone/server.conf
    
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/gateone.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/gateone-init ${D}${sysconfdir}/init.d/gateone
}

FILES_${PN} = "${localstatedir}/lib ${localstatedir}/log ${localstatedir}/volatile/log ${base_libdir} ${sysconfdir} ${PYTHON_SITEPACKAGES_DIR}"
RDEPENDS_${PN} = "file \
                  mime-support \
                  openssh-ssh \
                  python-compression \
                  python-crypt \
                  python-datetime \
                  python-email \
                  python-fcntl \
                  python-html \
                  python-imaging \
                  python-io \
                  python-json \
                  python-logging \
                  python-misc \
                  python-multiprocessing \
                  python-netclient \
                  python-pkgutil \
                  python-pyopenssl \
                  python-re \
                  python-readline \
                  python-shell \
                  python-simplejson \
                  python-subprocess \
                  python-syslog \
                  python-terminal \
                  python-textutils \
                  python-tornado \
                  python-unixadmin \
                  python-xml \
"

SYSTEMD_SERVICE_${PN} = "gateone.service"
INITSCRIPT_NAME = "gateone"
