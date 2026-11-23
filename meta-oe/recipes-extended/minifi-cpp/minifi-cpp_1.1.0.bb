SUMMARY = "A subproject of Apache NiFi to collect data where it originates."
DESCRIPTION = "MiNiFi--a subproject of Apache NiFi--is a complementary \
data collection approach that supplements the core tenets of NiFi in dataflow \
management, focusing on the collection of data at the source of its creation."
HOMEPAGE = "https://nifi.apache.org/minifi/index.html"
SECTION = "console/network"
LICENSE = "Apache-2.0 AND BSD-2-Clause AND BSD-3-Clause AND BSL-1.0 AND ISC AND MIT AND Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=50c0151bf7c3999dcb7de9652ec6e3a6"

SRC_URI = "git://github.com/apache/nifi-minifi-cpp.git;protocol=https;branch=main;tag=rel/minifi-cpp-${PV} \
           git://github.com/ericniebler/range-v3.git;protocol=https;branch=master;tag=0.12.0;name=range-v3;destsuffix=${S}/thirdparty/range-v3-src \
           git://github.com/Neargye/magic_enum.git;protocol=https;branch=master;tag=v0.9.8;name=magic-enum;destsuffix=${S}/thirdparty/magic-enum-src \
           git://github.com/p-ranav/argparse.git;protocol=https;branch=master;tag=v3.2;name=argparse;destsuffix=${S}/thirdparty/argparse-src \
           git://github.com/gsl-lite/gsl-lite.git;protocol=https;branch=master;tag=v1.1.0;name=gsl-lite;destsuffix=${S}/thirdparty/gsl-lite-src \
           git://github.com/HowardHinnant/date.git;protocol=https;branch=master;tag=v3.0.4;name=date;destsuffix=${S}/thirdparty/date-src \
           git://github.com/chriskohlhoff/asio.git;protocol=https;branch=master;tag=asio-1-38-0;name=asio;destsuffix=${S}/thirdparty/asio-src \
           git://github.com/fmtlib/fmt.git;protocol=https;branch=main;tag=12.1.0;name=fmt;destsuffix=${S}/thirdparty/fmt-src \
           git://github.com/gabime/spdlog.git;protocol=https;branch=v1.x;tag=v1.17.0;name=spdlog;destsuffix=${S}/thirdparty/spdlog-src \
           git://github.com/danielaparker/jsoncons.git;protocol=https;branch=master;tag=v1.7.0;name=jsoncons;destsuffix=${S}/thirdparty/jsoncons-src \
           ${DEBIAN_MIRROR}/main/o/ossp-uuid/ossp-uuid_1.6.2.orig.tar.gz;name=ossp-uuid;subdir=${S}/thirdparty \
           https://download.libsodium.org/libsodium/releases/libsodium-1.0.22.tar.gz;name=libsodium;subdir=${S}/thirdparty \
           file://0001-Do-not-use-bundled-packages.patch \
           file://0002-Fix-osspuuid-build.patch \
           file://0003-Fix-libsodium-build.patch \
           file://0001-generateVersion.sh-set-BUILD_DATE-to-SOURCE_DATE_EPO.patch \
           file://0005-generateVersion.sh-set-correct-buildrev.patch \
           file://0004-Pass-noline-flag-to-flex.patch \
           file://0006-CMakeLists.txt-do-not-use-ccache.patch \
           file://systemd-volatile.conf \
           file://sysvinit-volatile.conf \
          "

# minifi-cpp: 1.1.0
SRCREV = "1a4b84eb8b53eebcf7c92d83f6ae818f440e33ee"
# The third-party revisions below are the versions pinned in cmake/*.cmake
# range-v3: 0.12.0
SRCREV_range-v3 = "a81477931a8aa2ad025c6bda0609f38e09e4d7ec"
# magic-enum: 0.9.8
SRCREV_magic-enum = "1384769c66bd16ec9bb1353f45fe8ec8ccc12dbd"
# argparse: 3.2
SRCREV_argparse = "3eda91b2e1ce7d569f84ba295507c4cd8fd96910"
# gsl-lite: 1.1.0
SRCREV_gsl-lite = "c767277c1cad4d4446ba30a13a48ed18ebf9754a"
# date: 3.0.4
SRCREV_date = "f94b8f36c6180be0021876c4a397a054fe50c6f2"
# asio: 1.38.0
SRCREV_asio = "03cf5f86a780dd102f1cdd3a59d1244d12143e46"
# fmt: 12.1.0
SRCREV_fmt = "407c905e45ad75fc29bf0f9bb7c5c2fd3475976f"
# spdlog: 1.17.0
SRCREV_spdlog = "79524ddd08a4ec981b7fea76afd08ee05f83755d"
# jsoncons: 1.7.0
SRCREV_jsoncons = "cb54cdc3134a62634466bf7bcd24f1a906f4ef25"

