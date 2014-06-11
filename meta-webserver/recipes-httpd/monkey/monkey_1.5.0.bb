SUMMARY = "Fast and Lightweight HTTP Server for Linux"
HOMEPAGE = "http://monkey-project.com"
BUGTRACKER = "https://github.com/monkey/monkey/issues"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

SECTION = "net"

SRC_URI = "http://monkey-project.com/releases/1.5/monkey-${PV}.tar.gz"
SRC_URI[md5sum] = "9123fc371334621fde1c060ab17adcd3"
SRC_URI[sha256sum] = "f7a5e4839822750930a723f63a4edbf700e0fb8299a4b3637bd8bab5b1d098a7"

EXTRA_OECONF = " \
             --plugdir=${libdir}/monkey/ \
             --logdir=${localstatedir}/log/monkey/ \
             --pidfile=${localstatedir}/run/monkey.pid \
             --default-user=www-data \
             --datadir= ${localstatedir}/www/monkey/ \
             --sysconfdir=${sysconfdir}/monkey/ \
             --enable-plugins=* \
             --disable-plugins=polarssl \
             --debug \
             --malloc-libc \
"

inherit autotools-brokensep pkgconfig update-rc.d systemd

INITSCRIPT_NAME = "monkey"
INITSCRIPT_PARAMS = "defaults 70"

SYSTEMD_SERVICE_${PN} = "monkey.service"

FILES_${PN} += "${localstatedir}/www/monkey/"

CONFFILES_${PN} = "${sysconfdir}/monkey/"
