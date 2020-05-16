require postfix.inc

SRC_URI += "ftp://ftp.porcupine.org/mirrors/postfix-release/official/postfix-${PV}.tar.gz \
           file://makedefs.patch \
           file://install.patch \
           file://main.cf \
           file://postfix \
           file://internal_recipient \
           file://postfix.service \
           file://aliasesdb \
           file://check_hostname.sh \
           file://0001-makedefs-Use-native-compiler-to-build-makedefs.test.patch \
           file://postfix-install.patch \
           file://icu-config.patch \
           file://0001-makedefs-add-lnsl-and-lresolv-to-SYSLIBS-by-default.patch \
           file://0001-Fixed-build-failure-with-glibc-2.30-due-to-dropped-R.patch \
           "
SRC_URI[sha256sum] = "908a66fc38537a0047e8561e1bc0ef096c53357ffad16d2d728cd4fc8ae56654"
UPSTREAM_CHECK_REGEX = "postfix\-(?P<pver>3\.3(\.\d+)+).tar.gz"
