SUMMARY = "Multiplatform C library implementing the SSHv2 and SSHv1 protocol"
HOMEPAGE = "http://www.libssh.org"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=388a4fb1dea8ceae0be78ba9b01fc139"

DEPENDS = "zlib openssl libgcrypt"

SRC_URI = "git://git.libssh.org/projects/libssh.git;branch=v0-7"
SRCREV = "cdf7690e038230623cbbf6b024ece62f41efa98d"

S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ??=""
PACKAGECONFIG[gssapi] = "-DWITH_GSSAPI=1, -DWITH_GSSAPI=0, krb5, "

EXTRA_OECMAKE = " \
    -DWITH_GCRYPT=1 \
    -DWITH_PCAP=1 \
    -DWITH_SFTP=1 \
    -DWITH_ZLIB=1 \
    -DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')} \
    "

do_configure_prepend () {
    # Disable building of examples
    sed -i -e '/add_subdirectory(examples)/s/^/#DONOTWANT/' ${S}/CMakeLists.txt \
        || bbfatal "Failed to disable examples"
}

TOOLCHAIN = "gcc"
