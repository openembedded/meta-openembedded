#
# Copyright (C) 2013 Wind River Systems, Inc.
#

SUMMARY = "The CELT codec is a compression algorithm for audio"
DESCRIPTION = "The CELT codec is a compression algorithm for \
audio. Like MP3, Vorbis, and AAC it is suitable for transmitting music \
with high quality. Unlike these formats CELT imposes very little delay \
on the signal, even less than is typical for speech centric formats \
like Speex, GSM, or G.729."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=375f60ab360d17f0172737036ff155b2"

PV = "0.5.1.3"
PR = "r0"

SRCREV = "5555aae843f57241d005e330b9cb65602d56db0f"

SRC_URI = "git://git.xiph.org/celt.git;branch=compat-v0.5.1;protocol=https"

S = "${WORKDIR}/git"

DEPENDS += "libogg"

inherit pkgconfig autotools-brokensep

PACKAGECONFIG = ""

PACKAGECONFIG[ogg] = "--with-ogg --enable-oggtest,--without-ogg --disable-oggtest,,"
