SUMMARY = "InfluxDB 3 Core time series database"
DESCRIPTION = "InfluxDB 3 Core is a time series database built on Apache Arrow, \
DataFusion and Parquet, with SQL and InfluxQL queries and an embedded Python \
processing engine."
HOMEPAGE = "https://www.influxdata.com/products/influxdb/"

# The prebuilt Python UDF component embeds CPython and its standard library
LICENSE = "PSF-2.0 AND (Apache-2.0 OR MIT)"
LIC_FILES_CHKSUM = "file://LICENSE-MIT;md5=4397d14116cc032eb68bf7b79ef60e79 \
                    file://LICENSE-APACHE;md5=1836efb2eb779966696f473ee8540542 \
                    file://${COMMON_LICENSE_DIR}/PSF-2.0;md5=76c1502273262a5ebefb50dfb20d7c4f"

SRC_URI = "\
    git://github.com/influxdata/influxdb.git;protocol=https;branch=3.12;tag=v${PV};name=influxdb \
    git://github.com/influxdata/arrow-datafusion.git;protocol=https;nobranch=1;name=datafusion;subdir=${GIT_DEPS_DIR}/arrow-datafusion;type=git-dependency \
    git://github.com/influxdata/arrow-rs-object-store.git;protocol=https;nobranch=1;name=objectstore;subdir=${GIT_DEPS_DIR}/arrow-rs-object-store;type=git-dependency \
    git://github.com/influxdata/datafusion-udf-wasm.git;protocol=https;nobranch=1;name=udfwasm;subdir=${GIT_DEPS_DIR}/datafusion-udf-wasm;type=git-dependency \
    ${UDF_WASM_URL}/sha256sum.txt;downloadfilename=datafusion-udf-wasm-${UDF_WASM_COMMIT}-sha256sum.txt;name=udf-sums;subdir=udf-wasm \
    ${UDF_WASM_URL}/${UDF_WASM_ELF};downloadfilename=datafusion-udf-wasm-${UDF_WASM_COMMIT}-${UDF_WASM_ELF};name=udf-${TARGET_ARCH};subdir=udf-wasm \
    file://0001-Use-pre-fetched-git-dependencies.patch \
    file://0002-iox_query_udf-Allow-using-pre-downloaded-UDF-artifac.patch \
    file://influxdb3.init \
    file://influxdb3.service \
    file://influxdb3.conf \
"
SRCREV_influxdb = "51f4ac865b8670e0d3555ae992639ec05ecc334e"
# Revisions pinned in Cargo.toml
SRCREV_datafusion = "676dd338f8fe8464a861b5c74ec3c4de8325d382"
SRCREV_objectstore = "36cca19484f8a7da8a7bbfb3410135791c8e929e"
SRCREV_udfwasm = "9186474d530904e787e8903b61f586c10e97e317"
SRCREV_FORMAT = "influxdb_datafusion_objectstore_udfwasm"

# Path used by 0001-Use-pre-fetched-git-dependencies.patch
GIT_DEPS_DIR = "influxdb-git-deps"

# Precompiled Python WASM UDF component, pinned in core/iox_query_udf/build.rs
UDF_WASM_COMMIT = "171fb4746619c95167162a89f289633e9891a3f9"
UDF_WASM_BUILD = "2026-08-24T15-37-49"
UDF_WASM_URL = "https://github.com/influxdata/datafusion-udf-wasm/releases/download/wasm-binaries/${UDF_WASM_BUILD}/${UDF_WASM_COMMIT}"
UDF_WASM_TARGET = "${TARGET_ARCH}-unknown-linux-gnu"
UDF_WASM_ELF = "datafusion_udf_wasm_python.release.${UDF_WASM_TARGET}.elf"
SRC_URI[udf-sums.sha256sum] = "a25fec27067332f63cd342e5a87967b0848d836124387a0d9f48f7b56fc99fd7"
SRC_URI[udf-aarch64.sha256sum] = "88b8daa5f2277d8fbbdaeb00ee759ae3bf3113c6143701d9c0576b0b9ce85eba"
SRC_URI[udf-x86_64.sha256sum] = "7f951ba7f55c0e6ecaa7ebffcf02debbb9e73eb5764f9d47db62c27b52eab299"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

require ${BPN}-crates.inc

inherit cargo cargo-update-recipe-crates pkgconfig python3-dir siteinfo systemd update-rc.d useradd

# The UDF component is only published for these architectures
COMPATIBLE_HOST = "(x86_64|aarch64)-.*-linux"

DEPENDS = "protobuf-native python3 sqlite3 sqlite3-native xz zstd"

export PROTOC = "${STAGING_BINDIR_NATIVE}/protoc"
export PROTOC_INCLUDE = "${STAGING_INCDIR_NATIVE}"
export GIT_HASH = "${SRCREV_influxdb}"
export IOX_QUERY_UDF_DOWNLOAD_DIR = "${B}/udf-wasm"
export IOX_QUERY_UDF_TARGET = "${UDF_WASM_TARGET}"
export PYO3_CROSS = "1"
export PYO3_CONFIG_FILE = "${WORKDIR}/pyo3.config"

RUSTFLAGS:append = " --cfg tokio_unstable"

do_configure:append() {
    cat > ${WORKDIR}/pyo3.config <<EOF
implementation=CPython
version=${PYTHON_BASEVERSION}
shared=true
abi3=false
lib_name=${PYTHON_DIR}
lib_dir=${STAGING_LIBDIR}
pointer_width=${SITEINFO_BITS}
build_flags=
suppress_build_script_link_lines=false
EOF

    rm -rf ${B}/udf-wasm
    install -d ${B}/udf-wasm
    install -m 0644 ${UNPACKDIR}/udf-wasm/datafusion-udf-wasm-${UDF_WASM_COMMIT}-sha256sum.txt ${B}/udf-wasm/sha256sum.txt
    install -m 0644 ${UNPACKDIR}/udf-wasm/datafusion-udf-wasm-${UDF_WASM_COMMIT}-${UDF_WASM_ELF} ${B}/udf-wasm/${UDF_WASM_ELF}
}

do_install:append() {
    install -d ${D}${sysconfdir}/influxdb3
    install -m 0640 ${UNPACKDIR}/influxdb3.conf ${D}${sysconfdir}/influxdb3/influxdb3.conf
    chown -R root:influxdb3 ${D}${sysconfdir}/influxdb3

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${UNPACKDIR}/influxdb3.init ${D}${sysconfdir}/init.d/influxdb3
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${UNPACKDIR}/influxdb3.service ${D}${systemd_system_unitdir}/influxdb3.service
    fi
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "influxdb3"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE:${PN} = "influxdb3.service"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system -d /var/lib/influxdb3 -m -s /bin/nologin influxdb3"

# The Python processing engine creates a venv and runs pip for plugins
RRECOMMENDS:${PN} = "python3-modules python3-pip python3-venv"

CVE_PRODUCT = "influxdata:influxdb"
CVE_STATUS[CVE-2019-10329] = "cpe-incorrect: Version does not match and only the Jenkins plugin is affected."
CVE_STATUS[CVE-2024-30896] = "not-applicable-config: vulnerability only exists in version 2.x"
