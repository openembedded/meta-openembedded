SECTION = "base"
require uclibc_git.bb

DEPENDS = "linux-libc-headers ncurses-native virtual/${TARGET_PREFIX}gcc-initial"
PROVIDES = "virtual/${TARGET_PREFIX}libc-initial"

PACKAGES = ""
PACKAGES_DYNAMIC = ""

STAGINGCC = "gcc-cross-initial"
STAGINGCC_virtclass-nativesdk = "gcc-crosssdk-initial"

do_install() {
	# Install initial headers into the cross dir
	make PREFIX=${D} DEVEL_PREFIX=${prefix}/ RUNTIME_PREFIX=/ \
		install_headers install_startfiles
	${CC} -nostdlib -nostartfiles -shared -x c /dev/null \
		-o lib/libc.so
	${CC} -nostdlib -nostartfiles -shared -x c /dev/null \
		-o lib/libm.so
	install -d ${D}${libdir}
	install -m 755 lib/lib[cm].so ${D}${libdir}
	# add links to linux-libc-headers: gcc-{cross,crossdk}-intermediate need this.
        for t in linux asm asm-generic; do
                if [ -d ${D}${includedir}/$t ]; then
                    rm -rf ${D}${includedir}/$t
                fi
                ln -sf ${STAGING_DIR_TARGET}${includedir}/$t ${D}${includedir}/
        done
}
do_compile() {
	:
}

do_siteconfig () {
        :
}

do_populate_sysroot[sstate-outputdirs] = "${STAGING_DIR_TCBOOTSTRAP}"
