SUMMARY = "mongodb"
LICENSE = "SSPL-1.0 AND Apache-2.0 AND BSD-2-Clause AND BSD-3-Clause AND BSD-4-Clause AND BSL-1.0 AND MIT AND Unicode-3.0 AND Unicode-DFS-2016 AND Unlicense"
LIC_FILES_CHKSUM = "file://LICENSE-Community.txt;md5=3a865f27f11f43ecbe542d9ea387dcf1 \
                    file://distsrc/THIRD-PARTY-NOTICES;md5=3c39e5c161df3327a0842fe6e8318b33"

DEPENDS = "openssl zlib curl \
           python3-cheetah-native python3-packaging-native \
           python3-psutil-native python3-pymongo-native \
           python3-pyyaml-native \
           "

inherit siteinfo python3native systemd useradd

SRCREV = "1fd1fee93dc835b17751f55e5ddaf193d14496d9"
SRC_URI = "git://github.com/mongodb/mongo.git;branch=v${PV};protocol=https;tag=r${PV} \
           file://0001-moduleconfig.py-python-3.12-compatibility.patch \
           file://0001-Use-packaging-instead-of-pkg_resources-for-version-p.patch \
           file://0001-distsrc-Use-shutil.which-instead-of-distutils.patch \
           file://disable_runtime_check.patch \
           file://0001-SConstruct-Respect-the-CPU-selected-by-the-compiler-.patch \
           file://0001-Use-__GLIBC__-to-control-use-of-gnu_get_libc_version.patch \
           file://0001-Support-deprecated-resolver-functions.patch \
           file://ppc64_ARCH_BITS.patch \
           file://0001-apply-msvc-workaround-for-clang-16.patch \
           file://0003-boost-Fix-build-with-clang-23.patch \
           file://0001-The-std-lib-unary-binary_function-base-classes-are-d.patch \
           file://0004-ssl-Fix-build-with-OpenSSL-4.patch \
           file://0001-SERVER-79426-fix-build-issues-under-clang-15.patch \
           file://0001-SERVER-77007-MongoDB-fails-to-compile-with-Clang-15-.patch \
           file://0005-sbe-Add-default-constructors-to-RowEq-and-RowHasher.patch \
           file://0006-pcre-Fix-build-with-PCRE2-10.49.patch \
           file://0007-SConstruct-Honor-js-engine-none.patch \
           file://0008-Do-not-include-linux-prctl.h-together-with-sys-prctl.patch \
           file://0009-DirectMmap-Use-off_t-instead-of-off64_t-for-the-offs.patch \
           file://oe-buildinfo.vars \
           "
SRC_URI:append:libc-musl = "\
           file://0002-Fix-default-stack-size-to-256K.patch \
           file://0004-wiredtiger-Disable-strtouq-on-musl.patch \
           file://0001-wiredtiger-Avoid-using-off64_t.patch \
           "

# 8.0 and newer build with Bazel only
UPSTREAM_CHECK_GITTAGREGEX = "^r(?P<pver>7\.0\.\d+)$"

CVE_STATUS[CVE-2014-8180] = "not-applicable-config: Not affecting our configuration so it can be safely ignored."
CVE_STATUS[CVE-2017-2665] = "not-applicable-config: Not affecting our configuration so it can be safely ignored."
CVE_STATUS[CVE-2025-14911] = "cpe-incorrect: The vulnerability is about mongo-c-driver application, not mongodb"

COMPATIBLE_HOST ?= '(x86_64|aarch64|powerpc64le).*-linux'

PACKAGECONFIG ??= "tcmalloc system-pcre2"
PACKAGECONFIG:remove:libc-musl = "tcmalloc"

PACKAGECONFIG[tcmalloc] = "--use-system-tcmalloc,--allocator=system,gperftools,"
PACKAGECONFIG[js] = "--server-js=on,--server-js=off --js-engine=none,,"
PACKAGECONFIG[system-pcre2] = "--use-system-pcre2,,libpcre2,"

MONGO_ARCH ?= "${HOST_ARCH}"
MONGO_ARCH:powerpc64le = "ppc64le"

MONGO_LINKER ?= "bfd"
MONGO_LINKER:toolchain-clang = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-lld', 'lld', 'bfd', d)}"

MONGO_SCONS = "${PYTHON} ${S}/src/third_party/scons-4.9.1/scons.py"

# std::atomic<Timestamp> (8 bytes, 4-byte aligned) calls the generic __atomic_compare_exchange
LDFLAGS:append = " -Wl,--push-state,--no-as-needed -latomic -Wl,--pop-state"

EXTRA_OESCONS = "--variables-files=etc/scons/propagate_shell_environment.vars \
                 --variables-files=etc/scons/compilers_from_env.vars \
                 --variables-files=${UNPACKDIR}/oe-buildinfo.vars \
                 PREFIX=${prefix} \
                 DESTDIR=${B}/oe-install \
                 MAXLINELENGTH='2097152' \
                 LIBPATH=${STAGING_LIBDIR} \
                 AR='${AR}' \
                 CFLAGS='${CFLAGS}' \
                 CXXFLAGS='${CXXFLAGS}' \
                 LINKFLAGS='${LDFLAGS}' \
                 TARGET_ARCH=${MONGO_ARCH} \
                 MONGO_VERSION=${PV} \
                 MONGO_GIT_HASH=${SRCREV} \
                 SPLIT_DWARF=0 \
                 GDB_INDEX=0 \
                 --ssl=on \
                 --disable-warnings-as-errors \
                 --use-system-zlib \
                 --linker=${MONGO_LINKER} \
                 --endian=${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
                 --use-hardware-crc32=${@bb.utils.contains('TUNE_FEATURES', 'crc', 'on', 'off', d)} \
                 --wiredtiger=on \
                 ${PACKAGECONFIG_CONFARGS}"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --no-create-home --home-dir /var/run/${BPN} --shell /bin/false --user-group ${BPN}"

do_compile() {
    ${MONGO_SCONS} ${PARALLEL_MAKE} ${EXTRA_OESCONS} install-core ||
        die "scons build execution failed."
}

do_install() {
    # install binaries
    install -d ${D}${bindir}
    install -m 0755 ${B}/oe-install${bindir}/mongod ${B}/oe-install${bindir}/mongos ${D}${bindir}

    # install config
    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/debian/mongod.conf ${D}${sysconfdir}

    # install systemd service
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/debian/mongod.service ${D}${systemd_system_unitdir}

    # install mongo data folder
    install -m 755 -d ${D}${localstatedir}/lib/${BPN}
    chown ${BPN}:${BPN} ${D}${localstatedir}/lib/${BPN}

    # Create /var/log/mongodb in runtime.
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}" ]; then
        install -d ${D}${nonarch_libdir}/tmpfiles.d
        echo "d ${localstatedir}/log/${BPN} 0755 ${BPN} ${BPN} -" > ${D}${nonarch_libdir}/tmpfiles.d/${BPN}.conf
    fi
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'sysvinit', d)}" ]; then
        install -d ${D}${sysconfdir}/default/volatiles
        echo "d ${BPN} ${BPN} 0755 ${localstatedir}/log/${BPN} none" > ${D}${sysconfdir}/default/volatiles/99_${BPN}
    fi
}

CONFFILES:${PN} = "${sysconfdir}/mongod.conf"

SYSTEMD_SERVICE:${PN} = "mongod.service"

FILES:${PN} += "${nonarch_libdir}/tmpfiles.d"

RDEPENDS:${PN} += "tzdata-core"
