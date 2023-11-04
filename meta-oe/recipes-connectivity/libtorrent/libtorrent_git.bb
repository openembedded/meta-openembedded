DESCRIPTION = "libTorrent is a BitTorrent library written in C++ for *nix, \
with a focus on high performance and good code."
HOMEPAGE = "http://libtorrent.rakshasa.no/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

DEPENDS = "zlib libsigc++-2.0 openssl cppunit"

SRC_URI = "git://github.com/rakshasa/libtorrent;branch=master;protocol=https \
           file://0001-Fix-compilation-issue-with-gcc-v6.x-and-empty-CXXFLA.patch \
           file://0002-Modfiy-gcc-v6.x-fix-for-empty-CXXFLAGS-See-10.patch \
           file://0003-Add-space-to-fmt-str-in-log_gz_file_write.patch \
           file://0004-IPv4-filter-enhancement-11IPv4-filter-enhancement-Cl.patch \
           file://0005-Disable-extents-test-to-pass-TravisCI-See-11.patch \
           file://0006-Bumped-version-to-0.13.7.patch \
           file://0007-Added-support-for-openssl-1.1.patch \
           file://0008-Use-AC_COMPILE-instead-of-AC_RUN-to-check-for-execin.patch \
           file://0009-Modify-configure-to-prevent-unnecessary-kqueue-check.patch \
           file://0010-Display-info-on-failed-tracker-bencode-parsing-See-9.patch \
           file://0011-Strip-tags-also-when-displaying-info-on-failed-track.patch \
           file://0012-Switch-to-C-11-MRT-RNG-for-random-bytes.patch \
           file://0013-Prevent-loss-of-m_ipv6_socket-attribute-which-led-to.patch \
           file://0014-If-during-socket-creation-AF_INET6-failes-initialize.patch \
           file://0015-Fixes-https-github.com-rakshasa-rtorrent-issues-731.patch \
           file://0016-Fix-honoring-throttle.min_peers-settings-in-rtorrent.patch \
           file://0017-increase-piece-length-max.patch \
           file://0018-Set-max-piece-size-512mb.patch \
           file://0019-Fixed-compiler-warning.patch \
           file://0020-Added-_GNU_SOURCE-to-fallocate-test.-neheb.patch \
           file://0021-Fixed-diffie-hellman-implementation.patch \
           file://0022-Increased-max-timeout-for-tracker-requests.patch \
           file://0023-Close-log-files-when-reusing-a-name.patch \
           file://0024-Bumped-to-version-0.13.8.patch \
           file://0025-Allow-logs-to-be-appended-rather-than-overwritten.patch \
           file://0026-Removed-log-append-function.-Added-append-parameter-.patch \
           file://0027-Backport-changes-from-feature-bind.-200.patch \
           file://0028-libtorrent.pc.in-add-Libs.Private-202.patch \
           file://0029-Fix-for-inotify-missing-quickly-renamed-files-203.patch \
           file://0030-Fix-compiler-warnings.-204.patch \
           file://0031-Fix-log-format-so-GCC-can-check-it.-205.patch \
           file://0032-Consolidate-make-script-to-optimize-build.-206.patch \
           file://0033-Refactor-make-process.-207.patch \
           file://0034-Changes-automake-required-files.patch \
           file://0035-Replaced-custom-execinfo-autoconf-test.patch \
           file://0036-Added-option-to-disable-pthread_setname_np.patch \
           file://0037-Improved-backtrace-error-checking.patch \
           file://0038-Fixed-issue-with-multiple-connections-from-NAT-not-w.patch \
           file://0039-Added-disable-execinfo-option-to-configure.patch \
           file://0040-Detect-ip-address.patch \
           file://0041-Added-ipv6-options.patch \
           file://0042-Removed-obsolete-files.patch \
           file://0043-Updated-and-cleaned-up-automake.-224.patch \
           file://0044-Create-FUNDING.yml.patch \
           "
SRCREV = "c167c5a9e0bcf0df23ae5efd91396aae0e37eb87"

CVE_STATUS[CVE-2009-1760] = "backported-patch: patched in our product"

PV = "1"

S = "${WORKDIR}/git"


PACKAGECONFIG ??= "instrumentation aligned"

PACKAGECONFIG:remove:mipsarch = "instrumentation"
PACKAGECONFIG:remove:powerpc = "instrumentation"
PACKAGECONFIG:remove:riscv32 = "instrumentation"

PACKAGECONFIG[instrumentation] = "--enable-instrumentation,--disable-instrumentation,"
PACKAGECONFIG[aligned] = "--enable-aligned,--disable-aligned,"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-zlib=${STAGING_EXECPREFIXDIR}"
