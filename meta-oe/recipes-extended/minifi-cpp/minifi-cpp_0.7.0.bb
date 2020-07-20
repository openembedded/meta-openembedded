SUMMARY = "A subproject of Apache NiFi to collect data where it originates."
DESCRIPTION = "MiNiFi--a subproject of Apache NiFi--is a complementary \
data collection approach that supplements the core tenets of NiFi in dataflow \
management, focusing on the collection of data at the source of its creation."
HOMEPAGE = "https://nifi.apache.org/minifi/index.html"
SECTION = "console/network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f9534eb5f4ab800b573a37bffc62f3a7"

DEPENDS = "libxcrypt bzip2 expat flex zlib python3"

SRCREV = "aa42957a2e227df41510047cece3cd606dc1cb6a"
SRC_URI = "git://github.com/apache/nifi-minifi-cpp.git \
           file://fix-build-issue-in-yocto.patch \
           file://fix-OSSPUUID-cross-compile.patch \
           file://minifi.service \
           "

S = "${WORKDIR}/git"

inherit pkgconfig cmake systemd

SYSTEMD_PACKAGES = "minifi-cpp"
SYSTEMD_SERVICE_${PN} = "minifi.service"

EXTRA_OECMAKE += " \
    -DHOST_SYS=${HOST_SYS} -DBUILD_SYS=${BUILD_SYS} \
    -DSKIP_TESTS=ON \
    "

OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"


do_install() {
    DESTDIR='${B}/minifi-install' cmake_runcmake_build --target ${OECMAKE_TARGET_INSTALL}

    CONF_DIR=${D}${base_prefix}/etc/minifi/conf
    install -d ${D}${base_prefix}/usr/bin
    install -d ${CONF_DIR}
    cp -a ${B}/minifi-install/usr/bin/*   ${D}${base_prefix}/usr/bin/
    cp -a ${B}/minifi-install/usr/conf/*  ${CONF_DIR}/
    sed -i 's|#appender.rolling.directory=.*|appender.rolling.directory=/var/log/minifi|g' \
        ${CONF_DIR}/minifi-log.properties
    sed -i 's|nifi.provenance.repository.directory.default=.*|nifi.provenance.repository.directory.default=/var/lib/minifi/provenance_repository|g' \
        ${CONF_DIR}/minifi.properties
    sed -i 's|nifi.flowfile.repository.directory.default=.*|nifi.flowfile.repository.directory.default=/var/lib/minifi/flowfile_repository|g' \
        ${CONF_DIR}/minifi.properties
    sed -i 's|nifi.database.content.repository.directory.default=.*|nifi.database.content.repository.directory.default=/var/lib/minifi/content_repository|g' \
        ${CONF_DIR}/minifi.properties
    sed -i 's|nifi.flow.configuration.file=.*|nifi.flow.configuration.file=/etc/minifi/conf/config.yml|g' \
        ${CONF_DIR}/minifi.properties

    install -m 0755 -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/minifi.service ${D}${systemd_unitdir}/system/
    sed -i -e 's|@BASE_BINDIR@|${base_bindir}|g' ${D}${systemd_unitdir}/system/minifi.service
    sed -i -e 's|@BINDIR@|${bindir}|g' ${D}${systemd_unitdir}/system/minifi.service
}

FILES_${PN} = " \
        /usr/bin/* \
        /etc/minifi/* \
        ${systemd_unitdir}/system/minifi.service \
        "
