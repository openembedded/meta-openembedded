SUMMARY = "Audio decoder for MPEG-1 Layer 1/2/3"
DESCRIPTION = "The core of mpg123 is an MPEG-1 Layer 1/2/3 decoding library, which can be used by other programs. \
mpg123 also comes with a command-line tool which can playback using ALSA, PulseAudio, OSS, and several other APIs, \
and also can write the decoded audio to WAV."
HOMEPAGE = "http://mpg123.de/"
BUGTRACKER = "http://sourceforge.net/p/mpg123/bugs/"
SECTION = "multimedia"

DEPENDS = "tslib audiofile"

# The options should be mutually exclusive for configuration script.
# If both alsa and pulseaudio are specified (as in the default distro features)
# pulseaudio takes precedence.
PACKAGECONFIG_ALSA = "${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)}"
PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '${PACKAGECONFIG_ALSA}', d)}"
PACKAGECONFIG[pulseaudio] = "--with-default-audio=pulse,,pulseaudio"
PACKAGECONFIG[alsa] = "--with-default-audio=alsa,,alsa-lib"
PACKAGECONFIG[sdl] = ",,libsdl"
PACKAGECONFIG[openal] = ",,openal-soft"
PACKAGECONFIG[jack] = ",,jack"
PACKAGECONFIG[portaudio] = ",,portaudio-v19"
PACKAGECONFIG[esd] = ",,esound"

# Following are possible sound output modules
#alsa tinyalsa oss coreaudio sndio sun win32 win32_wasapi os2 esd jack portaudio pulse sdl nas arts openal dummy
AUDIOMODS += "${@base_contains('PACKAGECONFIG', 'alsa', 'alsa', '', d)}"
AUDIOMODS += "${@base_contains('PACKAGECONFIG', 'pulseaudio', 'pulse', '', d)}"
AUDIOMODS += "${@base_contains('PACKAGECONFIG', 'sdl', 'sdl', '', d)}"
AUDIOMODS += "${@base_contains('PACKAGECONFIG', 'openal', 'openal', '', d)}"
AUDIOMODS += "${@base_contains('PACKAGECONFIG', 'jack', 'jack', '', d)}"
AUDIOMODS += "${@base_contains('PACKAGECONFIG', 'portaudio', 'portaudio', '', d)}"
AUDIOMODS += "${@base_contains('PACKAGECONFIG', 'esd', 'esd', '', d)}"

LICENSE = "LGPLv2.1"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=1e86753638d3cf2512528b99079bc4f3"

SRC_URI = "${SOURCEFORGE_MIRROR}/mpg123/mpg123/${PV}/${BP}.tar.bz2"
SRC_URI[md5sum] = "13a9df5d4e7aa110e4ae27cf30128ce9"
SRC_URI[sha256sum] = "3ad197f77c9ffdf3601e1c3183ae0709ccb3c3de68309527ce9375fcfb15dcba"

inherit autotools pkgconfig

EXTRA_OECONF = " \
    --enable-shared \
    --with-audio='${AUDIOMODS}' \
    --with-module-suffix=.so \
    ${@bb.utils.contains('TUNE_FEATURES', 'neon', '--with-cpu=neon', '', d)} \
    ${@bb.utils.contains('TUNE_FEATURES', 'altivec', '--with-cpu=altivec', '', d)} \
"

