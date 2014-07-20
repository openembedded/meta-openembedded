SUMMARY = "MKVToolNix -- Cross-platform tools for Matroska"
HOMEPAGE = "http://www.bunkus.org/videotools/mkvtoolnix/source.html"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "curl boost expat zlib libebml libmatroska libogg libvorbis bzip2 lzo file ruby-native"

SRC_URI = "http://www.bunkus.org/videotools/mkvtoolnix/sources/mkvtoolnix-${PV}.tar.xz"
SRC_URI[md5sum] = "ddd5ce6288d2fdaa6b79cb6d7bfffb04"
SRC_URI[sha256sum] = "2bbdf060e193d4a7f961f84d28b28d67d859be66e3f2cdf8ee4ae380f8d15725"

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
    ${S}/drake
}

do_install() {
    ${S}/drake install DESTDIR=${D}
}
