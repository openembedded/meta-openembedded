DESCRIPTION = "The Audio File Library provides a uniform and elegant \
API for accessing a variety of audio file formats, such as AIFF/AIFF-C, \
WAVE, NeXT/Sun .snd/.au, Berkeley/IRCAM/CARL Sound File, Audio Visual \
Research, Amiga IFF/8SVX, and NIST SPHERE."
SECTION = "libs"
LICENSE = "LGPLv2 && GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://COPYING.GPL;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "http://www.68k.org/~michael/audiofile/audiofile-${PV}.tar.gz \
	   file://audiofile-m4_quote_fix.diff;striplevel=0 \
	   file://audiofile-oldstyle.patch;striplevel=0 \
	   file://audiofile-0.2.6.patch;striplevel=0 \
	   file://CVE-2008-5824.patch \
"

inherit autotools lib_package binconfig

SRC_URI[md5sum] = "9c1049876cd51c0f1b12c2886cce4d42"
SRC_URI[sha256sum] = "4b6167b56e21556fb07c9ef06962fe32817064c62181ba47afd3322e0d0f22a9"
