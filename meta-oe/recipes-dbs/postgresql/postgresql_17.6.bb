require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=08b6032a749e67f6e3de84ea8e466933"

SRC_URI += "\
   file://not-check-libperl.patch \
   file://0001-Add-support-for-RISC-V.patch \
   file://0002-Improve-reproducibility.patch \
   file://0003-configure.ac-bypass-autoconf-2.69-version-check.patch \
   file://0004-config_info.c-not-expose-build-info.patch \
   file://0005-postgresql-fix-ptest-failure-of-sysviews.patch \
   file://0001-tcl.m4-Recognize-tclsh9.patch \
"

SRC_URI[sha256sum] = "e0630a3600aea27511715563259ec2111cd5f4353a4b040e0be827f94cd7a8b0"

CVE_STATUS[CVE-2017-8806] = "not-applicable-config: Ddoesn't apply to out configuration of postgresql so we can safely ignore it."
