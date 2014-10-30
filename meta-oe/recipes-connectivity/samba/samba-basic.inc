SRC_URI += "file://config-lfs.patch \
            file://quota.patch;striplevel=0 \
"

EXTRA_OECONF += "\
        --enable-nss-wrapper \
        --without-ads \
        --with-winbind \
        --without-ldap \
        --without-krb5"

PACKAGES =+ "libwbclient libwinbind libwinbind-dbg libnss-winbind winbind winbind-dbg libnetapi libtdb libsmbsharemodes libsmbclient libsmbclient-dev cifs cifs-doc swat"

FILES_winbind-dbg = "${libdir}/idmap/.debug/*.so \
                     ${libdir}/security/.debug/pam_winbind.so \
"

FILES_${PN} += "${libdir}/vfs/*.so \
                ${libdir}/charset/*.so \
                ${libdir}/*.dat \
                ${libdir}/auth/*.so \
                ${libdir}/security/pam_smbpass.so \
"

FILES_${PN}-dbg += "${libdir}/vfs/.debug/*.so \
                    ${libdir}/charset/.debug/*.so \
                    ${libdir}/auth/.debug/*.so \
                    ${libdir}/security/.debug/pam_smbpass.so \
"

FILES_libwbclient = "${libdir}/libwbclient.so.*"
FILES_libnetapi = "${libdir}/libnetapi.so.*"
FILES_libsmbsharemodes = "${libdir}/libsmbsharemodes.so.*"
FILES_libtdb = "${libdir}/libtdb.so.*"
FILES_cifs = "${base_sbindir}/mount.cifs ${base_sbindir}/umount.cifs"
FILES_cifs-doc = "${mandir}/man8/mount.cifs.8 ${mandir}/man8/umount.cifs.8"
FILES_libsmbclient = "${libdir}/libsmbclient.so.*"
FILES_libsmbclient-dev = "${libdir}/libsmbclient.so ${includedir}"
FILES_winbind = "${sbindir}/winbindd \
                 ${bindir}/wbinfo \
                 ${bindir}/ntlm_auth \
                 ${sysconfdir}/init.d/winbind \
                 ${systemd_unitdir}/system/winbind.service \
"
FILES_libwinbind = "${libdir}/idmap/*.so \
                    ${libdir}/pdb \
                    ${libdir}/gpext \
                    ${libdir}/perfcount \
                    ${libdir}/security/pam_winbind.so \
"

FILES_libnss-winbind = "${libdir}/libnss_*${SOLIBS} \
                        ${libdir}/nss_info"

FILES_swat       = "${sbindir}/swat ${datadir}/swat ${libdir}/*.msg"
