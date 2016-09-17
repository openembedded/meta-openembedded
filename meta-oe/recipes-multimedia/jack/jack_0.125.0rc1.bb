DESCRIPTION = "JACK is a low-latency audio server. It can \
connect a number of different applications to an audio \
device, as well as allowing them to share audio between \
themselves."
SECTION = "libs/multimedia"

LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=c030468238b2cf89925a57f2780396a7 \
                    file://COPYING.GPL;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LGPL;md5=e77fe93202736b47c07035910f47974a \
"

DEPENDS = "libsamplerate0 libsndfile1 readline db"

# Seems part of our code is shared with jack2
SRC_URI = " \
    git://github.com/jackaudio/jack1.git;name=jack \
    git://github.com/jackaudio/example-clients.git;destsuffix=git/example-clients;name=example-clients \
    git://github.com/jackaudio/tools.git;destsuffix=git/tools;name=tools \
    git://github.com/jackaudio/headers.git;destsuffix=git/jack;name=headers \
"
SRCREV_jack = "1ff912463e5553c515ea45a411d136e775e32613"
SRCREV_example-clients = "7fa089095c81e81dacd2554ae3184acc7f2d58ed"
SRCREV_tools = "02ddc6ea2f16e41326c675b1bcfcd6335390b88e"
SRCREV_headers = "07f1ecf1ce119d861ec631cb4a6a81a1ab1e5657"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGECONFIG ??= "alsa"
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[portaudio] = "--enable-portaudio,--disable-portaudio,portaudio"

EXTRA_OECONF = " \
    --enable-force-install \
    --enable-timestamps \
    --disable-capabilities \
    --disable-oldtrans \
    --disable-coreaudio \
    --disable-oss \
"

PACKAGES =+ "libjack jack-server jack-utils"

FILES_libjack = "${libdir}/*.so.* ${libdir}/jack/*.so"
FILES_jack-server = "${bindir}/jackd"
FILES_jack-utils = "${bindir}/*"
FILES_${PN}-doc += " ${datadir}/jack-audio-connection-kit/reference/html/* "
