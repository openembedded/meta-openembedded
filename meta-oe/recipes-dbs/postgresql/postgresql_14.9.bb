require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=c31f662bb2bfb3b4187fe9a53e0ffe7c"

SRC_URI += "\
   file://not-check-libperl.patch \
   file://0001-Add-support-for-RISC-V.patch \
   file://0001-Improve-reproducibility.patch \
   file://0001-configure.ac-bypass-autoconf-2.69-version-check.patch \
   file://0001-config_info.c-not-expose-build-info.patch \
   file://0001-postgresql-fix-ptest-failure-of-sysviews.patch \
   file://CVE-2023-5868.patch \
   file://CVE-2023-5869.patch \
   file://CVE-2023-5870.patch \
"

SRC_URI[sha256sum] = "b1fe3ba9b1a7f3a9637dd1656dfdad2889016073fd4d35f13b50143cbbb6a8ef"

CVE_CHECK_IGNORE += "\
   CVE-2017-8806 \
"
