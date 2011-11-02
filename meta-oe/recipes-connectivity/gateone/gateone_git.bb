DESCRIPTION = "HTML5 (plugin-free) web-based terminal emulator and SSH client"
LICENSE = "AGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=07d5a2790569bd3e3b422b69ccd43bec"

PR = "r3"

PV = "0.9"
SRCREV = "3a10118f10aeea457be0cbea89a5e1e642fbf59f"
SRC_URI = "git://github.com/liftoff/GateOne.git"

S = "${WORKDIR}/git"

inherit distutils

do_configure_prepend() {
	sed -i -e s:/opt:${D}${localstatedir}/lib: setup.py
}

FILES_${PN} = "${localstatedir}/lib/"
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
                 "
