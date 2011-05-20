SUMMARY = "C library for embedded systems"
DESCRIPTION = "The name uClibc is an abbreviation for 'the \
microcontroller C library'. For simplicity, uClibc is pronounced \
'yew-see-lib-see'.  The goal of uClibc is to provide as much \
functionality as possible in a small amount of space, and it is intended \
primarily for embedded use. It is also highly configurable in supported \
features, at the cost of ABI differences for different configurations. \
uClibc has been designed from the ground up to be a C library for \
embedded Linux.  It is NOT compatible with binaries linked against glibc."

LICENSE = "LGPL"
SECTION = "libs"
PRIORITY = "required"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=a6f89e2100d9b6cdffcea4f398e37343 \
		    file://COPYING.LIB.boilerplate;md5=aaddeadcddeb918297e0e4afc52ce46f \
file://${S}/test/regex/testregex.c;startline=1;endline=31;md5=234efb227d0a40677f895e4a1e26e960"
INC_PR = "r2"

require uclibc-config.inc
STAGINGCC = "gcc-cross-intermediate"
STAGINGCC_virtclass-nativesdk = "gcc-crosssdk-intermediate"
PATH_prepend = "${STAGING_BINDIR_TOOLCHAIN}.${STAGINGCC}:"

TOOLCHAIN_OPTIONS = " --sysroot=${STAGING_DIR_TCBOOTSTRAP}"

# siteconfig.bbclass runs configure which needs a working compiler
# For the compiler to work we need a working libc yet libc isn't 
# in the sysroots directory at this point. This means the libc.so
# linker script won't work as the --sysroot setting isn't correct.
# Here we create a hacked up libc linker script and pass in the right
# flags to let configure work. Ugly.
EXTRASITECONFIG = "CFLAGS='${CFLAGS} -Wl,-L${WORKDIR}/site_config_libc -L${WORKDIR}/site_config_libc -L${SYSROOT_DESTDIR}${libdir} -L${SYSROOT_DESTDIR}${base_libdir} -Wl,-L${SYSROOT_DESTDIR}${libdir} -Wl,-L${SYSROOT_DESTDIR}${base_libdir}'"
siteconfig_do_siteconfig_gencache_prepend = " \
mkdir -p ${WORKDIR}/site_config_libc; \
cp ${SYSROOT_DESTDIR}${libdir}/libc.so ${WORKDIR}/site_config_libc; \
sed -i -e 's# ${base_libdir}# ${SYSROOT_DESTDIR}${base_libdir}#g' -e 's# ${libdir}# ${SYSROOT_DESTDIR}${libdir}#g' ${WORKDIR}/site_config_libc/libc.so; \
"

#
# For now, we will skip building of a gcc package if it is a uclibc one
# and our build is not a uclibc one, and we skip a glibc one if our build
# is a uclibc build.
#
# See the note in gcc/gcc_3.4.0.oe
#

python __anonymous () {
    import bb, re
    uc_os = (re.match('.*uclibc*', bb.data.getVar('TARGET_OS', d, 1)) != None)
    if not uc_os:
        raise bb.parse.SkipPackage("incompatible with target %s" %
                                   bb.data.getVar('TARGET_OS', d, 1))
}
PROVIDES += "virtual/libc virtual/${TARGET_PREFIX}libc-for-gcc"
DEPENDS = "virtual/${TARGET_PREFIX}binutils \
           virtual/${TARGET_PREFIX}gcc-intermediate \
           linux-libc-headers ncurses-native"
RDEPENDS_${PN}-dev = "linux-libc-headers-dev"

INHIBIT_DEFAULT_DEPS = "1"

PACKAGES =+ "ldd uclibc-utils-dbg uclibc-utils uclibc-gconv uclibc-thread-db uclibc-argp uclibc-backtrace"

LEAD_SONAME = "libc.so"

