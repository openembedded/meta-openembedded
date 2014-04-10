SUMMARY = "Control and monitor storage systems using S.M.A.R.T"
DESCRIPTION = \
"The smartmontools package contains two utility programs (smartctl \
and smartd) to control and monitor storage systems using the Self-\
Monitoring, Analysis and Reporting Technology System (SMART) built \
into most modern ATA and SCSI hard disks. In many cases, these \
utilities will provide advanced warning of disk degradation and failure."

HOMEPAGE = "http://smartmontools.sourceforge.net/"
SECTION = "console/utils"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "${SOURCEFORGE_MIRROR}/smartmontools/smartmontools-${PV}.tar.gz"

SRC_URI[md5sum] = "d44f84081a12cef79cd17f78044351fc"
SRC_URI[sha256sum] = "486f660579bb0fb4f6b927ded7531cb1f99685c666397377761c5b04dd96065b"

inherit autotools

