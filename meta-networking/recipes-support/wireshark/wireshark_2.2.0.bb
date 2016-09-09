DESCRIPTION = "wireshark - a popular network protocol analyzer"
HOMEPAGE = "http://www.wireshark.org"
SECTION = "net"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.linux;md5=631e077455b7972172eb149195e065b0"

DEPENDS = "pcre expat glib-2.0 sbc"

SRC_URI = "https://2.na.dl.wireshark.org/src/all-versions/${BP}.tar.bz2"

SRC_URI[md5sum] = "c7de0997f74934f25b456846cf75cb81"
SRC_URI[sha256sum] = "a6847e741efcba6cb9d92d464d4219917bee3ad0b8f5b0f80d4388ad2f3f1104"

inherit autotools pkgconfig perlnative

ARM_INSTRUCTION_SET = "arm"


PACKAGECONFIG ?= "libpcap gnutls libnl libcap"
PACKAGECONFIG += " ${@bb.utils.contains("DISTRO_FEATURES", "x11", "gtk2 graphics", "", d)}"
#PACKAGECONFIG += " ${@bb.utils.contains("DISTRO_FEATURES", "opengl", "gtk3", "", d)}"

PACKAGECONFIG[libcap] = "--with-libcap=${STAGING_LIBDIR}, --with-libcap=no --enable-pcap-ng-default , libcap"
PACKAGECONFIG[libpcap] = "--with-pcap=${STAGING_LIBDIR} --with-pcap-remote, --with-pcap=no --enable-pcap-ng-default  , libpcap"
PACKAGECONFIG[libsmi] = "--with-libsmi=yes, --with-libsmi=no, libsmi"
PACKAGECONFIG[libnl] = "--with-libnl=yes, --with-libnl=no, libnl"
PACKAGECONFIG[portaudio] = "--with-portaudio=yes, --with-portaudio=no, portaudio-v19"
PACKAGECONFIG[gtk2] = "--with-gtk=2, , gtk+"
PACKAGECONFIG[gtk3] = "--with-gtk=3, , gtk+3"
PACKAGECONFIG[graphics] = "--enable-wireshark, --with-gtk=no --disable-wireshark,"
PACKAGECONFIG[gnutls] = "--with-gnutls=yes, --with-gnutls=no, gnutls"
PACKAGECONFIG[gcrypt] = "--with-gcrypt=yes, --with-gcrypt=no, libgcrypt"
PACKAGECONFIG[ssl] = "--with-ssl=yes, --with-ssl=no, openssl"
PACKAGECONFIG[krb5] = "--with-krb5=yes, --with-krb5=no, krb5"
PACKAGECONFIG[lua] = "--with-lua=yes, --with-lua=no, lua"
PACKAGECONFIG[zlib] = "--with-zlib=yes, --with-zlib=no, zlib"
PACKAGECONFIG[geoip] = "--with-geoip=yes, --with-geoip=no, geoip"
PACKAGECONFIG[plugins] = "--with-plugins=yes, --with-plugins=no"

# these next two options require addional layers
PACKAGECONFIG[c-ares] = "--with-c-ares=yes, --with-c-ares=no, c-ares"

EXTRA_OECONF += "--with-qt=no --enable-tshark --enable-rawshark"

ALLOW_EMPTY_${PN} = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

FILES_${PN} += "${datadir}*"
