SUMMARY = "mongodb"
LICENSE = "AGPL-3.0 & Apache-2.0 & Zlib"
LIC_FILES_CHKSUM = "file://GNU-AGPL-3.0.txt;md5=73f1eb20517c55bf9493b7dd6e480788 \
                    file://APACHE-2.0.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "openssl libpcre libpcap zlib boost curl python \
           python-setuptools-native python-typing-native \
           python-pyyaml-native python-cheetah-native \
           "

inherit scons dos2unix siteinfo pythonnative

PV = "4.0.1+git${SRCPV}"
#v4.0.1
SRCREV = "54f1582fc6eb01de4d4c42f26fc133e623f065fb"
SRC_URI = "git://github.com/mongodb/mongo.git;branch=v4.0 \
           file://0001-Tell-scons-to-use-build-settings-from-environment-va.patch \
           file://0001-Use-long-long-instead-of-int64_t.patch \
           file://0001-Use-__GLIBC__-to-control-use-of-gnu_get_libc_version.patch \
           file://0002-Add-a-definition-for-the-macro-__ELF_NATIVE_CLASS.patch \
           file://arm64-support.patch \
           file://0001-IntelRDFPMathLib20U1-Check-for-__DEFINED_wchar_t.patch \
           file://disable-hw-crc32-on-arm64-s390x.patch \
           file://0001-Support-deprecated-resolver-functions.patch \
           "
SRC_URI_append_libc-musl ="\
           file://0002-Fix-default-stack-size-to-256K.patch \
           file://0004-wiredtiger-Disable-strtouq-on-musl.patch \
           "
S = "${WORKDIR}/git"

# Wiredtiger supports only 64-bit platforms
PACKAGECONFIG_x86-64 ??= "tcmalloc wiredtiger"
PACKAGECONFIG_aarch64 ??= "tcmalloc wiredtiger"
PACKAGECONFIG ??= "tcmalloc"
# gperftools compilation fails for arm below v7 because of missing support of
# dmb operation. So we use system-allocator instead of tcmalloc
PACKAGECONFIG_remove_armv6 = "tcmalloc"
PACKAGECONFIG_remove_libc-musl = "tcmalloc"

#std::current_exception is undefined for arm < v6
COMPATIBLE_MACHINE_armv4 = "(!.*armv4).*"
COMPATIBLE_MACHINE_armv5 = "(!.*armv5).*"
COMPATIBLE_MACHINE_armv7a = "(!.*armv7a).*"
COMPATIBLE_MACHINE_armv7ve = "(!.*armv7ve).*"
COMPATIBLE_MACHINE_powerpc = "(!.*ppc).*"
# https://jira.mongodb.org/browse/SERVER-16898
COMPATIBLE_HOST_mipsarch = "null"

PACKAGECONFIG[tcmalloc] = "--use-system-tcmalloc,--allocator=system,gperftools,"
PACKAGECONFIG[wiredtiger] = "--wiredtiger=on,--wiredtiger=off,,"

EXTRA_OESCONS = "--prefix=${D}${prefix} \
                 LIBPATH=${STAGING_LIBDIR} \
                 LINKFLAGS='${LDFLAGS}' \
                 CXXFLAGS='${CXXFLAGS}' \
                 TARGET_ARCH=${TARGET_ARCH} \
                 --ssl \
                 --disable-warnings-as-errors \
                 --use-system-pcre \
                 --use-system-zlib \
                 --js-engine=none \
                 --nostrip \
                 --endian=${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
                 ${PACKAGECONFIG_CONFARGS} \
                 mongod mongos"

do_configure_prepend() {
        # tests use hex floats, not supported in plain C++
        sed -e 's|-std=c++11|-std=gnu++11|g' -i ${S}/SConstruct
}
scons_do_compile() {
        ${STAGING_BINDIR_NATIVE}/scons ${PARALLEL_MAKE} ${EXTRA_OESCONS} || \
        die "scons build execution failed."
}

scons_do_install() {
        ${STAGING_BINDIR_NATIVE}/scons install ${EXTRA_OESCONS}|| \
        die "scons install execution failed."
}
