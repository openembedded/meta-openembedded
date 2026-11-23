SUMMARY = "cbindgen creates C/C++11 headers for Rust libraries which expose a public C API"
HOMEPAGE = "https://github.com/mozilla/cbindgen"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI += "git://github.com/mozilla/cbindgen.git;protocol=https;branch=main;tag=v${PV}"
SRC_URI += "file://0001-Use-libc-SYS_futex_time64-on-riscv32.patch;patchdir=${UNPACKDIR}/cargo_home/bitbake/getrandom-0.3.3/"

SRCREV = "b826cb8911488fe8a209d2b693492c0c673e8cca"

UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

require cbindgen-crates.inc

inherit cargo cargo-update-recipe-crates pkgconfig

BBCLASSEXTEND += "native"
