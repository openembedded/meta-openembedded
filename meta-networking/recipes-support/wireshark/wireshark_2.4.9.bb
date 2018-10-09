DESCRIPTION = "wireshark - a popular network protocol analyzer"
HOMEPAGE = "http://www.wireshark.org"
SECTION = "net"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6e271234ba1a13c6e512e76b94ac2f77"

DEPENDS = "pcre expat glib-2.0 glib-2.0-native libgcrypt libgpg-error"

SRC_URI = "https://1.as.dl.wireshark.org/src/${BP}.tar.xz"
SRC_URI += "file://libgcrypt.patch"

UPSTREAM_CHECK_URI = "https://1.as.dl.wireshark.org/src"

SRC_URI[md5sum] = "55caea0f5f334c96977d54b0d1e52dae"
SRC_URI[sha256sum] = "63c4f24665269536f0e2ec321d394336fef6ba12466241a0e2433e936ac633e3"

PE = "1"

inherit autotools pkgconfig perlnative upstream-version-is-even

ARM_INSTRUCTION_SET = "arm"

PACKAGECONFIG ?= "libpcap gnutls libnl libcap sbc"
PACKAGECONFIG += " ${@bb.utils.contains("DISTRO_FEATURES", "x11", "gtk2 graphics", "", d)}"

PACKAGECONFIG[libcap] = "--with-libcap=${STAGING_DIR_HOST}/usr, --with-libcap=no --enable-pcap-ng-default , libcap"
PACKAGECONFIG[libpcap] = "--with-pcap=${STAGING_DIR_HOST}/usr --with-pcap-remote, --with-pcap=no --enable-pcap-ng-default  , libpcap"
PACKAGECONFIG[libsmi] = "--with-libsmi=yes, --with-libsmi=no, libsmi"
PACKAGECONFIG[libnl] = "--with-libnl=yes, --with-libnl=no, libnl"
PACKAGECONFIG[portaudio] = "--with-portaudio=yes, --with-portaudio=no, portaudio-v19"
PACKAGECONFIG[gtk2] = "--with-gtk=2, , gtk+"
PACKAGECONFIG[gtk3] = "--with-gtk=3, , gtk+3"
PACKAGECONFIG[graphics] = "--enable-wireshark, --with-gtk=no --disable-wireshark,"
PACKAGECONFIG[gnutls] = "--with-gnutls=yes, --with-gnutls=no, gnutls"
PACKAGECONFIG[ssl] = "--with-ssl=yes, --with-ssl=no, openssl"
PACKAGECONFIG[krb5] = "--with-krb5=yes, --with-krb5=no, krb5"
PACKAGECONFIG[lua] = "--with-lua=yes, --with-lua=no, lua"
PACKAGECONFIG[zlib] = "--with-zlib=yes, --with-zlib=no, zlib"
PACKAGECONFIG[geoip] = "--with-geoip=yes, --with-geoip=no, geoip"
PACKAGECONFIG[plugins] = "--with-plugins=yes, --with-plugins=no"
PACKAGECONFIG[sbc] = "--with-sbc=yes, --with-sbc=no, sbc"
PACKAGECONFIG[libssh] = "--with-libssh=${STAGING_DIR_HOST}/usr, --with-libssh=no, libssh2"
PACKAGECONFIG[lz4] = "--with-lz4=${STAGING_DIR_HOST}/usr, --with-lz4=no, lz4"

# these next two options require addional layers
PACKAGECONFIG[c-ares] = "--with-c-ares=yes, --with-c-ares=no, c-ares"

EXTRA_OECONF += "--with-libgcrypt-prefix=${PKG_CONFIG_DIR} --with-qt=no --enable-tshark --enable-rawshark"

LDFLAGS_append = " -lgpg-error"

# Currently wireshark does not install header files
do_install_append () {

	install -d ${D}/${includedir}/${BPN}
	install -d ${D}/${includedir}/${BPN}/epan
	install -d ${D}/${includedir}/${BPN}/epan/crypt
	install -d ${D}/${includedir}/${BPN}/epan/dfilter
	install -d ${D}/${includedir}/${BPN}/epan/dissectors
	install -d ${D}/${includedir}/${BPN}/epan/ftypes
	install -d ${D}/${includedir}/${BPN}/epan/wmem

	install config.h ${D}/${includedir}/${BPN}
	install ${S}/register.h ${D}/${includedir}/${BPN}
	install -D ${S}/epan/*.h ${D}/${includedir}/${BPN}/epan
	install -D ${S}/epan/crypt/*.h ${D}/${includedir}/${BPN}/epan/crypt
	install -D ${S}/epan/dfilter/*.h ${D}/${includedir}/${BPN}/epan/dfilter
	install -D ${S}/epan/dissectors/*.h ${D}/${includedir}/${BPN}/epan/dissectors
	install -D ${S}/epan/ftypes/*.h ${D}/${includedir}/${BPN}/epan/ftypes
	install -D ${S}/epan/wmem/*.h ${D}/${includedir}/${BPN}/epan/wmem
}

FILES_${PN} += "${datadir}*"
