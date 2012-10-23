require ntp.inc
DEPENDS = "openssl"

S = "${WORKDIR}/ntp-${PV}"

EXTRA_OECONF += "--with-openssl-libdir=${STAGING_LIBDIR} \
		 --with-openssl-incdir=${STAGING_INCDIR}/openssl \
		 --with-crypto"

FILES_${PN} += "${bindir}/sntp ${bindir}/ntp-keygen"
