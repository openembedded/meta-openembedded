DESCRIPTION = "wireshark - a popular network protocol analyzer"
HOMEPAGE = "http://www.wireshark.org"
SECTION = "net"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.linux;md5=631e077455b7972172eb149195e065b0"

DEPENDS = "perl-native pcre expat glib-2.0 sbc"

SRC_URI = "https://2.na.dl.wireshark.org/src/all-versions/${BP}.tar.bz2"

PE = "1"

SRC_URI[md5sum] = "988a4595a4a87dafb3c4870ea40e89b5"
SRC_URI[sha256sum] = "357e0a4e49525d80cdc740bb16539fcdb526ad38cc2ed6cabedafc9bdee5c7e7"

inherit autotools pkgconfig

ARM_INSTRUCTION_SET = "arm"

# Works with either gtk+ or gtk3.
WHICH_GTK = "gtk3"

PACKAGECONFIG ?= "libpcap gnutls libnl libcap"
PACKAGECONFIG += " ${@bb.utils.contains("DISTRO_FEATURES", "x11", "${WHICH_GTK}  graphics", "", d)}"
PACKAGECONFIG += " ${@bb.utils.contains("DISTRO_FEATURES", "ipv6", "ipv6", "", d)}"

PACKAGECONFIG[libcap] = "--with-libcap=${STAGING_LIBDIR}, --with-libcap=no --enable-pcap-ng-default , libcap"
PACKAGECONFIG[libpcap] = "--with-pcap=${STAGING_LIBDIR} --with-pcap-remote, --with-pcap=no --enable-pcap-ng-default  , libpcap"
PACKAGECONFIG[libsmi] = "--with-libsmi=yes, --with-libsmi=no, libsmi"
PACKAGECONFIG[libnl] = "--with-libnl=yes, --with-libnl=no, libnl"
PACKAGECONFIG[portaudio] = "--with-portaudio=yes, --with-portaudio=no, portaudio-v19"
PACKAGECONFIG[gtk2] = "--with-gtk2=yes, --with-gtk2=no, gtk+"
PACKAGECONFIG[gtk3] = "--with-gtk3=yes, --with-gtk3=no, gtk+3"
PACKAGECONFIG[graphics] = "--enable-wireshark, --disable-wireshark,"
PACKAGECONFIG[ipv6] = "--enable-ipv6, --disable-ipv6,"
PACKAGECONFIG[gnutls] = "--with-gnutls=yes, --with-gnutls=no, gnutls"
PACKAGECONFIG[gcrypt] = "--with-gcrypt=yes, --with-gcrypt=no, libgcrypt"
PACKAGECONFIG[ssl] = "--with-ssl=yes, --with-ssl=no, openssl"
PACKAGECONFIG[krb5] = "--with-krb5=yes, --with-krb5=no, krb5"
PACKAGECONFIG[lua] = "--with-lua=yes, --with-lua=no, lua"
PACKAGECONFIG[zlib] = "--with-zlib=yes, --with-zlib=no, zlib"
PACKAGECONFIG[geoip] = "--with-geoip=yes, --with-geoip=no, geoip"
PACKAGECONFIG[plugins] = "--with-plugins=yes, --with-plugins=no"

# these next two options require addional layers
PACKAGECONFIG[adns] = "--with-adns=yes, --with-adns=no, adns"
PACKAGECONFIG[c-ares] = "--with-c-ares=yes, --with-c-ares=no, c-ares"

EXTRA_OECONF += "--with-qt=no --enable-usr-local=no --enable-tshark --enable-rawshark"

do_configure_prepend() {
    # force to use fallback 
    sed -i -e '/^glib_prefix/s/=.*$/=""/' ${S}/aclocal-flags
}

ALLOW_EMPTY_${PN} = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
