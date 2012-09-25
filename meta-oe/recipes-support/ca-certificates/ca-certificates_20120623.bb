DESCRIPTION = "Common CA certificates"
HOMEPAGE = "http://packages.debian.org/sid/ca-certificates"
SECTION = "misc"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=6135800ff6d893c7904d7aad90972eb5"

SRC_URI = "${DEBIAN_MIRROR}/main/c/ca-certificates/ca-certificates_${PV}.tar.gz \
           file://0001-update-ca-certificates-remove-c-rehash.patch"

SRC_URI[md5sum] = "5105d4cc086f0d4ecf7bf2e4c4667289"
SRC_URI[sha256sum] = "878cd1130ba056fe5f96decde7e5fc1b71d35eb8565a1515744912e100731ee9"

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
