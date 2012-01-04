DESCRIPTION = "The Network Time Protocol (NTP) is used to \
synchronize the time of a computer client or server to \
another server or reference time source, such as a radio \
or satellite receiver or modem."
HOMEPAGE = "http://ntp.isc.org/bin/view/Main/WebHome"
SECTION = "console/network"
LICENSE = "ntp"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=fea4b50c33b18c2194b4b1c9ca512670"
RSUGGESTS_${PN} = "iana-etc"

SRC_URI = "http://www.eecis.udel.edu/~ntp/ntp_spool/ntp4/ntp-4.2/${P}.tar.gz \
	file://ipv6only-workaround.patch \
	file://ntpd \
	file://ntp.conf \
	file://ntpdate \
	file://ntpd.service \
"

inherit autotools update-rc.d

INITSCRIPT_NAME = "ntpd"
# No dependencies, so just go in at the standard level (20)
INITSCRIPT_PARAMS = "defaults"

# The ac_cv_header_readline_history is to stop ntpdc depending on either
# readline or curses
EXTRA_OECONF = "--without-openssl --without-crypto ac_cv_header_readline_history_h=no"
CFLAGS_append = " -DPTYS_ARE_GETPT -DPTYS_ARE_SEARCHED"

PACKAGES += "ntpdate ${PN}-bin ${PN}-tickadj ${PN}-utils"
# NOTE: you don't need ntpdate, use "ntpd -q -g -x"
# or the ntpdate systemd service

# This should use rc.update
FILES_ntpdate = "${bindir}/ntpdate ${sysconfdir}/init.d/ntpdate"
