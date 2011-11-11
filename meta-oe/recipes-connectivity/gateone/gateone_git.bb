DESCRIPTION = "HTML5 (plugin-free) web-based terminal emulator and SSH client"
LICENSE = "AGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=07d5a2790569bd3e3b422b69ccd43bec"

PR = "r7"

PV = "0.9"
SRCREV = "78d1263b9b8823d97eb29ad9605e52dc8f0ddf55"
SRC_URI = "git://github.com/liftoff/GateOne.git \
           file://gateone-avahi.service \
           file://gateone.service \
          "

S = "${WORKDIR}/git"

inherit distutils systemd

do_configure_prepend() {
	sed -i -e s:/opt:${D}${localstatedir}/lib: setup.py
}

do_install_append() {
	install -d ${D}${localstatedir}/log/${BPN}

	install -m 0755 -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/gateone.service ${D}${base_libdir}/systemd/system/

	install -m 0755 -d ${D}${sysconfdir}/avahi/services/
	install -m 0644 ${WORKDIR}/gateone-avahi.service ${D}${sysconfdir}/avahi/services/
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE = "gateone.service"

FILES_${PN} = "${localstatedir} ${base_libdir} ${sysconfdir}"
RDEPENDS_${PN} = "python-tornado \
                  python-datetime \
                  python-shell \
                  python-subprocess \
                  python-terminal \
                  python-io \
                  python-compression \
                  python-syslog \
                  python-misc \
                  python-crypt \
                  python-netclient \
                  python-email \
                  python-html \
                  python-textutils \
                  python-pyopenssl \
                  findutils \
                  python-simplejson \
                  python-multiprocessing \
                  file \
                  openssh-ssh \
                  mime-support \
                 "
