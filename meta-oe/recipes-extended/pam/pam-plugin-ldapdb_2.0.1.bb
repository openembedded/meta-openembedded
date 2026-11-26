SUMMARY = "PAM searchless LDAP authentication module"
HOMEPAGE = "https://github.com/smackysnacks/pam_ldapdb"
BUGTRACKER = "https://github.com/smackysnacks/pam_ldapdb/issues"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41ab94182d94be9bb35e2a8b933f1e7d"

DEPENDS = "libpam openldap"

inherit features_check
REQUIRED_DISTRO_FEATURES = "pam"

SRCREV = "bfd92da201b2ce412fc4094442427aa68c2526d8"
SRC_URI = "git://github.com/smackysnacks/pam_ldapdb;branch=master;protocol=https;tag=v${PV}"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"


do_install () {
    oe_runmake install DESTDIR=${D} PAMDIR=${base_libdir}/security
}

FILES:${PN} += "${base_libdir}/security/pam_ldapdb.so"
