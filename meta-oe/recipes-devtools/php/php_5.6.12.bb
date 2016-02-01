require php.inc

SRC_URI += "file://change-AC_TRY_RUN-to-AC_TRY_LINK.patch \
            file://CVE-2015-7803.patch \
            file://CVE-2015-7804.patch \
            file://CVE-2016-1903.patch \
"
SRC_URI[md5sum] = "4578dee9d979114610a444bee263ed9b"
SRC_URI[sha256sum] = "6f27104272af7b2a996f85e4100fac627630fbdaf39d7bd263f16cf529c8853a"
