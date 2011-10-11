DESCRIPTION = "Guile is an interpreter for the Scheme programming language, \
packaged as a library which can be incorporated into your programs."
HOMEPAGE = "http://www.gnu.org/software/guile/guile.html"
SECTION = "devel/scheme"
DEPENDS = "guile-native gmp libtool"
PACKAGES =+ "${PN}-el"
FILES_${PN}-el = "${datadir}/emacs"
DESCRIPTION_${PN}-el = "Emacs lisp files for Guile"

LICENSE = "LGPLv2.1+"

inherit autotools gettext

acpaths = "-I ${S}/guile-config"

EXTRA_OECONF = " \
		--without-threads \
		--without-included-ltdl \
               "		

do_compile() {
	for i in $(find ${S} -name "Makefile") ; do
		sed -i -e s:-Werror::g $i
	done

	(cd libguile; oe_runmake CC="${BUILD_CC}" CFLAGS="${BUILD_CFLAGS}" LDFLAGS="${BUILD_LDFLAGS}" guile_filter_doc_snarfage)
	oe_runmake preinstguile="`which guile`"
        
        sed -i -e s:${STAGING_DIR_TARGET}::g \
               -e s:/${TARGET_SYS}::g \
               -e s:-L/usr/lib::g \
               -e s:-isystem/usr/include::g \
               -e s:,/usr/lib:,\$\{libdir\}:g \
                  guile-1.8.pc
}

SYSROOT_PREPROCESS_FUNCS = "guile_cross_config"

guile_cross_config() {
	# Create guile-config returning target values instead of native values
	install -d ${SYSROOT_DESTDIR}${STAGING_BINDIR_CROSS}
	echo '#!'`which guile`$' \\\n-e main -s\n!#\n(define %guile-build-info '\'\( >guile-config.cross
	sed -n $'s:-isystem[^ ]* ::;s:-Wl,-rpath-link,[^ ]* ::;s:^[ \t]*{[ \t]*":  (:;s:",[ \t]*": . ":;s:" *}, *\\\\:"):;/^  (/p' <libguile/libpath.h >>guile-config.cross
	echo '))' >>guile-config.cross
	cat guile-config/guile-config >>guile-config.cross
	install guile-config.cross ${SYSROOT_DESTDIR}${STAGING_BINDIR_CROSS}/guile-config
}
