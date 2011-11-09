DESCRIPTION = "HTML5 (plugin-free) web-based terminal emulator and SSH client"
LICENSE = "AGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=07d5a2790569bd3e3b422b69ccd43bec"

PR = "r5"

PV = "0.9"
SRCREV = "031a85e8e24ae499e41e15b5077c33a4bd647bd3"
SRC_URI = "git://github.com/liftoff/GateOne.git"

S = "${WORKDIR}/git"

inherit distutils

do_configure_prepend() {
	sed -i -e s:/opt:${D}${localstatedir}/lib: setup.py
}

do_install_append() {
	install -d ${D}${localstatedir}/log/${BPN}
}

FILES_${PN} = "${localstatedir}"
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
