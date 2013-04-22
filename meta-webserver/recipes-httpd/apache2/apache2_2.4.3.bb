DESCRIPTION = "The Apache HTTP Server is a powerful, efficient, and \
extensible web server."
SUMMARY = "Apache HTTP Server"
HOMEPAGE = "http://httpd.apache.org/"
DEPENDS = "libtool-native apache2-native openssl expat pcre apr apr-util"
SECTION = "net"
LICENSE = "Apache-2.0"
PR = "r1"

SRC_URI = "http://www.apache.org/dist/httpd/httpd-${PV}.tar.bz2 \
           file://server-makefile.patch \
           file://httpd-2.4.1-corelimit.patch \
           file://httpd-2.4.1-export.patch \
           file://httpd-2.4.1-selinux.patch \
           file://httpd-2.4.2-r1332643.patch \
           file://apache-configure_perlbin.patch \
           file://replace-lynx-to-curl-in-apachectl-script.patch \
           file://apache-ssl-ltmain-rpath.patch \
           file://httpd-2.4.3-fix-race-issue-of-dir-install.patch \
           file://init"

LIC_FILES_CHKSUM = "file://LICENSE;md5=eff226ae95d0516d6210ed77dfdf2dcc"
SRC_URI[md5sum] = "87aaf7bc7e8715f0455997bb8c6791aa"
SRC_URI[sha256sum] = "d82102b9c111f1892fb20a2bccf4370de579c6521b2f172ed0b36f2759fb249e"

S = "${WORKDIR}/httpd-${PV}"

inherit autotools update-rc.d

CFLAGS_append = " -DPATH_MAX=4096"
CFLAGS_prepend = "-I${STAGING_INCDIR}/openssl "
EXTRA_OECONF = "--enable-ssl \
    --with-ssl=${STAGING_LIBDIR}/.. \
    --with-expat=${STAGING_LIBDIR}/.. \
    --with-apr=${WORKDIR}/apr-1-config \
    --with-apr-util=${WORKDIR}/apu-1-config \
    --enable-info \
    --enable-rewrite \
    --with-dbm=sdbm \
    --with-berkeley-db=no \
    --localstatedir=/var/${BPN} \
    --with-gdbm=no \
    --with-ndbm=no \
    --includedir=${includedir}/${BPN} \
    --datadir=${datadir}/${BPN} \
    --sysconfdir=${sysconfdir}/${BPN} \
    --libexecdir=${libdir}/${BPN}/modules \
    ap_cv_void_ptr_lt_long=no \
    --enable-mpms-shared \
    ac_cv_have_threadsafe_pollset=no"

do_configure_prepend() {
    # FIXME: this hack is required to work around an issue with apr/apr-util
    # Can be removed when fixed in OE-Core (also revert --with-* options above)
    # see http://bugzilla.yoctoproject.org/show_bug.cgi?id=3267
    cp ${STAGING_BINDIR_CROSS}/apr-1-config ${STAGING_BINDIR_CROSS}/apu-1-config ${WORKDIR}
    sed -i -e 's:location=source:location=installed:' ${WORKDIR}/apr-1-config
    sed -i -e 's:location=source:location=installed:' ${WORKDIR}/apu-1-config
}

do_install_append() {
    install -d ${D}/${sysconfdir}/init.d
    cat ${WORKDIR}/init | \
        sed -e 's,/usr/sbin/,${sbindir}/,g' \
            -e 's,/usr/bin/,${bindir}/,g' \
            -e 's,/usr/lib,${libdir}/,g' \
            -e 's,/etc/,${sysconfdir}/,g' \
            -e 's,/usr/,${prefix}/,g' > ${D}/${sysconfdir}/init.d/${BPN}
    chmod 755 ${D}/${sysconfdir}/init.d/${BPN}
    # remove the goofy original files...
    rm -rf ${D}/${sysconfdir}/${BPN}/original
    # Expat should be found in the staging area via DEPENDS...
    rm -f ${D}/${libdir}/libexpat.*

    install -d ${D}${sysconfdir}/${BPN}/conf.d
    install -d ${D}${sysconfdir}/${BPN}/modules.d

    # Ensure configuration file pulls in conf.d and modules.d
    printf "\nIncludeOptional ${sysconfdir}/${BPN}/conf.d/*.conf" >> ${D}/${sysconfdir}/${BPN}/httpd.conf
    printf "\nIncludeOptional ${sysconfdir}/${BPN}/modules.d/*.conf\n\n" >> ${D}/${sysconfdir}/${BPN}/httpd.conf
}

