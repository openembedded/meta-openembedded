require ntp_${PV}.bb
DEPENDS = "openssl"

S = "${WORKDIR}/ntp-${PV}"

EXTRA_OECONF = "--with-openssl-libdir=${STAGING_LIBDIR} \
	        --with-openssl-incdir=${STAGING_INCDIR}/openssl"


SRC_URI[md5sum] = "98e16c7aa4ecd4c004b51bff18962e95"
SRC_URI[sha256sum] = "9f4a5271a285d390c9225e3ea28f70049ea377d30fc6de4659007cfff278671a"
