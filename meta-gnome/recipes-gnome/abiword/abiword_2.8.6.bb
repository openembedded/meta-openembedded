require abiword-2.5.inc
DEPENDS += " librsvg loudmouth libwmf-native gtkmathview asio"
RCONFLICTS_${PN} = "abiword-embedded"

PR = "r2"

SRC_URI = "http://www.abisource.com/downloads/abiword/${PV}/source/abiword-${PV}.tar.gz;name=abiword \
           file://autogen-common.sh \
           file://nodolt.patch \
           file://fix.no.undefined.param.patch"

LIC_FILES_CHKSUM = "file://COPYING;md5=ecd3ac329fca77e2d0e412bec38e1c20"

SRC_URI[abiword.md5sum] = "f883b0a7f26229a9c66fd6a1a94381aa"
SRC_URI[abiword.sha256sum] = "d99089a63a6cfc1a6a4a026be9278028d47d224088d24b1853acb67e95683a15"


EXTRA_OECONF = " --disable-static  \
                 --enable-plugins \
                 --without-gnomevfs \
                 --enable-collab-backend-xmpp \
                 --enable-collab-backend-tcp \
                 --enable-collab-backend-service \
                 --with-libwmf-config=${STAGING_DIR} \
"

do_configure() {
    install -m 0755 ${WORKDIR}/autogen-common.sh ${S}/autogen-common.sh
    cd ${S}
    ./autogen-common.sh
    autotools_do_configure
}

FILES_${PN} 			+= "${libdir}/libabiword-*.so ${datadir}/mime-info ${datadir}/abiword-${SHRT_VER}/certs ${datadir}/abiword-${SHRT_VER}/ui ${datadir}/abiword-${SHRT_VER}/xsl* ${datadir}/abiword-${SHRT_VER}/mime-info ${datadir}/abiword-${SHRT_VER}/Pr*.xml"
FILES_abiword-strings           += "${datadir}/abiword-${SHRT_VER}/strings"
FILES_abiword-systemprofiles    += "${datadir}/abiword-${SHRT_VER}/system.profile*"

PACKAGES_DYNAMIC = "abiword-meta abiword-plugin-*"

python populate_packages_prepend () {
	abiword_libdir    = bb.data.expand('${libdir}/abiword-2.8/plugins', d)
	do_split_packages(d, abiword_libdir, '(.*)\.so$', 'abiword-plugin-%s', 'Abiword plugin for %s', extra_depends='')

	metapkg = "abiword-meta"
	bb.data.setVar('ALLOW_EMPTY_' + metapkg, "1", d)
	bb.data.setVar('FILES_' + metapkg, "", d)
	blacklist = [ 'abiword-plugins-dbg', 'abiword-plugins', 'abiword-plugins-doc', 'abiword-plugins-dev', 'abiword-plugins-locale' ]
	metapkg_rdepends = []
	packages = bb.data.getVar('PACKAGES', d, 1).split()
	for pkg in packages[1:]:
		if not pkg in blacklist and not pkg in metapkg_rdepends and not pkg.count("-dev") and not pkg.count("-dbg") and not pkg.count("static") and not pkg.count("locale") and not pkg.count("abiword-doc"):
			print "Modifying ", pkg
			metapkg_rdepends.append(pkg)
	bb.data.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends), d)
	bb.data.setVar('DESCRIPTION_' + metapkg, 'abiword-plugin meta package', d)
	packages.append(metapkg)
	bb.data.setVar('PACKAGES', ' '.join(packages), d)
}

FILES_${PN}-dev += "${libdir}/abiword-${SHRT_VER}/plugins/*.la"
FILES_${PN}-dbg += "${libdir}/abiword-${SHRT_VER}/plugins/.debug"
FILES_${PN}-doc += "${datadir}/abiword-*/readme*"
