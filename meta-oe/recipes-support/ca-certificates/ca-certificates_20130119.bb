DEPENDS = "ca-certificates-cross"

require ca-certificates-${PV}.inc

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

pkg_postinst_${PN} () {
SYSROOT="$D" update-ca-certificates
}

CONFFILES_${PN} = "/etc/ca-certificates.conf"
