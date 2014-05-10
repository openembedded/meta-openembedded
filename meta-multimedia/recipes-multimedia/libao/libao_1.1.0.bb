SUMMARY = "Cross-platform audio output library and plugins"
DESCRIPTION = "Libao is a cross-platform audio library that allows programs to \
               output audio using a simple API on a wide variety of platforms."
SECTION = "multimedia"
HOMEPAGE = "https://www.xiph.org/ao/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI="http://downloads.xiph.org/releases/ao/${BP}.tar.gz"
SRC_URI[md5sum] = "2b2508c29bc97e4dc218fa162cf883c8"
SRC_URI[sha256sum] = "29de5bb9b1726ba890455ef7e562d877df87811febb0d99ee69164b88c171bd4"

inherit autotools

PACKAGES_DYNAMIC += "^${BPN}-plugin-.*"

do_install_append () {
    find "${D}" -name '*.la' -exec rm -f {} +
}

python populate_packages_prepend () {
    rootdir = bb.data.expand('${libdir}/ao/plugins-4', d)
    rootdir_dbg = bb.data.expand('${libdir}/ao/plugins-4/.debug', d)
    do_split_packages(d, rootdir, '^(.*)\.so$', output_pattern='${BPN}-plugin-%s', description='AO %s plugin')
    do_split_packages(d, rootdir_dbg, '^(.*)\.so$', output_pattern='${BPN}-plugin-%s-dbg', description='AO %s plugin debug data')
}

PACKAGECONFIG ?= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d) ${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)"
PACKAGECONFIG[esound] = "--enable-esd,--disable-esd,esound"
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"
