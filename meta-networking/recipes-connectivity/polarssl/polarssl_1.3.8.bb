SUMMARY = "Lightweight crypto and SSL/TLS library"
DESCRIPTION = "PolarSSL is a lean open source crypto library         \
for providing SSL and TLS support in your programs. It offers        \
an intuitive API and documented header files, so you can actually    \
understand what the code does. It features:                          \
                                                                     \
 - Symmetric algorithms, like AES, Blowfish, Triple-DES, DES, ARC4,  \
   Camellia and XTEA                                                 \
 - Hash algorithms, like SHA-1, SHA-2, RIPEMD-160 and MD5            \
 - Entropy pool and random generators, like CTR-DRBG and HMAC-DRBG   \
 - Public key algorithms, like RSA, Elliptic Curves, Diffie-Hellman, \
   ECDSA and ECDH                                                    \
 - SSL v3 and TLS 1.0, 1.1 and 1.2                                   \
 - Abstraction layers for ciphers, hashes, public key operations,    \
   platform abstraction and threading                                \
"

HOMEPAGE = "https://polarssl.org"
BUGTRACKER = "https://github.com/polarssl/polarssl/issues"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

SECTION = "net"

SRC_URI = "https://polarssl.org/download/polarssl-${PV}-gpl.tgz"

SRC_URI[md5sum] = "d1a2b4f21727e888f143414d2e3144e6"
SRC_URI[sha256sum] = "318171db41335cacbb5b0047c94f1faf91442ab70a223b5223436703c9406ff1"

DEPENDS = "openssl"
RDEPENDS_${PN} += "libcrypto"
EXTRA_OECMAKE = "-DUSE_SHARED_POLARSSL_LIBRARY=on"

inherit cmake
