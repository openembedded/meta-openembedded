SUMMARY = "Hardware performance monitoring counters"
HOMEPAGE = "http://tiptop.gforge.inria.fr/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "ncurses libxml2"

SRC_URI = "http://tiptop.gforge.inria.fr/releases/${BP}.tar.gz"
SRC_URI[md5sum] = "74255a29acf44569db021b080ad3e3f7"
SRC_URI[sha256sum] = "965cb99b16cb59df78363d83b62877ce8501b0aac1116a86bed8a16aa96b171d"

inherit autotools-brokensep
EXTRA_OECONF = "CFLAGS="$CFLAGS -I${STAGING_INCDIR}/libxml2""
COMPATIBLE_HOST = "(i.86|x86_64|arm|powerpc).*-linux"

do_configure_prepend () {
    # Two bugs in configure.ac when cross-compiling.
    # 1. The path of libxml2. Specify it in EXTRA_OECONF.
    # 2. hw's value on other platforms. Replace it if the target is 
    #    not i*86/x86_64.
    if ( echo "${TARGET_ARCH}" | grep -q -e 'i.86' -e 'x86_64' ); then
        sed -i 's= -I/usr/include/libxml2=='    ${S}/configure.ac
    else
        sed -i 's/hw=`uname -m`/hw="unknown"/'  ${S}/configure.ac
        sed -i 's= -I/usr/include/libxml2=='    ${S}/configure.ac
    fi
}