SYSROOT_PREPROCESS_FUNCS += "apache_sysroot_preprocess"

apache_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}/
    install -m 755 ${D}${bindir}/apxs ${SYSROOT_DESTDIR}${bindir_crossscripts}/
    sed -i 's!my $installbuilddir = .*!my $installbuilddir = "${STAGING_DIR_HOST}/${datadir}/${BPN}/build";!' ${SYSROOT_DESTDIR}${bindir_crossscripts}/apxs
    sed -i 's!my $libtool = .*!my $libtool = "${STAGING_BINDIR_CROSS}/${TARGET_PREFIX}libtool";!' ${SYSROOT_DESTDIR}${bindir_crossscripts}/apxs

    sed -i 's!^APR_CONFIG = .*!APR_CONFIG = ${STAGING_BINDIR_CROSS}/apr-1-config!' ${SYSROOT_DESTDIR}${datadir}/${BPN}/build/config_vars.mk
    sed -i 's!^APU_CONFIG = .*!APU_CONFIG = ${STAGING_BINDIR_CROSS}/apu-1-config!' ${SYSROOT_DESTDIR}${datadir}/${BPN}/build/config_vars.mk
    sed -i 's!^includedir = .*!includedir = ${STAGING_INCDIR}/apache2!' ${SYSROOT_DESTDIR}${datadir}/${BPN}/build/config_vars.mk
}

#
# implications - used by update-rc.d scripts
#
INITSCRIPT_NAME = "apache2"
INITSCRIPT_PARAMS = "defaults 91 20"
LEAD_SONAME = "libapr-1.so.0"

PACKAGES = "${PN}-doc ${PN}-dev ${PN}-dbg ${PN}"

CONFFILES_${PN} = "${sysconfdir}/${BPN}/httpd.conf \
                   ${sysconfdir}/${BPN}/magic \
                   ${sysconfdir}/${BPN}/mime.types \
                   ${sysconfdir}/init.d/${BPN} "

# we override here rather than append so that .so links are
# included in the runtime package rather than here (-dev)
# and to get build, icons, error into the -dev package
FILES_${PN}-dev = "${datadir}/${BPN}/build \
                   ${datadir}/${BPN}/icons \
                   ${datadir}/${BPN}/error \
                   ${bindir}/apr-config ${bindir}/apu-config \
                   ${libdir}/apr*.exp \
                   ${includedir}/${BPN} \
                   ${libdir}/*.la \
                   ${libdir}/*.a"

# manual to manual
FILES_${PN}-doc += " ${datadir}/${BPN}/manual"

#
# override this too - here is the default, less datadir
#
FILES_${PN} =  "${bindir} ${sbindir} ${libexecdir} ${libdir}/lib*.so.* ${sysconfdir} \
                ${sharedstatedir} ${localstatedir} /bin /sbin /lib/*.so* \
                ${libdir}/${BPN}"

# we want htdocs and cgi-bin to go with the binary
FILES_${PN} += "${datadir}/${BPN}/htdocs ${datadir}/${BPN}/cgi-bin"

#make sure the lone .so links also get wrapped in the base package
FILES_${PN} += "${libdir}/lib*.so ${libdir}/pkgconfig/*"

FILES_${PN}-dbg += "${libdir}/${BPN}/modules/.debug"

RDEPENDS_${PN} += "openssl libgcc"
