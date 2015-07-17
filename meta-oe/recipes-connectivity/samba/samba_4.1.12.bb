SECTION = "console/network"

LICENSE = "GPL-3.0+ & LGPL-3.0+ & GPL-2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://${COREBASE}/meta/files/common-licenses/LGPL-3.0;md5=bfccfe952269fff2b407dd11f2f3083b \
                    file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 "

SAMBA_MIRROR = "http://samba.org/samba/ftp"
MIRRORS += "\
${SAMBA_MIRROR}    http://mirror.internode.on.net/pub/samba \n \
${SAMBA_MIRROR}    http://www.mirrorservice.org/sites/ftp.samba.org \n \
"

SRC_URI = "${SAMBA_MIRROR}/stable/samba-${PV}.tar.gz \
           file://00-fix-typos-in-man-pages.patch \
           file://01-fix-force-user-sec-ads.patch  \
           file://02-fix-ipv6-join.patch  \
           file://03-net-ads-kerberos-pac.patch  \
           file://04-ipv6-workaround.patch  \
           file://05-fix-gecos-field-with-samlogon.patch  \
           file://06-fix-nmbd-systemd-status-update.patch  \
           file://07-fix-idmap-ad-getgroups-without-gid.patch  \
           file://08-fix-idmap-ad-sfu-with-trusted-domains.patch  \
           file://09-fix-smbclient-echo-cmd-segfault.patch  \
           file://10-improve-service-principal-guessing-in-net.patch  \
           file://11-fix-overwriting-of-spns-during-net-ads-join.patch  \
           file://12-add-precreated-spns-from-AD-during-keytab-generation.patch  \
           file://13-fix-aes-enctype.patch  \
           file://14-fix-dnsupdate.patch  \
           file://15-fix-netbios-name-truncation.patch  \
           file://16-do-not-check-xsltproc-manpages.patch \
           file://17-execute-prog-by-qemu.patch \
           file://18-avoid-get-config-by-native-ncurses.patch \
           file://19-systemd-daemon-is-contained-by-libsystemd.patch \
           file://20-do-not-import-target-module-while-cross-compile.patch \
          "

SRC_URI[md5sum] = "232016d7581a1ba11e991ec2674553c4"
SRC_URI[sha256sum] = "033604674936bf5c77d7df299b0626052b84a41505a6a6afe902f6274fc29898"

inherit systemd waf-samba

DEPENDS += "readline virtual/libiconv zlib popt talloc libtdb libtevent libldb krb5 ctdb"
RDEPENDS_${PN} += "openldap"

PACKAGECONFIG = "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)}"
PACKAGECONFIG += "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

PACKAGECONFIG[pam] = "--with-pam,--without-pam,libpam"
PACKAGECONFIG[fam] = "--with-fam,--without-fam,gamin"
PACKAGECONFIG[systemd] = "--with-systemd,--without-systemd,systemd"

SAMBA4_IDMAP_MODULES="idmap_ad,idmap_rid,idmap_adex,idmap_hash,idmap_tdb2"
SAMBA4_PDB_MODULES="pdb_tdbsam,pdb_ldap,pdb_ads,pdb_smbpasswd,pdb_wbc_sam,pdb_samba4"
SAMBA4_AUTH_MODULES="auth_unix,auth_wbc,auth_server,auth_netlogond,auth_script,auth_samba4"
SAMBA4_MODULES="${SAMBA4_IDMAP_MODULES},${SAMBA4_PDB_MODULES},${SAMBA4_AUTH_MODULES}"

SAMBA4_LIBS="heimdal,!zlib,!popt,!talloc,!pytalloc,!pytalloc-util,!tevent,!pytevent,!tdb,!pytdb,!ldb,!pyldb"

PERL_VERNDORLIB="${datadir}/perl5/vendor_perl/"

