DESCRIPTION = "IPsec-Tools is a port of KAME's IPsec utilities to the \
Linux-2.6 IPsec implementation."
HOMEPAGE = "http://ipsec-tools.sourceforge.net/"
SECTION = "console/network"
LICENSE = "BSD"
DEPENDS = "virtual/kernel openssl readline flex"

SRC_URI = "${SOURCEFORGE_MIRROR}/ipsec-tools/ipsec-tools-${PV}.tar.bz2;name=ipsec-tools-${PV}"

inherit autotools

# Options:
#  --enable-adminport      enable admin port
#  --enable-rc5            enable RC5 encryption (patented)
#  --enable-idea enable IDEA encryption (patented)
#  --enable-gssapi         enable GSS-API authentication
#  --enable-hybrid         enable hybrid, both mode-cfg and xauth support
#  --enable-frag           enable IKE fragmentation payload support
#  --enable-stats          enable statistics logging function
#  --enable-dpd            enable dead peer detection
#  --enable-samode-unspec  enable to use unspecified a mode of SA
#  --disable-ipv6          disable ipv6 support
#  --enable-natt           enable NAT-Traversal (yes/no/kernel)
#  --enable-natt-versions=list    list of supported NAT-T versions delimited by coma.
#  --with-kernel-headers=/lib/modules/<uname>/build/include
#                          where your Linux Kernel headers are installed
#  --with-readline         support readline input (yes by default)
#  --with-flex             use directiory (default: no)
#  --with-flexlib=<LIB>    specify flex library.
#  --with-openssl=DIR      specify OpenSSL directory
#  --with-libradius=DIR    specify libradius path (like/usr/pkg)
#  --with-libpam=DIR       specify libpam path (like/usr/pkg)
#
# Note: if you give it the actual kernel headers it won't build, it actually
# needs to point at the linux-libc-headers version of the kernel headers.
#
EXTRA_OECONF = "--with-kernel-headers=${STAGING_INCDIR} \
                --with-readline \
                --with-openssl=${STAGING_LIBDIR}/.. \
                --without-libradius \
                --without-gssapi \
                --without-libpam \
		--disable-security-context"

# See http://bugs.debian.org/cgi-bin/bugreport.cgi?bug=530527
CFLAGS += "-fno-strict-aliasing"

# Sometimes it attempts to use y.tab.c before it is actually
# generated. Note that the failure case is very rare, but it does
# happen.
PARALLEL_MAKE = ""