# The last line (gdb and lib1) is for uclinux-uclibc builds
uclibc_baselibs = "${base_libdir}/libcrypt*.so.* ${base_libdir}/libcrypt-*.so \
                   ${base_libdir}/libintl*.so.* ${base_libdir}/libintl-*.so \
                   ${base_libdir}/libnsl*.so.* ${base_libdir}/libnsl-*.so \
                   ${base_libdir}/libresolv*.so.* ${base_libdir}/libresolv-*.so \
                   ${base_libdir}/ld*.so.* ${base_libdir}/ld*.so \
                   ${base_libdir}/libc*.so.* ${base_libdir}/libuClibc-*.so \
                   ${base_libdir}/libdl*.so.* ${base_libdir}/libdl-*.so \
                   ${base_libdir}/libm*.so.* ${base_libdir}/libm-*.so \
                   ${base_libdir}/libutil*.so.* ${base_libdir}/libutil-*.so \
                   ${base_libdir}/libpthread*.so.* ${base_libdir}/libpthread-*.so \
                   ${base_libdir}/librt*.so.* ${base_libdir}/librt-*.so \
                   ${libdir}/libc.gdb ${libdir}/libc ${base_libdir}/lib1.so \
                  "
FILES_${PN} = "${sysconfdir} ${uclibc_baselibs} /sbin/ldconfig \
               ${libexecdir} ${datadir}/zoneinfo ${libdir}/locale"
FILES_ldd = "${bindir}/ldd"
FILES_uclibc-dev_append = "\
        ${libdir}/lib*.so \
        ${libdir}/*_nonshared.a \
        ${libdir}/[S]*crt[1in].o \
        ${libdir}/crtreloc*.o \
        ${libdir}/lib*.a \
        ${includedir}/*.h ${includedir}/*/*.h \
       "
FILES_uclibc-utils = "${bindir} ${sbindir}"
FILES_uclibc-utils-dbg += "${bindir}/.debug ${sbindir}/.debug"
FILES_uclibc-gconv = "${libdir}/gconv"
FILES_uclibc-thread-db = "${base_libdir}/libthread_db*"
FILES_uclibc-argp = "${base_libdir}/libuargp-*.so ${base_libdir}/libuargp*.so.*"
FILES_uclibc-backtrace = "${base_libdir}/libubacktrace-*.so ${base_libdir}/libubacktrace*.so.*"

RPROVIDES_uclibc-dev += "libc-dev virtual-libc-dev"
# uclibc does not really have libsegfault but then using the one from glibc is also not
# going to work. So we pretend that we have it to make bitbake not pull other recipes
# to satisfy this dependency for the images/tasks
RPROVIDES_${PN} += "libsegfault"

SRC_URI = "\
        http://www.uclibc.org/downloads/uClibc-${PV}.tar.bz2;name=uClibc-${PV} \
        "

# do_stage barfs on a CC with whitespace, therefore put the 'HOST_CC_ARCH' in
# the CFLAGS (when building the utils).
OEMAKE_NO_CC = "'STRIPTOOL=true' 'LD=${LD}'"
EXTRA_OEMAKE = "${OEMAKE_NO_CC} \
                'HOSTCC=${BUILD_CC}' \
                'HOST_CFLAGS=${BUILD_CFLAGS}' \
                'CC=${CC}' \
                ARCH=${UCLIBC_ARCH}"

EXTRA_OEMAKE_task_do_package = "${OEMAKE_NO_CC}"

# enable verbose output:
export V="2"

# -O<n> -fno-omit-frame-pointer ends up with GCC ICE on thumb as reported
# http://gcc.gnu.org/bugzilla/show_bug.cgi?id=44860
#
CFLAGS_arm := "${@oe_filter_out('-fno-omit-frame-pointer', '${CFLAGS}', d)}"
UCLIBC_EXTRA_CFLAGS  := "${@oe_filter_out('(-I\S+|-i\S+)', '${CFLAGS}', d)}"
UCLIBC_EXTRA_LDFLAGS := "${@oe_filter_out('(-L\S+|-l\S+)', '${LDFLAGS}', d)}"
do_compile_prepend () {
  unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
}

