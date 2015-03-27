SUMMARY = "HTML5 (plugin-free) web-based terminal emulator and SSH client"
LICENSE = "AGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=834cbc6995db88433db17cdf8953a428"
HOMEPAGE = "http://liftoffsoftware.com/Products/GateOne"

PV = "1.2"
SRCREV = "1528d324088fc1c180b7fdf50f5b5c1af057eef6"
SRC_URI = "git://github.com/liftoff/GateOne.git \
           file://0001-configuration.py-Hack-around-broken-gethostname-thin.patch \
           file://gateone-avahi.service \
           file://80oe.conf \
           file://gateone.service \
           file://gateone-init \
"

S = "${WORKDIR}/git"

inherit distutils python-dir systemd update-rc.d
export prefix = "${localstatedir}"

DISTUTILS_INSTALL_ARGS = "--root=${D} \
    --prefix=${prefix} \
    --install-lib=${PYTHON_SITEPACKAGES_DIR} \
    --install-data=${PYTHON_SITEPACKAGES_DIR} \
    --install-scripts=${bindir} \
    --skip_init_scripts"

do_install_append() {

    # fix up hardcoded paths
    sed -i -e s:/usr/bin:${bindir}:g ${WORKDIR}/gateone.service
    
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/gateone.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/gateone-init ${D}${sysconfdir}/init.d/gateone
    
    install -m 0755 -d ${D}${sysconfdir}/avahi/services/
    install -m 0644 ${WORKDIR}/gateone-avahi.service ${D}${sysconfdir}/avahi/services/

    install -m 0755 -d ${D}${sysconfdir}/gateone/conf.d/
    install -m 0644 ${WORKDIR}/80oe.conf ${D}${sysconfdir}/gateone/conf.d/80oe.conf

    install -d ${D}${localstatedir}/lib/gateone
}

FILES_${PN} = "${localstatedir}/lib ${bindir} ${base_libdir} ${sysconfdir} ${PYTHON_SITEPACKAGES_DIR}"
RDEPENDS_${PN} = "mime-support \
                  openssh-ssh \
                  python-compression \
                  python-crypt \
                  python-datetime \
                  python-email \
                  python-fcntl \
                  python-futures \
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
                  python-setuptools \
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
