DESCRIPTION = "Maintenance tools for OverlayFS"
HOMEPAGE = "https://github.com/kmxz/overlayfs-tools"
LICENSE = "WTFPL"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f312a7c4d02230e8f2b537295d375c69"

SRC_URI = "\
    git://github.com/kmxz/overlayfs-tools.git;protocol=https;branch=master;tag=v${PV} \
    file://run-ptest \
"

SRCREV = "42582352e11b162895c06826f18a2246d8005ca9"

UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

inherit meson pkgconfig ptest

DEPENDS:append:libc-musl = " fts"

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/test_cases
    install -m 0755 ${S}/test_cases/run-tests.sh ${D}${PTEST_PATH}/test_cases/
    for f in lower.tar upper.tar diff.saved verbose.saved brief.saved; do
        install -m 0644 ${S}/test_cases/$f ${D}${PTEST_PATH}/test_cases/
    done
}

# "overlay merge" generates a script that needs GNU coreutils options
RDEPENDS:${PN}-ptest += "attr coreutils diffutils tar"
RRECOMMENDS:${PN}-ptest += "kernel-module-overlay"
