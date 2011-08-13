DESCRIPTION = "Install required headers to enable OCF Linux support"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://README;md5=b02a793c9b7893b4ce1f39f0b231ccd7"
 
INC_PR = "r3"

SRC_URI = "http://sourceforge.net/projects/ocf-linux/files/ocf-linux/${PV}/ocf-linux-${PV}.tar.gz"

S = "${WORKDIR}/ocf-linux-${PV}"

# Need to unpack the the ocf-linux.tar.gz file contained inside the
# downloaded tarball
# Install the OCF Linux headers so that other packages such as openssl
# can find them.  The headers must be in a crypto directory according to
# the README file.
do_install() {
    cd ${S}
    tar xzf ocf-linux.tar.gz
    install -d ${D}${includedir}/crypto
    install -m 0644 ${S}/ocf/*.h ${D}${includedir}/crypto/
}

ALLOW_EMPTY = "1"
BBCLASSEXTEND = "native nativesdk"
