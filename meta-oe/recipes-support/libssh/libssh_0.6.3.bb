SUMMARY = "Multiplatform C library implementing the SSHv2 and SSHv1 protocol"
HOMEPAGE = "http://www.libssh.org"
SECTION = "libs"

DEPENDS = "zlib openssl libgcrypt"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=388a4fb1dea8ceae0be78ba9b01fc139"

SRC_URI = "git://git.libssh.org/projects/libssh.git;branch=v0-6"
SRCREV = "87145387aa12b720b52d6bc75b4dd6cd058c868a"
S = "${WORKDIR}/git"

EXTRA_OECMAKE = " \
    -DWITH_GCRYPT=1 \
    -DWITH_PCAP=1 \
    -DWITH_SFTP=1 \
    -DWITH_ZLIB=1 \
    "

PACKAGECONFIG ??=""
PACKAGECONFIG[gssapi] = "-DWITH_GSSAPI=1, -DWITH_GSSAPI=0, krb5, "

inherit cmake

do_configure_prepend () {
    # Disable building of examples
    sed -i -e '/add_subdirectory(examples)/s/^/#DONOTWANT/' ${S}/CMakeLists.txt \
        || bbfatal "Failed to disable examples"
}

FILES_${PN}-dev += "${libdir}/cmake"
