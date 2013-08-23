SUMMARY = "Audio decoder for MPEG-1 Layer 1/2/3"
DESCRIPTION = "The core of mpg123 is an MPEG-1 Layer 1/2/3 decoding library, which can be used by other programs. \
mpg123 also comes with a command-line tool which can playback using ALSA, PulseAudio, OSS, and several other APIs, \
and also can write the decoded audio to WAV."
HOMEPAGE = "http://mpg123.de/"
BUGTRACKER = "http://sourceforge.net/p/mpg123/bugs/"
SECTION = "multimedia"

# The options should be mutually exclusive for configuration script.
# If both alsa and pulseaudio are specified (as in the default distro features)
# pulseaudio takes precedence.
PACKAGECONFIG_ALSA = "${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)}"
PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '${PACKAGECONFIG_ALSA}', d)}"
PACKAGECONFIG[pulseaudio] = "--with-default-audio=pulse,,pulseaudio"
PACKAGECONFIG[alsa] = "--with-default-audio=alsa,,alsa-lib"

LICENSE = "LGPLv2.1"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=a7aa23a2b646eca38ad4eeb7a853761c"

SRC_URI = "${SOURCEFORGE_MIRROR}/mpg123/mpg123/${PV}/mpg123-${PV}.tar.bz2"

SRC_URI[md5sum] = "f734f9e2982f398a1c919475fc0b3798"
SRC_URI[sha256sum] = "b6b1aef887835f83ac3d4acb5701ae619041fc60d76548ad779a409080338df7" 

S = "${WORKDIR}/mpg123-${PV}"

inherit autotools pkgconfig

EXTRA_OECONF = " \
    --enable-shared \
    ${@bb.utils.contains('TUNE_FEATURES', 'neon', '--with-cpu=neon', '', d)} \
    ${@bb.utils.contains('TUNE_FEATURES', 'altivec', '--with-cpu=altivec', '', d)} \
"

