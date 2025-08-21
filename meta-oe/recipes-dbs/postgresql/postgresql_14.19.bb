require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=08b6032a749e67f6e3de84ea8e466933"

SRC_URI += "\
   file://not-check-libperl.patch \
   file://0001-Add-support-for-RISC-V.patch \
   file://0001-Improve-reproducibility.patch \
   file://0001-configure.ac-bypass-autoconf-2.69-version-check.patch \
   file://0001-config_info.c-not-expose-build-info.patch \
   file://0001-postgresql-fix-ptest-failure-of-sysviews.patch \
"

SRC_URI[sha256sum] = "727e9e334bc1a31940df808259f69fe47a59f6d42174b22ae62d67fe7a01ad80"

CVE_CHECK_IGNORE += "\
   CVE-2017-8806 \
"
