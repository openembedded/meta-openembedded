# This package builds tools to manage NIS
# The source package is utils/net/NIS/yp-tools
#
PR = "r3"
DESCRIPTION="\
Network Information Service tools.  \
This package contains ypcat, ypmatch, ypset, \
ypwhich, yppasswd, domainname, nisdomainname \
and ypdomainname."

require nis.inc
SRC_URI = "http://www.linux-nis.org/download/yp-tools/${BP}.tar.bz2 \
           file://domainname.service \
"

SRC_URI[md5sum] = "ce1e06d86caa285fa8cd76fdf103f51e"
SRC_URI[sha256sum] = "6ae8321666eea7837da343eea90ea30273fb74943ad111d5a4befd2afb252063"

CACHED_CONFIGUREVARS += "ac_cv_prog_STRIP=/bin/true"

inherit systemd

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "domainname.service"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/domainname.service ${D}${systemd_unitdir}/system
}
