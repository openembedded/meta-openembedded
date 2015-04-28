SUMMARY = "OpenSAF is an open source implementation of the SAF AIS specification"
DESCRIPTION = "OpenSAF is an open source project established to develop a base platform \
middleware consistent with Service Availability Forum (SA Forum) \
specifications, under the LGPLv2.1 license. The OpenSAF Foundation was \
established by leading Communications and Enterprise Computing Companies to \
facilitate the OpenSAF Project and to accelerate the adoption of the OpenSAF \
code base in commercial products. \
The OpenSAF project was launched in mid 2007 and has been under development by \
an informal group of supporters of the OpenSAF initiative. The OpenSAF \
Foundation was founded on January 22nd 2008 with Emerson Network Power, \
Ericsson, Nokia Siemens Networks, HP and Sun Microsystems as founding members."
HOMEPAGE = "http://www.opensaf.org"

inherit autotools useradd systemd pkgconfig

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/releases/${BPN}-${PV}.tar.gz \
           file://install-samples-from-srcdir.patch"

SRC_URI[md5sum] = "534c0a99438a62c4c8dda56cfa67300c"
SRC_URI[sha256sum] = "2f5ba57fe67e94099c0df82d0a0dd207b5c583c93030035ba354c97b5471b590"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=a916467b91076e631dd8edb7424769c7"

DEPENDS = "libxml2 python"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "-f -r opensaf"
USERADD_PARAM_${PN} =  "-r -g opensaf -d ${datadir}/opensaf/ -s ${sbindir}/nologin -c \"OpenSAF\" opensaf"

SYSTEMD_SERVICE_${PN} += "opensafd.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN} += "${localstatedir}/run"

RDEPENDS_${PN} += "bash python"

INSANE_SKIP_${PN} = "dev-so"

do_install_append() {
    rm -fr "${D}${localstatedir}/lock"
    rm -fr "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}
