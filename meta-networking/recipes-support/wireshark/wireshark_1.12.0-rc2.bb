DESCRIPTION = "wireshark - a popular network protocol analyzer"
HOMEPAGE = "http://www.wireshark.org"
SECTION = "network"
LICENSE = "GPL-2.0"

DEPENDS = "perl-native libcap libpcap pcre expat glib-2.0 libsmi libnl portaudio-v19 sbc"

inherit autotools pkgconfig

ARM_INSTRUCTION_SET = "arm"

# Works with either gtk+ or gtk3.
WHICH_GTK = "gtk3"

PACKAGECONFIG ??= "gnutls gcrypt"
PACKAGECONFIG += " ${@bb.utils.contains("DISTRO_FEATURES", "x11", "${WHICH_GTK}  graphics", "", d)}"
PACKAGECONFIG += " ${@bb.utils.contains("DISTRO_FEATURES", "ipv6", "ipv6", "", d)}"

PACKAGECONFIG[gtk2] = "--with-gtk2=yes, --with-gtk2=no, gtk+"
PACKAGECONFIG[gtk3] = "--with-gtk3=yes, --with-gtk3=no, gtk+3"
PACKAGECONFIG[graphics] = "--enable-wireshark, --disable-wireshark,"
PACKAGECONFIG[ipv6] = "--enable-ipv6, --disable-ipv6,"
PACKAGECONFIG[gnutls] = "--with-gnutls=yes, --with-gnutls=no, gnutls"
PACKAGECONFIG[gcrypt] = "--with-gcrypt=yes, --with-gcrypt=no, libgcrypt"

EXTRA_OECONF = "--with-qt=no --enable-usr-local=no -enable-tshark"

LIC_FILES_CHKSUM = "file://README.linux;md5=631e077455b7972172eb149195e065b0"
SRC_URI = "http://wiresharkdownloads.riverbed.com/wireshark/src/wireshark-1.12.0-rc2.tar.bz2 \
    file://va_list-can-t-be-NULL-on-ARM.patch \
"
    
SRC_URI[md5sum] = "dc1149073066a29f91116c168558262e"
SRC_URI[sha256sum]= "31009bb450126e9b12808267419f31016d14e6fde7b5e39c85ad37459908cffb"

do_configure_prepend() {
    # force to use fallback 
    sed -i -e '/^glib_prefix/s/=.*$/=""/' ${S}/aclocal-flags
}

ALLOW_EMPTY_${PN} = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