configmangle = '/^KERNEL_HEADERS/d; \
                /^RUNTIME_PREFIX/d; \
                /^DEVEL_PREFIX/d; \
                /^SHARED_LIB_LOADER_PREFIX/d; \
                /^UCLIBC_EXTRA_CFLAGS/d; \
                s,.*UCLIBC_HAS_WCHAR.*,UCLIBC_HAS_WCHAR=y,g; \
                ${@["","s,.*UCLIBC_HAS_LOCALE.*,UCLIBC_HAS_LOCALE=y,;"][bb.data.getVar("USE_NLS", d, 1) == "yes"]} \
                ${@["","s,.*LDSO_GNU_HASH_SUPPORT.*,# LDSO_GNU_HASH_SUPPORT is not set,;"][bb.data.getVar("TARGET_ARCH", d, 1) in ['mips', 'mipsel', 'mips64', 'mips64el', 'avr32']]} \
                /^CROSS/d; \
                /^TARGET_ARCH=/d; \
                /^TARGET_/s,^\([^=]*\).*,# \1 is not set,g; \
                s,^DOSTRIP.*,# DOSTRIP is not set,g; \
                /_[EO]*ABI/d; \
                /HAS_FPU/d; \
               '
OE_FEATURES := "${@features_to_uclibc_conf(d)}"
OE_DEL      := "${@features_to_uclibc_del(d)}"
python () {
  if "${OE_DEL}":
    bb.data.setVar('configmangle_append', "${OE_DEL}" + "\n", d)
  if "${OE_FEATURES}":
    bb.data.setVar('configmangle_append',
                   "/^### DISTRO FEATURES$/a\\\n%s\n\n" %
                   ("\\n".join((bb.data.expand("${OE_FEATURES}", d).split("\n")))),
                   d)
  bb.data.setVar('configmangle_append',
                 "/^### CROSS$/a\\\n%s\n" %
                  ("\\n".join(["CROSS_COMPILER_PREFIX=\"${TARGET_PREFIX}\"",
                        "UCLIBC_EXTRA_CFLAGS=\"${UCLIBC_EXTRA_CFLAGS}\"",
                         "KERNEL_HEADERS=\"${STAGING_INCDIR}\"",
                         "RUNTIME_PREFIX=\"/\"",
                         "DEVEL_PREFIX=\"/${prefix}\"",
                         "SHARED_LIB_LOADER_PREFIX=\"/lib\"",
                        ])
                  ),
                 d)
  bb.data.setVar('configmangle_append',
                 "/^### TGT$/a\\\nTARGET_ARCH=\"%s\"\\nTARGET_%s=y\n" %
                        ("${UCLIBC_ARCH}", "${UCLIBC_ARCH}"),
                 d)
  bb.data.setVar('configmangle_append',
    "/^### FPU$/a\\\n%s\n\n" % (["UCLIBC_HAS_FPU=y","# UCLIBC_HAS_FPU is not set"][bb.data.getVar('TARGET_FPU', d, 1) in [ 'soft' ]]), d)
  if "${UCLIBC_ENDIAN}":
    bb.data.setVar('configmangle_append',
                   "/^### ABI$/a\\\nARCH_%s_ENDIAN=y\n\n" % ("${UCLIBC_ENDIAN}"),
                   d)
  if "${UCLIBC_ABI}":
    bb.data.setVar('configmangle_append',
                   "/^### ABI$/a\\\nCONFIG_%s=y\n\n" % ("${UCLIBC_ABI}"),
                   d)
}

do_patch_append() {
        os.system("ln -sf ${STAGING_INCDIR}/linux ${S}/include/linux")
        os.system("ln -sf ${STAGING_INCDIR}/asm ${S}/include/asm")
}

do_configure() {
        rm -f ${S}/.config

        # OpenEmbedded splits the uClibc.config in two parts:
        # uClibc.machine, uClibc.distro
        echo "### uClibc.machine ###" >${S}/merged.config
        cat ${WORKDIR}/uClibc.machine >>${S}/merged.config
        echo "### uClibc.distro ###" >>${S}/merged.config
        cat ${WORKDIR}/uClibc.distro >>${S}/merged.config
        echo "### CROSS" >>${S}/merged.config
        echo "### TGT" >>${S}/merged.config
        echo "### MMU" >>${S}/merged.config
        echo "### FPU" >>${S}/merged.config
        echo "### ABI" >>${S}/merged.config
        echo "### DISTRO FEATURES" >>${S}/merged.config
        cp ${S}/merged.config ${S}/.config

        # Mangle the resulting .config depending on OE variables
        sed -i -e '${configmangle}' ${S}/.config

        oe_runmake oldconfig
}

do_install() {
        oe_runmake PREFIX=${D} install
        oe_runmake PREFIX=${D} "SSP_ALL_CFLAGS=${TARGET_LINK_HASH_STYLE}" install_utils
}
