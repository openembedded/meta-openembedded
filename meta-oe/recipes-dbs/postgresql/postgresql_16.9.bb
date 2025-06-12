require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=d5dd73e90ee1a572679460ee67233fcc"

SRC_URI += "\
   file://not-check-libperl.patch \
   file://0001-Add-support-for-RISC-V.patch \
   file://0002-Improve-reproducibility.patch \
   file://0003-configure.ac-bypass-autoconf-2.69-version-check.patch \
   file://0004-config_info.c-not-expose-build-info.patch \
   file://0005-postgresql-fix-ptest-failure-of-sysviews.patch \
"

SRC_URI[sha256sum] = "07c00fb824df0a0c295f249f44691b86e3266753b380c96f633c3311e10bd005"

CVE_STATUS[CVE-2017-8806] = "not-applicable-config: Ddoesn't apply to out configuration of postgresql so we can safely ignore it."
