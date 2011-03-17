DESCRIPTION = "Secure Socket Layer (SSL) binary and related cryptographic tools."
HOMEPAGE = "http://www.openssl.org/"
SECTION = "libs/network"

# "openssl | SSLeay" dual license
LICENSE = "openssl"
LIC_FILES_CHKSUM = "file://LICENSE;md5=83d26c69f6f0172ee7f795790424b453"

SRC_URI = "http://www.openssl.org/source/openssl-${PV}.tar.gz;name=src"
S = "${WORKDIR}/openssl-${PV}"

inherit siteinfo

INC_PR = "r13"

AR_append = " r"
CFLAG = "${@base_conditional('SITEINFO_ENDIANNESS', 'le', '-DL_ENDIAN', '-DB_ENDIAN', d)} \
	-DTERMIO ${FULL_OPTIMIZATION} -Wall"

# -02 does not work on mipsel: ssh hangs when it tries to read /dev/urandom
CFLAG_mtx-1 := "${@'${CFLAG}'.replace('-O2', '')}"
CFLAG_mtx-2 := "${@'${CFLAG}'.replace('-O2', '')}"

export DIRS = "crypto ssl apps"
BBCLASSEXTEND = "native nativesdk"

export EX_LIBS = "-lgcc -ldl"
export AS = "${CC} -c"

PACKAGES =+ "libcrypto libssl ${PN}-misc"
FILES_libcrypto = "${libdir}/libcrypto.so.*"
FILES_libssl = "${libdir}/libssl.so.*"
FILES_${PN}-misc = "${libdir}/ssl/misc ${libdir}/ssl/openssl.cnf"

do_configure_prepend_darwin () {
	sed -i -e '/version-script=openssl\.ld/d' Configure
}

do_configure () {
	cd util
	perl perlpath.pl ${STAGING_BINDIR_NATIVE}
	cd ..
	ln -sf apps/openssl.pod crypto/crypto.pod ssl/ssl.pod doc/

	os=${HOST_OS}
	if [ "x$os" = "xlinux-uclibc" ]; then
		os=linux
	elif [ "x$os" = "xlinux-uclibceabi" ]; then
		os=linux
	elif [ "x$os" = "xlinux-gnueabi" ]; then
		os=linux
	fi
	target="$os-${HOST_ARCH}"
	case $target in
	linux-arm)
		target=linux-elf-arm
		;;
	linux-armeb)
		target=linux-elf-armeb
		;;
	linux-sh3)
		target=debian-sh3
		;;
	linux-sh4)
		target=debian-sh4
		;;
	linux-i486)
		target=debian-i386-i486
		;;
	linux-i586 | linux-viac3)
		target=debian-i386-i586
		;;
	linux-i686)
		target=debian-i386-i686/cmov
		;;
	linux-mips)
		target=debian-mips
		;;
	linux-mipsel)
		target=debian-mipsel
		;;
	linux-powerpc)
		target=linux-ppc
		;;
	linux-gnuspe-powerpc)
		target=linux-ppc
		;;
	linux-supersparc)
		target=linux-sparcv8
		;;
	linux-sparc)
		target=linux-sparcv8
		;;
	darwin-i386)
		target=darwin-i386-cc
		;;
	esac
	# inject machine-specific flags
	sed -i -e "s|^\(\"$target\",\s*\"[^:]\+\):\([^:]\+\)|\1:${CFLAG}|g" Configure
        useprefix=${prefix}
        if [ "x$useprefix" == "x" ]; then
                useprefix=/
        fi        
	perl ./Configure shared --prefix=$useprefix --openssldir=${libdir}/ssl $target
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake INSTALL_PREFIX="${D}" install

	# On x86_64, move lib/* to lib64
	if [ "${libdir}" != "${prefix}/lib" ]
	then
		install -d ${D}${libdir} ${D}${libdir}/pkgconfig
		mv ${D}${prefix}/lib/lib* ${D}${libdir}
		mv ${D}${prefix}/lib/pkgconfig/*.pc ${D}${libdir}/pkgconfig
	fi

	oe_libinstall -so libcrypto ${D}${libdir}
	oe_libinstall -so libssl ${D}${libdir}

	install -d ${D}${includedir}
	cp --dereference -R include/openssl ${D}${includedir}
}
