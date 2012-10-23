SECTION = "console/network"
LICENSE = "GPL"
DEPENDS = "readline virtual/libiconv talloc"

SRC_URI = "http://samba.org/samba/ftp/stable/samba-${PV}.tar.gz \
           file://volatiles.03_samba \
           file://smb.conf \
           file://init.samba \
           file://init.winbind \
           file://tdb.pc \
           "

S = "${WORKDIR}/samba-${PV}/source"

inherit autotools update-rc.d

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
              --with-logfilebase=${localstatedir}/log \
              --with-libdir=${libdir} \
              --with-mandir=${mandir} \
              --with-swatdir=${datadir}/swat \
              --with-aio-support \
              --without-libtalloc \
              samba_cv_struct_timespec=yes \
              libreplace_cv_HAVE_MMAP=${SAMBAMMAP}'

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
        install -m 0644 ${WORKDIR}/${PN}-${PV}/nsswitch/libnss_winbind.so ${D}${libdir}/libnss_winbind.so.2
        install -m 0644 ${WORKDIR}/${PN}-${PV}/nsswitch/libnss_wins.so ${D}${libdir}/libnss_wins.so.2

}



pkg_postinst_winbind () {
    # add wins to the list of resolvers
    ns=$D${sysconfdir}/nsswitch.conf
    if ! grep "hosts:.*wins" $ns > /dev/null; then
	hosts="`grep '^hosts:' $ns`"
	hosts=`echo "$hosts" | sed 's/\[/\\\\[/g; s/\]/\\\\]/g'`
	sed -i "s/$hosts/$hosts wins/" "$ns"
    fi
}
