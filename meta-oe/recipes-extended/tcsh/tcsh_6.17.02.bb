DESCRIPTION = "TENEX C Shell, an enhanced version of Berkeley csh \
 The TENEX C Shell is an enhanced version of the Berkeley Unix C shell. \
 It includes all features of 4.4BSD C shell, plus a command-line editor, \
 programmable word completion, spelling correction and more."

HOMEPAGE = "http://www.tcsh.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://Copyright;md5=1cf29be62df2be1a3763118b25b4c780"
SECTION = "base"

SRC_URI = " \
	${DEBIAN_MIRROR}/main/t/tcsh/tcsh_6.17.02.orig.tar.gz;name=tarball \
	${DEBIAN_MIRROR}/main/t/tcsh/tcsh_6.17.02-4.diff.gz;name=diffs \
	file://01_build.1.patch \
	file://01_build.2.patch \
	file://01_build.3.patch \
	file://07_nls.patch \
	file://15_no-strip.patch \
	file://disable-test-notty.patch \
	file://disable-test-nice.patch \
	file://fix-make-catalogs.patch \
	file://disable-lexical.at-31.patch \
	file://12_unknown_lscolors.patch \
	file://tcsh-6.17.02-multibyte.patch \
	file://disable-broken-test.patch \
	file://cross-compile.patch \
"

inherit autotools

do_install_append () {
	oe_runmake install.man DESTDIR=${D}

	install -d ${D}${base_bindir}
	ln -s /usr/bin/tcsh ${D}${base_bindir}/tcsh

	install -d ${D}${sysconfdir}/csh/login.d
	install -m 0644 ${S}/debian/csh.cshrc ${S}/debian/csh.login ${S}/debian/csh.logout ${S}/complete.tcsh ${D}${sysconfdir}
	install -D -m 0644 ${S}/csh-mode.el ${D}${datadir}/emacs/site-lisp/csh-mode.el
}

FILES_${PN} += "${datadir}/emacs/site-lisp/csh-mode.el"


pkg_postinst_${PN} () {
#!/bin/sh -e
echo /usr/bin/tcsh >> $D/etc/shells
}

SRC_URI[tarball.md5sum] = "ad6e89ddb654972b4c2a8bad06778625"
SRC_URI[tarball.sha256sum] = "8c675729810eb49102b68e09be4f3f592dfa0be2c238f0d0a58e5c1147da7dd8"
SRC_URI[diffs.md5sum] = "735286c36a83a043015b30871c7ab6b6"
SRC_URI[diffs.sha256sum] = "61c0b0f7ebbc07544551f56b66c6e78def702c2197307e960898182b340ffe67"
