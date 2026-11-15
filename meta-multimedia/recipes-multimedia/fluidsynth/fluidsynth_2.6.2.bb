SUMMARY = "Fluidsynth is a software synthesizer"
HOMEPAGE = "http://www.fluidsynth.org/"
SECTION = "libs/multimedia"
LICENSE = "LGPL-2.1-only AND Apache-2.0 AND MIT AND 0BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4bf661c1e3793e55c8d1051bc5e0ae21 \
                    file://gcem/LICENSE;md5=175792518e4ac015ab6696d16c4f607e \
                    file://signalsmith-audio-basics/LICENSE.txt;md5=24bc379a8a5433981804371861282220 \
                    file://signalsmith-deps/linear/LICENSE.txt;md5=4c639037b59931f4da681810a23d6afd \
                    file://signalsmith-deps/dsp/LICENSE.txt;md5=10f34296480e9e3fa42e9dd2acfff6a5 \
                    file://signalsmith-deps/hilbert/LICENSE.txt;md5=5ded873b823122eb4fd596677999bbfd"

DEPENDS = "glib-2.0"

SRC_URI = " \
    git://github.com/FluidSynth/fluidsynth.git;name=fluidsynth;nobranch=1;protocol=https;tag=v${PV} \
    git://github.com/kthohr/gcem.git;name=gcem;subdir=${S}/gcem;protocol=https;nobranch=1 \
    git://github.com/Signalsmith-Audio/basics.git;name=signalsmith-audio-basics;subdir=${S}/signalsmith-audio-basics;protocol=https;nobranch=1 \
    git://github.com/Signalsmith-Audio/linear.git;name=signalsmith-linear;subdir=${S}/signalsmith-deps/linear;protocol=https;nobranch=1 \
    git://github.com/Signalsmith-Audio/dsp.git;name=signalsmith-dsp;subdir=${S}/signalsmith-deps/dsp;protocol=https;nobranch=1 \
    git://github.com/Signalsmith-Audio/hilbert-iir.git;name=signalsmith-hilbert;subdir=${S}/signalsmith-deps/hilbert;protocol=https;nobranch=1 \
    file://0003-Use-ARM-NEON-accelaration-for-float-multithreaded-se.patch \
"
SRCREV_FORMAT = "fluidsynth"
SRCREV_fluidsynth = "c3b244ddfd094489a5490beee013c621a504adf2"
SRCREV_gcem = "012ae73c6d0a2cb09ffe86475f5c6fba3926e200"
SRCREV_signalsmith-audio-basics = "012d2be17b0eb6839628f8c73687c4ccccc1bb01"
SRCREV_signalsmith-linear = "5668673560146a9cfe38c25315071e3fd68c8317"
SRCREV_signalsmith-dsp = "2d20161915e733f117545c6be8cd3275a739a1e3"
SRCREV_signalsmith-hilbert = "9e42e09714c8215d51eaa1b52a3d54ae8dd2a3a3"

inherit cmake pkgconfig lib_package

EXTRA_OECMAKE = "-Denable-floats=ON -DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')} -DGCEM_INCLUDE_DIR=${S}/gcem/include \
                 -DFETCHCONTENT_SOURCE_DIR_SIGNALSMITH-LINEAR=${S}/signalsmith-deps/linear \
                 -DFETCHCONTENT_SOURCE_DIR_SIGNALSMITH-DSP=${S}/signalsmith-deps/dsp \
                 -DFETCHCONTENT_SOURCE_DIR_SIGNALSMITH-HILBERT=${S}/signalsmith-deps/hilbert"

do_install:append() {
    sed -i -e 's|${STAGING_LIBDIR}|${libdir}|g' ${D}${libdir}/pkgconfig/fluidsynth.pc
    sed -i -e 's|${STAGING_LIBDIR}|${libdir}|g' ${D}${libdir}/cmake/fluidsynth/FluidSynth-shared-targets.cmake
}

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio pipewire systemd alsa dbus', d)}"
PACKAGECONFIG[alsa] = "-Denable-alsa=ON,-Denable-alsa=OFF,alsa-lib"
PACKAGECONFIG[dbus] = "-Denable-dbus=ON,-Denable-dbus=OFF,dbus"
PACKAGECONFIG[jack] = "-Denable-jack=ON,-Denable-jack=OFF,jack"
PACKAGECONFIG[oss] = "-Denable-oss=ON,-Denable-oss=OFF"
PACKAGECONFIG[pipewire] = "-Denable-pipewire=ON,-Denable-pipewire=OFF,pipewire"
PACKAGECONFIG[portaudio] = "-Denable-portaudio=ON,-Denable-portaudio=OFF,portaudio-v19"
PACKAGECONFIG[profiling] = "-Denable-profiling=ON,-Denable-profiling=OFF"
PACKAGECONFIG[pulseaudio] = "-Denable-pulseaudio=ON,-Denable-pulseaudio=OFF,pulseaudio"
PACKAGECONFIG[readline] = "-Denable-readline=ON,-Denable-readline=OFF,readline"
PACKAGECONFIG[sdl] = "-Denable-sdl3=ON,-Denable-sdl3=OFF,libsdl3"
PACKAGECONFIG[sndfile] = "-Denable-libsndfile=ON,-Denable-libsndfile=OFF,libsndfile1"
PACKAGECONFIG[systemd] = "-Denable-systemd=ON,-Denable-systemd=OFF,systemd"