EXTRA_OECONF += "--enable-fhs \
                 --with-piddir=${localstatedir}/run \
                 --with-sockets-dir=${localstatedir}/run/samba \
                 --with-modulesdir=${libdir}/samba \
                 --with-pammodulesdir=${libdir}/security \
                 --with-lockdir=${localstatedir}/lib/samba \
                 --with-cachedir=${localstatedir}/lib/samba \
                 --with-perl-lib-install-dir=${PERL_VERNDORLIB} \
                 --disable-gnutls \
                 --disable-rpath-install \
                 --with-shared-modules=${SAMBA4_MODULES} \
                 --bundled-libraries=${SAMBA4_LIBS} \
                 --with-system-mitkrb5 \
                 --without-ad-dc \
                 ${@base_conditional('TARGET_ARCH', 'x86_64', '', '--disable-glusterfs', d)} \
                 --with-cluster-support \
                 --enable-old-ctdb \
                 --with-profiling-data \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix} \
                "

LDFLAGS += "-Wl,-z,relro,-z,now"

do_install_append() {
    rmdir --ignore-fail-on-non-empty "${D}/${localstatedir}/run"

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        for i in nmb smb winbind; do
            install -m 0644 packaging/systemd/$i.service ${D}${systemd_unitdir}/system
        done
        sed -e 's,@BASE_BINDIR@,${base_bindir},g' \
            -e 's,@SBINDIR@,${sbindir},g' \
            -i ${D}${systemd_unitdir}/system/*.service

	install -d ${D}${sysconfdir}/tmpfiles.d
	echo "d ${localstatedir}/log/samba 0755 root root -" \
            > ${D}${sysconfdir}/tmpfiles.d/99-${BPN}.conf
    fi

    install -d ${D}${sysconfdir}/samba
    echo "127.0.0.1 localhost" > ${D}${sysconfdir}/samba/lmhosts
    install -m644 packaging/LSB/smb.conf ${D}${sysconfdir}/samba/smb.conf

    install -d ${D}${libdir}/tmpfiles.d
    install -m644 packaging/systemd/samba.conf.tmp ${D}${libdir}/tmpfiles.d/samba.conf

    install -d ${D}${sysconfdir}/sysconfig/
    install -m644 packaging/systemd/samba.sysconfig ${D}${sysconfdir}/sysconfig/samba
}

PACKAGES += "${PN}-python ${PN}-python-dbg ${PN}-pidl libwinbind libwinbind-dbg libwinbind-krb5-locator"

FILES_${PN} += "/run \
                ${libdir}/security/pam_smbpass.so \
                ${libdir}/tmpfiles.d/* \
               "

SMB_SERVICE="${systemd_unitdir}/system/nmb.service ${systemd_unitdir}/system/smb.service"
FILES_${PN} +="${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${SMB_SERVICE}', '', d)}"

FILES_${PN}-dbg += "${libdir}/samba/idmap/.debug/* \
                    ${libdir}/samba/pdb/.debug/* \
                    ${libdir}/samba/auth/.debug/* \
                    ${libdir}/samba/nss_info/.debug/* \
                    ${libdir}/samba/ldb/.debug/* \
                    ${libdir}/samba/vfs/.debug/* \
                    ${libdir}/security/.debug/pam_smbpass.so \
                   "

FILES_libwinbind = "${libdir}/security/pam_winbind.so"
FILES_libwinbind += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_unitdir}/system/winbind.service', '', d)}"
FILES_libwinbind-dbg = "${libdir}/security/.debug/pam_winbind.so"
FILES_libwinbind-krb5-locator = "${libdir}/winbind_krb5_locator.so"

FILES_${PN}-python = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/dcerpc/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/dcerpc/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/external/* \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/netcmd/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/provision/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/samba3/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/samba3/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/tests/* \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/web_server/* \
                     "
FILES_${PN}-python-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug/* \
                          ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/.debug/* \
                          ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/samba3/.debug/* \
                          ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/dcerpc/.debug/* \
                         "

FILES_${PN}-pidl = "${datadir}/perl5/vendor_perl/*"
