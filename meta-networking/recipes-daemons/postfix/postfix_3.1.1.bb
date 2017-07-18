require postfix.inc

SRC_URI = "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://main.cf_2.0 \
           file://postfix \
           file://internal_recipient \
           file://postfix.service \
           file://aliasesdb \
           file://check_hostname.sh \
           file://0001-Check-for-glibc-before-setting-CANT_USE_SEND_RECV_MS.patch \
           "
SRC_URI[md5sum] = "40d72ea143af7ab0038c2cee1f483707"
SRC_URI[sha256sum] = "3deda4c34631970490b1b5fbb559905f93531bf1c7eb00e38b0d0deb1dba9982"