SRCREV_FORMAT .= "_range-v3_magic-enum_argparse_gsl-lite_date_asio_fmt_spdlog_jsoncons"

# ossp-uuid: 1.6.2
SRC_URI[ossp-uuid.sha256sum] = "11a615225baa5f8bb686824423f50e4427acd3f70d394765bdff32801f0fd5b0"
# libsodium: 1.0.22
SRC_URI[libsodium.sha256sum] = "adbdd8f16149e81ac6078a03aca6fc03b592b89ef7b5ed83841c086191be3349"

UPSTREAM_CHECK_GITTAGREGEX = "^rel/minifi-cpp-(?P<pver>\d+(\.\d+)+)$"

inherit pkgconfig cmake systemd

DEPENDS = "virtual/crypt bison-native flex-native flex openssl curl zlib xz bzip2 yaml-cpp pugixml"

OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"

CXXFLAGS:append:toolchain-clang = " -Wno-error=c++11-narrowing-const-reference"
LDFLAGS:append:riscv32 = " -latomic"

EXTRA_OECMAKE = " \
                 -DCMAKE_BUILD_TYPE=Release \
                 -DHOST_SYS=${HOST_SYS} -DBUILD_SYS=${BUILD_SYS} \
                 -DFLEX_TARGET_ARG_COMPILE_FLAGS='--noline' \
                 -DBISON_TARGET_ARG_COMPILE_FLAGS='--no-lines --file-prefix-map=${S}=${TARGET_DBGSRC_DIR} --file-prefix-map=${B}=${TARGET_DBGSRC_DIR}' \
                 -DENABLE_ENCRYPT_CONFIG=ON \
                 -DENABLE_LUA_SCRIPTING=OFF \
                 -DENABLE_PYTHON_SCRIPTING=OFF \
                 -DENABLE_AWS=OFF \
                 -DENABLE_AZURE=OFF \
                 -DENABLE_GCP=OFF \
                 -DENABLE_KUBERNETES=OFF \
                 -DENABLE_MQTT=OFF \
                 -DENABLE_GRAFANA_LOKI=OFF \
                 -DENABLE_GRPC_FOR_LOKI=OFF \
                 -DENABLE_ELASTICSEARCH=OFF \
                 -DENABLE_SQL=OFF \
                 -DENABLE_PROMETHEUS=OFF \
                 -DENABLE_PROCFS=OFF \
                 -DENABLE_SPLUNK=OFF \
                 -DENABLE_OPC=OFF \
                 -DENABLE_KAFKA=OFF \
                 -DENABLE_BZIP2=ON \
                 -DENABLE_LZMA=ON \
                 -DENABLE_COUCHBASE=OFF \
                 -DENABLE_LLAMACPP=OFF \
                 -DSKIP_TESTS=ON \
                 -DMINIFI_OPENSSL_SOURCE=SYSTEM \
                 -DMINIFI_LIBCURL_SOURCE=SYSTEM \
                 -DMINIFI_ZLIB_SOURCE=SYSTEM \
                 -DMINIFI_BZIP2_SOURCE=SYSTEM \
                 -DMINIFI_LIBLZMA_SOURCE=SYSTEM \
                 -DMINIFI_LIBARCHIVE_SOURCE=SYSTEM \
                 -DMINIFI_YAMLCPP_SOURCE=SYSTEM \
                 -DMINIFI_PUGIXML_SOURCE=SYSTEM \
                 -DMINIFI_FMT_SOURCE=BUILD \
                 -DMINIFI_SPDLOG_SOURCE=BUILD \
                 -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
                 -DFETCHCONTENT_SOURCE_DIR_GSL-LITE=${S}/thirdparty/gsl-lite-src \
                 -DFETCHCONTENT_SOURCE_DIR_DATE_SRC=${S}/thirdparty/date-src \
                 -DFETCHCONTENT_SOURCE_DIR_RANGE-V3_SRC=${S}/thirdparty/range-v3-src \
                 -DFETCHCONTENT_SOURCE_DIR_MAGIC_ENUM=${S}/thirdparty/magic-enum-src \
                 -DFETCHCONTENT_SOURCE_DIR_ASIO=${S}/thirdparty/asio-src \
                 -DFETCHCONTENT_SOURCE_DIR_ARGPARSE=${S}/thirdparty/argparse-src \
                 -DFETCHCONTENT_SOURCE_DIR_FMT=${S}/thirdparty/fmt-src \
                 -DFETCHCONTENT_SOURCE_DIR_SPDLOG=${S}/thirdparty/spdlog-src \
                 -DFETCHCONTENT_SOURCE_DIR_JSONCONS=${S}/thirdparty/jsoncons-src \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DENABLE_SYSTEMD=ON', '-DENABLE_SYSTEMD=OFF', d)} \
                 -DBUILD_IDENTIFIER=${MINIFI_BUILD_IDENTIFIER} \
                "

