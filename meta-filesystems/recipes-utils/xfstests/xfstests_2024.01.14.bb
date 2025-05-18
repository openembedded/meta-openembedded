SUMMARY = "File system QA test suite"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0;md5=74274e8a218423e49eefdea80bc55038"

SRCREV = "11914614784735c504f43b5b6baabaa713375984"
SRCREV_FORMAT = "xfstests_unionmount"

SRC_URI = "git://git.kernel.org/pub/scm/fs/xfs/xfstests-dev.git;branch=master;name=xfstests \
           git://github.com/amir73il/unionmount-testsuite.git;branch=master;protocol=https;name=unionmount;destsuffix=unionmount-testsuite \
           file://0002-Drop-detached_mounts_propagation-and-remove-sys-moun.patch \
           file://0001-add-missing-FTW_-macros-when-not-available-in-libc.patch \
           "

SRCREV_xfstests = "c46ca4d1f6c0c45f9a3ea18bc31ba5ae89e02c70"
SRCREV_unionmount = "e3825b16b46f4c4574a1a69909944c059835f914"

S = "${WORKDIR}/git"

# brokensep because m4/package_globals.m4 calls ". ./VERSION" (and that's not the only issue)
inherit autotools-brokensep useradd

DEPENDS += "xfsprogs acl"
RDEPENDS:${PN} += "\
    bash \
    bc \
    coreutils \
    e2fsprogs \
    e2fsprogs-tune2fs \
    e2fsprogs-resize2fs \
    libaio \
    libcap-bin \
    overlayfs-tools \
    perl \
    python3 \
    python3-core \
    xfsprogs \
    acl \
    gawk \
"

USERADD_PACKAGES = "${PN}"
# these users are necessary to run the tests
USERADD_PARAM:${PN} = "-U -m fsgqa; -N 123456-fsgqa; -N fsgqa2"

EXTRA_OECONF = "INSTALL_USER=root INSTALL_GROUP=root"

TARGET_CC_ARCH:append:libc-musl = " -D_LARGEFILE64_SOURCE"

do_configure:prepend() {
    # this is done by Makefile configure target, but we don't call it in do_configure
    cp -a ${S}/include/install-sh .
}

# Not sure if this is needed, but with old install-sh it was sometimes failing with:
# cp: cannot stat 'group.list': No such file or directory
# http://errors.yoctoproject.org/Errors/Details/752404/
# PARALLEL_MAKEINST = "-j1"

do_install:prepend() {
    # otherwise install-sh duplicates DESTDIR prefix
    export DIST_ROOT="/" DIST_MANIFEST="" DESTDIR="${D}"
    oe_runmake install

    unionmount_target_dir=${D}/usr/xfstests/unionmount-testsuite
    install -d ${D}/usr/xfstests/unionmount-testsuite/tests
    install -D ${WORKDIR}/unionmount-testsuite/tests/* -t $unionmount_target_dir/tests
    install ${WORKDIR}/unionmount-testsuite/*.py -t $unionmount_target_dir
    install ${WORKDIR}/unionmount-testsuite/run -t $unionmount_target_dir
    install ${WORKDIR}/unionmount-testsuite/README -t $unionmount_target_dir
}

FILES:${PN} += "\
    /usr/xfstests \
"
