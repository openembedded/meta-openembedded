SUMMARY = "Kernel analysis utility for live systems, netdump, diskdump, kdump, LKCD or mcore dumpfiles"
DESCRIPTION = "The core analysis suite is a self-contained tool that can be used to\
investigate either live systems, kernel core dumps created from the\
netdump, diskdump and kdump packages from Red Hat Linux, the mcore kernel patch\
offered by Mission Critical Linux, or the LKCD kernel patch."

HOMEPAGE = "http://people.redhat.com/anderson"
SECTION = "devel"

inherit gettext

DEPENDS = "zlib readline"

SRC_URI = "https://github.com/crash-utility/${BPN}/archive/${PV}.tar.gz \
           file://7001force_define_architecture.patch \ 
           file://7003cross_ranlib.patch \
           file://0001-cross_add_configure_option.patch \
           "

SRC_URI[md5sum] = "d70ad2ed0f6d210ed11e88b8e977f5fd"
SRC_URI[sha256sum] = "c6034c6eb6b52691c60d0b72dbdec58fac4c1b3ed1cd0697c209dc48d13a577e"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING3;md5=d32239bcb673463ab874e80d47fae504"

EXTRA_OEMAKE = 'RPMPKG="${PV}" \
                GDB_TARGET="${TARGET_SYS}" \
                GDB_HOST="${BUILD_SYS}" \
                '
do_configure() {
    :
}

do_compile_prepend() {
   case ${TARGET_ARCH} in
                arm*)     ARCH=ARM ;;
                i*86*)    ARCH=X86 ;;
                powerpc*) ARCH=PPC ;;
                x86_64*)  ARCH=X86_64 ;;
        esac

    sed -i s/FORCE_DEFINE_ARCH/"${ARCH}"/g ${S}/configure.c
    sed -i -e 's/#define TARGET_CFLAGS_ARM_ON_X86_64.*/#define TARGET_CFLAGS_ARM_ON_X86_64\t\"TARGET_CFLAGS=-D_FILE_OFFSET_BITS=64\"/g' ${S}/configure.c
    sed -i 's/&gt;/>/g' ${S}/Makefile
}
do_compile() {
    oe_runmake ${EXTRA_OEMAKE}
}

do_install () {
    install -d ${D}${bindir}
    install -d ${D}/${mandir}/man8
    install -d ${D}${includedir}/crash

    oe_runmake DESTDIR=${D} install
    install -m 0644 ${S}/crash.8 ${D}/${mandir}/man8/
    install -m 0644 ${S}/defs.h ${D}${includedir}/crash
}   
RDEPENDS_${PN} += "liblzma"