# Used in the extension registration function name, so it must be an identifier
MINIFI_BUILD_IDENTIFIER = "${@d.getVar('PV').replace('.', '_')}"

PACKAGECONFIG ??= "libarchive"

# rocksdb is not compatible with libc-musl:powerpc & armv5
PACKAGECONFIG:remove:libc-musl:powerpc = "rocksdb"
PACKAGECONFIG:remove:armv5 = "rocksdb"

PACKAGECONFIG[libarchive] = "-DENABLE_LIBARCHIVE=ON,-DENABLE_LIBARCHIVE=OFF,libarchive"
PACKAGECONFIG[civetweb] = "-DENABLE_CIVET=ON -DMINIFI_CIVETWEB_SOURCE=SYSTEM,-DENABLE_CIVET=OFF,civetweb"
PACKAGECONFIG[rocksdb] = "-DENABLE_ROCKSDB=ON -DBUILD_ROCKSDB=OFF,-DENABLE_ROCKSDB=OFF,rocksdb"

SYSTEMD_PACKAGES = "minifi-cpp"
SYSTEMD_SERVICE:${PN} = "minifi.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_install[cleandirs] += "${WORKDIR}/minifi-install"
PSEUDO_CONSIDER_PATHS .= ",${WORKDIR}/minifi-install"

do_compile:prepend() {
    # Remove build host references
    sed -i -e 's,--sysroot=${STAGING_DIR_TARGET},,g' \
        -e 's|${DEBUG_PREFIX_MAP}||g' \
        -e 's:${RECIPE_SYSROOT_NATIVE}::g' \
        ${B}/libminifi/agent_version.cpp
}

