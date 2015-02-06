SECTION = "console/network"
LICENSE = "GPL-3.0"
DEPENDS = "readline virtual/libiconv zlib popt"

SAMBA_MIRROR = "http://samba.org/samba/ftp"

MIRRORS += "\
${SAMBA_MIRROR}    http://mirror.internode.on.net/pub/samba \n \
${SAMBA_MIRROR}    http://www.mirrorservice.org/sites/ftp.samba.org \n \
"

SRC_URI = "${SAMBA_MIRROR}/stable/samba-${PV}.tar.gz \
           file://volatiles.03_samba \
           file://smb.conf \
           file://init.samba \
           file://init.winbind \
           file://tdb.pc \
           file://nmb.service \
           file://smb.service \
           file://winbind.service \
"

S = "${WORKDIR}/samba-${PV}/source"

inherit autotools-brokensep update-rc.d systemd

SYSTEMD_PACKAGES = "${PN} winbind"
SYSTEMD_SERVICE_${PN} = "nmb.service smb.service"
SYSTEMD_SERVICE_winbind = "winbind.service"

SAMBAMMAP = "no"
SAMBAMMAP_libc-glibc = "yes"

# The file system settings --foodir=dirfoo and overridden unconditionally
# in the samba config by --with-foodir=dirfoo - even if the --with is not
# specified!  Fix that here.  Set the privatedir to /etc/samba/private.
EXTRA_OECONF='--disable-cups \
              --with-readline=${STAGING_LIBDIR}/.. \
              --with-libiconv=${STAGING_LIBDIR}/.. \
              --without-automount \
              --with-configdir=${sysconfdir}/samba \
              --with-privatedir=${sysconfdir}/samba/private \
              --with-lockdir=${localstatedir}/lock \
              --with-piddir=${localstatedir}/run \
              --with-logfilebase=${localstatedir}/log/samba \
              --libdir=${libdir} \
              --with-mandir=${mandir} \
              --with-swatdir=${datadir}/swat \
              --with-aio-support \
              --with-winbind \
              --with-wbclient \
              --without-acl-support \
              --disable-avahi \
              samba_cv_struct_timespec=yes \
              libreplace_cv_HAVE_MMAP=${SAMBAMMAP}'

PACKAGECONFIG = "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)}"
PACKAGECONFIG[pam] = "--with-pam,--without-pam,libpam"
PACKAGECONFIG[fam] = "--enable-fam,--disable-fam,gamin"
PACKAGECONFIG[talloc] = "--enable-external-libtalloc --with-libtalloc, --disable-external-libtalloc --without-libtalloc, talloc"

INITSCRIPT_PACKAGES = "samba winbind"
INITSCRIPT_NAME_samba = "samba"
INITSCRIPT_NAME_winbind = "winbind"
# No dependencies, goes in at level 20 (NOTE: take care with the
# level, later levels put the shutdown later too - see the links
# in rc6.d, the shutdown must precede network shutdown).
INITSCRIPT_PARAMS = "defaults"
CONFFILES_${PN} = "${sysconfdir}/samba/smb.conf"

do_configure_prepend () {
    ./script/mkversion.sh
    if [ ! -e acinclude.m4 ]; then
        touch aclocal.m4    
        cat aclocal.m4 > acinclude.m4
    fi
}

do_compile () {
    oe_runmake proto_exists
    base_do_compile
}

do_install_append() {
    install -d ${D}${libdir}/pkgconfig/
    cp ${WORKDIR}/tdb.pc ${D}${libdir}/pkgconfig/
    mv ${D}${libdir}/libsmbclient.so ${D}${libdir}/libsmbclient.so.0 || true
    ln -sf libsmbclient.so.0 ${D}${libdir}/libsmbclient.so
    mkdir -p ${D}${base_sbindir}
    rm -f ${D}${bindir}/*.old
    rm -f ${D}${sbindir}/*.old
    [ -f ${D}${sbindir}/mount.cifs ] && mv ${D}${sbindir}/mount.cifs ${D}${base_sbindir}/
    [ -f ${D}${sbindir}/umount.cifs ] && mv ${D}${sbindir}/umount.cifs ${D}${base_sbindir}/

    # This is needed for < 3.2.4
    rm -f ${D}${sbindir}/mount.smbfs ${D}${base_sbindir}/mount.smbfs
    if [ -f ${D}${bindir}/smbmount ]; then
        ln -sf ${bindir}/smbmount ${D}${base_sbindir}/mount.smb
        ln -sf ${bindir}/smbmount ${D}${base_sbindir}/mount.smbfs
    fi
    
        install -D -m 755 ${WORKDIR}/init.samba ${D}${sysconfdir}/init.d/samba
        install -D -m 755 ${WORKDIR}/init.winbind ${D}${sysconfdir}/init.d/winbind
    install -D -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba/smb.conf
    install -D -m 644 ${WORKDIR}/volatiles.03_samba ${D}${sysconfdir}/default/volatiles/volatiles.03_samba
    install -d ${D}/var/log/samba
    install -d ${D}/var/spool/samba

    # Install other stuff not installed by "make install"
    if [ -d ${WORKDIR}/${PN}-${PV}/nsswitch ]; then
        install -m 0644 ${WORKDIR}/${PN}-${PV}/nsswitch/libnss_winbind.so ${D}${libdir}/libnss_winbind.so.2
        install -m 0644 ${WORKDIR}/${PN}-${PV}/nsswitch/libnss_wins.so ${D}${libdir}/libnss_wins.so.2
    fi

    rmdir --ignore-fail-on-non-empty ${D}${base_sbindir}
    sed -i -e '1s,#!.*perl,#!${USRBINPATH}/env perl,' ${D}${bindir}/findsmb

    # usershare mount place
    mkdir -p ${D}${localstatedir}/lib/samba/usershares

    # Remove sysinit script if sysvinit is not in DISTRO_FEATURES
    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'false', 'true', d)}; then
        rm -rf ${D}${sysconfdir}/init.d/
    fi

    install -d ${D}${systemd_unitdir}/system
    for i in nmb smb winbind; do
        install -m 0644 ${WORKDIR}/$i.service ${D}${systemd_unitdir}/system
    done
    sed -e 's,@BASE_BINDIR@,${base_bindir},g' \
        -e 's,@SBINDIR@,${sbindir},g' \
        -i ${D}${systemd_unitdir}/system/*.service

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
	install -d ${D}${sysconfdir}/tmpfiles.d
	echo "d ${localstatedir}/log/samba 0755 root root -" \
            > ${D}${sysconfdir}/tmpfiles.d/99-${BPN}.conf
    fi
}

DEPENDS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-systemctl-native', '', d)}"
pkg_postinst_${PN} () {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd sysvinit', 'true', 'false', d)}; then
        if [ -n "$D" ]; then
            OPTS="--root=$D"
        fi
        systemctl $OPTS mask samba.service
    fi
}

pkg_postinst_libnss-winbind () {
    # add wins to the list of resolvers
    ns=$D${sysconfdir}/nsswitch.conf
    if ! grep "hosts:.*wins" $ns > /dev/null; then
    hosts="`grep '^hosts:' $ns`"
    hosts=`echo "$hosts" | sed 's/\[/\\\\[/g; s/\]/\\\\]/g'`
    sed -i "s/$hosts/$hosts wins/" "$ns"
    fi
}
