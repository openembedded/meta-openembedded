SUMMARY = "MKVToolNix -- Cross-platform tools for Matroska"
HOMEPAGE = "http://www.bunkus.org/videotools/mkvtoolnix/source.html"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "curl boost expat zlib libebml libmatroska libogg libvorbis bzip2 lzo file ruby-native"

PV = "7.1.0+git${SRCPV}"
SRCREV = "0c89ff941bfdd9f3378312f293a84f13cf3e2a96"
SRC_URI = "git://github.com/mbunkus/mkvtoolnix.git"

S = "${WORKDIR}/git"

inherit autotools-brokensep gettext

# make sure rb files are used from sysroot, not from host
# ruby-1.9.3-always-use-i386.patch is doing target_cpu=`echo $target_cpu | sed s/i.86/i386/`
# we need to replace it too (a bit longer version without importing re)
RUBY_SYS = "${@ '${BUILD_SYS}'.replace('i486', 'i386').replace('i586', 'i386').replace('i686', 'i386') }"
export RUBYLIB="${STAGING_DATADIR_NATIVE}/rubygems:${STAGING_LIBDIR_NATIVE}/ruby:${STAGING_LIBDIR_NATIVE}/ruby/${RUBY_SYS}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[flac] = "--with-flac,--without-flac,flac"

EXTRA_OECONF = " --with-boost-libdir=${STAGING_LIBDIR} \
"

# remove some hardcoded searchpaths
do_configure_prepend() {
    sed -i -e s:/usr/local/lib:${STAGING_LIBDIR}:g -e s:/usr/local/include:${STAGING_INCDIR}:g ${S}/ac/ebml.m4
}

# Yeah, no makefile
do_compile() {
    ${S}/drake ${PARALLEL_MAKE}
}

do_install() {
    ${S}/drake install DESTDIR=${D}
}