do_install() {
    DESTDIR='${WORKDIR}/minifi-install' cmake_runcmake_build --target ${OECMAKE_TARGET_INSTALL}
    MINIFI_BIN=${bindir}
    MINIFI_HOME=${sysconfdir}/minifi
    MINIFI_RUN=${localstatedir}/lib/minifi
    MINIFI_LOG=${localstatedir}/log/minifi

    install -m 755 -d ${D}${MINIFI_BIN}
    install -m 755 -d ${D}${MINIFI_HOME}/conf
    install -m 755 -d ${D}${localstatedir}/lib/minifi

    for i in minifi-encrypt-config minifi minifi.sh minifi-controller; do
        install -m 755 ${WORKDIR}/minifi-install/usr/bin/${i} ${D}${MINIFI_BIN}
    done
    for i in minifi-log.properties minifi.properties minifi-uid.properties; do
        install -m 644 ${WORKDIR}/minifi-install/usr/conf/${i} ${D}${MINIFI_HOME}/conf
    done

    install -m 755 -d ${D}${libdir}/minifi-extensions
    install -m 755 ${WORKDIR}/minifi-install/usr/bin/libcore-minifi.so ${D}${libdir}
    install -m 755 ${WORKDIR}/minifi-install/usr/extensions/*.so ${D}${libdir}/minifi-extensions

    install -m 755 -d ${D}${libexecdir}/minifi-python
    install -m 755 -d ${D}${libexecdir}/minifi-python-examples
    cp -rf ${WORKDIR}/minifi-install/usr/minifi-python/* ${D}${libexecdir}/minifi-python/
    cp -rf ${WORKDIR}/minifi-install/usr/minifi-python-examples/* ${D}${libexecdir}/minifi-python-examples/

    sed -i "s|MINIFI_HOME=.*|MINIFI_HOME=${MINIFI_HOME}|g" ${D}${MINIFI_BIN}/minifi.sh
    sed -i "s|bin_dir=.*|bin_dir=${MINIFI_BIN}|g" ${D}${MINIFI_BIN}/minifi.sh

    sed -i "s|#appender.rolling.directory=.*|appender.rolling.directory=${MINIFI_LOG}|g" \
        ${D}${MINIFI_HOME}/conf/minifi-log.properties
    sed -i "s|nifi.provenance.repository.directory.default=.*|nifi.provenance.repository.directory.default=${MINIFI_RUN}/provenance_repository|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties
    sed -i "s|nifi.flowfile.repository.directory.default=.*|nifi.flowfile.repository.directory.default=${MINIFI_RUN}/flowfile_repository|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties
    sed -i "s|nifi.database.content.repository.directory.default=.*|nifi.database.content.repository.directory.default=${MINIFI_RUN}/content_repository|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties
    sed -i "s|nifi.asset.directory=.*|nifi.asset.directory=${MINIFI_RUN}/asset|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties
    sed -i "s|nifi.python.virtualenv.directory=.*|nifi.python.virtualenv.directory=${MINIFI_RUN}/minifi-python-env|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties
    sed -i "s|nifi.flow.configuration.file=.*|nifi.flow.configuration.file=${MINIFI_HOME}/conf/config.yml|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties
    sed -i "s|nifi.python.processor.dir=.*|nifi.python.processor.dir=${libexecdir}/minifi-python|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties
    sed -i "s|nifi.extension.path=.*|nifi.extension.path=${libdir}/minifi-extensions/*|g" \
        ${D}${MINIFI_HOME}/conf/minifi.properties

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -m 755 -d ${D}${sysconfdir}/tmpfiles.d
        install -m 644 ${UNPACKDIR}/systemd-volatile.conf ${D}${sysconfdir}/tmpfiles.d/minifi.conf
        sed -i "s|@MINIFI_LOG@|${MINIFI_LOG}|g" ${D}${sysconfdir}/tmpfiles.d/minifi.conf

        install -m 755 -d ${D}${systemd_system_unitdir}
        install -m 644 ${WORKDIR}/minifi-install/usr/bin/minifi.service ${D}${systemd_system_unitdir}

        sed -i -e "s|^Environment=MINIFI_HOME=.*|Environment=MINIFI_HOME=${MINIFI_HOME}|g" ${D}${systemd_system_unitdir}/minifi.service
        sed -i -e "s|^ExecStart=.*|ExecStart=${MINIFI_BIN}/minifi|g" ${D}${systemd_system_unitdir}/minifi.service
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/default/volatiles
        install -m 0644 ${UNPACKDIR}/sysvinit-volatile.conf ${D}${sysconfdir}/default/volatiles/99_minifi

        sed -i "s|@MINIFI_LOG@|${MINIFI_LOG}|g" ${D}${sysconfdir}/default/volatiles/99_minifi
    fi
    
    for ss in $(find ${D}${libexecdir}/minifi-python-examples -type f); do
        sed -i 's,/usr/bin/env python$,/usr/bin/env python3,' "$ss"
    done

}

pkg_postinst:${PN}() {
    if [ -z "$D" ]; then
        if type systemd-tmpfiles >/dev/null; then
            systemd-tmpfiles --create
        elif [ -e ${sysconfdir}/init.d/populate-volatile.sh ]; then
            ${sysconfdir}/init.d/populate-volatile.sh update
        fi
    fi
}

FILES:${PN}-dev = ""
FILES:${PN} += "${libdir}/libcore-minifi.so \
                ${libdir}/minifi-extensions \
                ${libexecdir}/minifi-python \
                ${libexecdir}/minifi-python-examples \
               "

INSANE_SKIP:${PN} += "dev-deps"

CLEANBROKEN = "1"
