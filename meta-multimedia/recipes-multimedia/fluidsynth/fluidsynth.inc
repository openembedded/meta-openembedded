SUMMARY = "Fluidsynth is a software synthesizer"
HOMEPAGE = "http://www.fluidsynth.org/"
SECTION = "libs/multimedia"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

SRC_URI = "git://github.com/FluidSynth/fluidsynth.git;branch=master;protocol=https"
SRCREV = "926581851ed1a095ef5b8659f77b38272d57e624"
S = "${WORKDIR}/git"
PV = "2.2.3"

inherit cmake pkgconfig lib_package
