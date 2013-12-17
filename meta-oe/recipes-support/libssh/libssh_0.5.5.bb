DESCRIPTION = "Multiplatform C library implementing the SSHv2 and SSHv1 protocol"
HOMEPAGE = "http://www.libssh.org"
SECTION = "libs"

DEPENDS = "zlib openssl libgcrypt"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=388a4fb1dea8ceae0be78ba9b01fc139"

SRC_URI = "git://git.libssh.org/projects/libssh.git"
SRCREV = "43914a5f07702fe292a968322d5ff2627e0431db"
S = "${WORKDIR}/git"

OECMAKE_BUILDPATH = "${S}/build"
OECMAKE_SOURCEPATH = "${S}"
EXTRA_OECMAKE = " \
    -DWITH_GCRYPT=1 \
    -DWITH_PCAP=1 \
    -DWITH_SFTP=1 \
    -DWITH_ZLIB=1 \
    "
EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"

inherit cmake

do_configure_prepend () {
    # Disable building of examples
    sed -i -e '/add_subdirectory(examples)/s/^/#DONOTWANT/' CMakeLists.txt \
        || bb_fatal "Failed to disable examples"
}
