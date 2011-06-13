SRC_URI += "file://config-lfs.patch \
            file://quota.patch;striplevel=0 \
            "

EXTRA_OECONF += "\
        --without-ads \
        --without-winbind \
        --without-ldap \
        --without-krb5"

PACKAGES =+ "libnetapi libtdb libsmbsharemodes libsmbclient libsmbclient-dev cifs cifs-doc swat"

FILES_libnetapi = "${libdir}/libnetapi.so.*"
FILES_libsmbsharemodes = "${libdir}/libsmbsharemodes.so.*"
FILES_libtdb = "${libdir}/libtdb.so.*"
FILES_cifs = "${base_sbindir}/mount.cifs ${base_sbindir}/umount.cifs"
FILES_cifs-doc = "${mandir}/man8/mount.cifs.8 ${mandir}/man8/umount.cifs.8"
FILES_libsmbclient = "${libdir}/libsmbclient.so.*"
FILES_libsmbclient-dev = "${libdir}/libsmbclient.so ${includedir}"
FILES_swat       = "${sbindir}/swat ${datadir}/swat ${libdir}/*.msg"
