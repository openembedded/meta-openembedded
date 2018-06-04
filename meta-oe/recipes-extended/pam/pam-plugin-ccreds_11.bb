SUMMARY = "PAM cached credentials module"
HOMEPAGE = "https://www.padl.com/OSS/pam_ccreds.html"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libpam openssl db"

inherit distro_features_check
REQUIRED_DISTRO_FEATURES = "pam"

SRCREV = "376bb189ceb3a113954f1012c45be7ff09e148ba"

SRC_URI = " \
    git://github.com/PADL/pam_ccreds \
    file://0001-make-sure-we-don-t-overflow-the-data-buffer.patch \
    file://0002-add-minimum_uid-option.patch \
    file://0003-Set-EXTENSION_SO-for-all-linux-targets.patch \
"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--libdir=${base_libdir} "

FILES_${PN} += "${base_libdir}/security/pam*"
