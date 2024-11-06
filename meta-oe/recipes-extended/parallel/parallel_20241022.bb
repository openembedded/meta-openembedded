SUMMARY = "GNU Parallel - A shell tool for executing jobs in parallel using one or more computers"
DESCRIPTION = "GNU Parallel is a command-line tool for executing jobs in parallel on one or more computers. \
It allows users to run multiple commands simultaneously, making it easier to process large batches of tasks."

HOMEPAGE = "https://www.gnu.org/software/parallel/"
LICENSE = "CC-BY-SA-4.0 & GFDL-1.3-or-later & GPL-3.0-or-later"

LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=8da5784ab1c72e63ac74971f88658166 \
    file://LICENSES/CC-BY-SA-4.0.txt;md5=7130783469368ceb248a4f03e89ea4b8\
    file://LICENSES/GFDL-1.3-or-later.txt;md5=e0771ae6a62dc8a2e50b1d450fea66b7\
"

SRC_URI = "https://ftp.gnu.org/gnu/parallel/parallel-${PV}.tar.bz2"
SRC_URI[sha256sum] = "6de22bf1c67f7f316670d21ed1a2a32f1214dfbd3e420939ba937925c0a57a12"

inherit autotools bash-completion

DEPENDS += "perl"

RDEPENDS:${PN} += "perl \
        perl-module-ipc-open3 \
        perl-module-getopt-long \
        perl-module-file-temp \
        perl-module-filehandle \
        perl-module-file-glob \
"
do_install:append() {
    rm -rf ${D}${datadir}/zsh
}
