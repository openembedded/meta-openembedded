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
LIC_FILES_CHKSUM = "file://COPYING;md5=1e86753638d3cf2512528b99079bc4f3"

SRC_URI = "${SOURCEFORGE_MIRROR}/mpg123/mpg123/${PV}/${BP}.tar.bz2"
SRC_URI[md5sum] = "87731f1437cfd8c50537a606d81130e4"
SRC_URI[sha256sum] = "869024e01d7cb4dae1aea2f2a10420d4be7e1ac02a9c434d06d727599169b01b"

inherit autotools pkgconfig

EXTRA_OECONF = " \
    --enable-shared \
    --with-module-suffix=.so \
    ${@bb.utils.contains('TUNE_FEATURES', 'neon', '--with-cpu=neon', '', d)} \
    ${@bb.utils.contains('TUNE_FEATURES', 'altivec', '--with-cpu=altivec', '', d)} \
"

