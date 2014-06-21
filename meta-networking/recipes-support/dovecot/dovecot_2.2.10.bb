SUMMARY = "Dovecot is an open source IMAP and POP3 email server"
DESCRIPTION = "Dovecot is an open source IMAP and POP3 email server for Linux/UNIX-like systems, written with security primarily in mind. Dovecot is an excellent choice for both small and large installations. It's fast, simple to set up, requires no special administration and it uses very little memory."

LICENSE = "LGPLv2.1 & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=a981379bd0f1c362f8d1d21515e5b30b"

SRC_URI = "http://dovecot.org/releases/2.2/dovecot-${PV}.tar.gz \
           file://0001-configure.ac-convert-AC_TRY_RUN-to-AC_TRY_LINK-state.patch \
           file://building-rquota_xdr.c-depend-on-rquota.h.patch \
          "
SRC_URI[md5sum] = "037e9c9e07d9dbff54dcff09f280fc8c"
SRC_URI[sha256sum] = "75592483d40dc4f76cc3b41af40caa4be80478946a699d46846d5d03e4d2e09b"

DEPENDS = "openssl xz zlib bzip2 libcap"

inherit autotools pkgconfig

PACKAGECONFIG ??= " \
                   ${@base_contains('DISTRO_FEATURES', 'ldap', 'ldap', '', d)} \
                   ${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                  "

PACKAGECONFIG[pam] = "--with-pam,--without-pam,libpam,"
PACKAGECONFIG[ldap] = "--with-ldap=plugin,--without-ldap,openldap,"

# From native build in armv7a-hf/eglibc
CACHED_CONFIGUREVARS += "i_cv_signed_size_t=no \
                         i_cv_gmtime_max_time_t=32 \
                         i_cv_signed_time_t=yes \
                         i_cv_mmap_plays_with_write=yes \
                         i_cv_fd_passing=yes \
                         i_cv_c99_vsnprintf=yes \
                         lib_cv___va_copy=yes \
                         lib_cv_va_copy=yes \
                         lib_cv_va_val_copy=yes \
                        "

# hardcode epoll() to avoid running unsafe tests
# BSD needs kqueue and uclibc poll()
EXTRA_OECONF = " --with-ioloop=epoll"

FILES_${PN}-staticdev += "${libdir}/dovecot/*/*.a"
FILES_${PN}-dev += "${libdir}/dovecot/*.so"
FILES_${PN}-dbg += "${libdir}/dovecot/*/.debug"

