SUMMARY = "The Performance Application Programming Interface"
DESCRIPTION = "PAPI provides tool designers and application engineers \
with a consistent interface and methodology for the use of low-level \
performance counter hardware found across the entire compute system."
HOMEPAGE = "http://icl.utk.edu/papi/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c63687a44058f7e489517bc1fa0f6ef4"
SECTION = "devel"

inherit autotools

SRC_URI = "git://bitbucket.org/icl/papi.git;branch=stable-5.7;protocol=https \
           file://0001-Replace-plain-cp-with-install-mechanism.patch \
           file://0001-Remove-useless-rpath-to-validate-do_package_qa.patch \
          "

SRCREV = "e2a4992f2ada87f51a6dbb79bc57293abddef2a1"

S = "${WORKDIR}/git"
B = "${S}/src"
AUTOTOOLS_SCRIPT_PATH = "${B}"

EXTRA_OECONF_append = " \
    --with-arch=${TARGET_ARCH} \
    --with-ffsll \
    --with-walltimer=cycle \
    --with-tls=__thread \
    --with-virtualtimer=perfctr \
    --with-tests=''  \
    --with-perf-events \
    --oldincludedir=${STAGING_INCDIR} \
"

EXTRA_OECONF_append_arm = " --with-CPU=arm"
EXTRA_OECONF_append_aarch64 = " --with-CPU=arm"
