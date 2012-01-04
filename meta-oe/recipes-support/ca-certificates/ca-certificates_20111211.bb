DESCRIPTION = "Common CA certificates"
HOMEPAGE = "http://packages.debian.org/sid/ca-certificates"
SECTION = "misc"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=6275b491c91b57d92ebe11205ebf4dfe"

SRC_URI = "${DEBIAN_MIRROR}/main/c/ca-certificates/ca-certificates_${PV}.tar.gz \
           file://remove-c-rehash.patch"

SRC_URI[md5sum] = "f99a90a91b23338b4df765c0d18eba73"
SRC_URI[sha256sum] = "6f0633136d17ffef3ed0adfb171a00136a320f0fd79c9c75733ac02662a754a7"

inherit autotools allarch

do_install_prepend() {
        mkdir -p ${D}/usr/share/ca-certificates
        mkdir -p ${D}/usr/sbin
        mkdir -p ${D}/etc/ssl/certs
        mkdir -p ${D}/etc/ca-certificates/update.d
}

do_install_append() {
        cd ${D}/usr/share/ca-certificates
        echo "# Lines starting with # will be ignored" > ${D}/etc/ca-certificates.conf
        echo "# Lines starting with ! will remove certificate on next update" >> ${D}/etc/ca-certificates.conf
        echo "#" >> ${D}/etc/ca-certificates.conf
        for crt in $(find . -type f -name '*.crt' -print)
        do
                crt=$(echo $crt | sed -e 's/\.\///')
                echo $crt >> ${D}/etc/ca-certificates.conf
        done
}

pkg_postinst_${PN} () {
if [ -n "$D" ] ; then
	exit 1
fi

${sbindir}/update-ca-certificates
}

CONFFILES_${PN} = "/etc/ca-certificates.conf"
