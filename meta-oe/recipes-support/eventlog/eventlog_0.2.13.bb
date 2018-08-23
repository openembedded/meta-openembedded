SUMMARY = "Replacement syslog API"
HOMEPAGE = "http://www.balabit.com/network-security/syslog-ng/opensource-logging-system"
DESCRIPTION = "The EventLog library aims to be a replacement of the \
              simple syslog() API provided on UNIX systems. The \
              major difference between EventLog and syslog is that \
              EventLog tries to add structure to messages. EventLog \
              provides an interface to build, format and output an \
              event record. The exact format and output method can \
              be customized by the administrator via a configuration \
              file. his package is the runtime part of the library. \
"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=b8ba8e77bcda9a53fac0fe39fe957767"

PV = "0.2.13+git${SRCPV}"
SRCREV = "a5c19163ba131f79452c6dfe4e31c2b4ce4be741"

SRC_URI = "git://github.com/balabit/eventlog;protocol=https"

S = "${WORKDIR}/git"

inherit autotools
