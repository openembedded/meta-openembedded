SUMMARY = "User configurable send-only Mail Transfer Agent"
DESCRIPTION = "ESMTP is a user-configurable relay-only MTA \
with a sendmail-compatible syntax, based on libESMTP and \
supporting the AUTH (including the CRAM-MD5 and NTLM SASL \
mechanisms) and StartTLS SMTP extensions."
HOMEPAGE = "http://esmtp.sourceforge.net/"
SECTION = "console/network"

DEPENDS = "libesmtp"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "http://nchc.dl.sourceforge.net/project/${PN}/${PN}/${PV}/${PN}-${PV}.tar.bz2"

# Have to set this or we get -L/lib in LDFLAGS
EXTRA_OECONF = "--with-libesmtp=${STAGING_EXECPREFIXDIR}"

inherit autotools update-alternatives

ALTERNATIVE_${PN} = "sbinsendmail libsendmail"

ALTERNATIVE_LINK_NAME[sbinsendmail] = "${sbindir}/sendmail"
ALTERNATIVE_TARGET[sbinsendmail] = "${bindir}/esmtp"
ALTERNATIVE_LINK_NAME[libsendmail] = "${libdir}/sendmail"
ALTERNATIVE_TARGET[libsendmail] = "${bindir}/esmtp"
ALTERNATIVE_PRIORITY = "10"

SRC_URI[md5sum] = "79a9c1f9023d53f35bb82bf446150a72"
SRC_URI[sha256sum] = "a0d26931bf731f97514da266d079d8bc7d73c65b3499ed080576ab606b21c0ce"

FILES_${PN} += "${libdir}/"
