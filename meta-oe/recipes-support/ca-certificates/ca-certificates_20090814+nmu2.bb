DESCRIPTION = "Common CA certificates"
HOMEPAGE = "http://packages.debian.org/sid/ca-certificates"
SECTION = "misc"
PRIORITY = "optional"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=6275b491c91b57d92ebe11205ebf4dfe"

SRC_URI = "${DEBIAN_MIRROR}/main/c/ca-certificates/ca-certificates_${PV}.tar.gz \
           file://remove-c-rehash.patch"
SRC_URI[md5sum] = "76a2b0381b0aa7a6892e2340cd2c159a"
SRC_URI[sha256sum] = "b1b144a3732df638e25b84ec6414ca9d1da4898cfd06d86b09f671585ce9c747"
inherit autotools

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

inherit allarch
PACKAGES = "${PN}"

pkg_postinst_${PN} () {
        /usr/sbin/update-ca-certificates
}

CONFFILES_${PN} = "/etc/ca-certificates.conf"
