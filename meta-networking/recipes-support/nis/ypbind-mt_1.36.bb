# This package builds the NIS ypbind daemon
# The source package is utils/net/NIS/ypbind-mt
#
PR = "r0"
DESCRIPTION="\
Multithreaded NIS bind service (ypbind-mt).  \
ypbind-mt is a complete new implementation of a NIS \
binding daemon for Linux. It has the following \
features.  Supports ypbind protocol V1 and V2.  \
Uses threads for better response.  Supports multiple \
domain bindings.  Supports /var/yp/binding/* file \
for Linux libc 4/5 and glibc 2.x.  Supports a list \
of known secure NIS server (/etc/yp.conf) Binds to \
the server which answered as first."
HOMEPAGE="http://www.linux-nis.org/nis/ypbind-mt/index.html"

require nis.inc

SRC_URI = "http://www.linux-nis.org/download/ypbind-mt/${P}.tar.bz2"
SRC_URI[md5sum] = "135834db97d78ff6d79fdee2810b4056"

LIC_FILES_CHKSUM = "file://COPYING;md5=082c9a0886c7c3db1bc862b5b62ffe08"
SRC_URI[sha256sum] = "0eff76c1849f4b38ea1a60280d8397c4240369c641fe5402ce57edf1a90958c7"

# ypbind-mt now provides all the functionality of ypbind
# and is used in place of it.
PROVIDES += "ypbind"

CACHED_CONFIGUREVARS = "ac_cv_prog_STRIP=/bin/true"
SRC_URI += "file://ypbind-yocto.init"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d

    install -m 0755 ${WORKDIR}/ypbind-yocto.init ${D}${sysconfdir}/init.d/ypbind

    ln -s ../init.d/ypbind ${D}${sysconfdir}/rcS.d/S44ypbind
}
