SUMMARY = "A shared storage lock manager"
DESCRIPTION = "sanlock  is  a lock manager built on shared storage.  Hosts with access \
to the storage can perform locking.   An  application  running  on  the \
hosts  is  given  a small amount of space on the shared block device or \
file, and uses sanlock for its  own  application-specific  synchronization. \
Internally,  the  sanlock  daemon manages locks using two disk-based \
lease algorithms: delta leases and paxos leases."
HOMEPAGE = "https://pagure.io/sanlock"
SECTION = "utils"

LICENSE = "LGPLv2+ & GPLv2 & GPLv2+"
LIC_FILES_CHKSUM = "file://README.license;md5=60487bf0bf429d6b5aa72b6d37a0eb22"

PV .= "+git${SRCPV}"

SRC_URI = "git://pagure.io/sanlock.git;protocol=http;branch=master \
           file://0001-sanlock-Replace-cp-a-with-cp-R-no-dereference-preser.patch \
           file://setuptools.patch \
          "
SRCREV = "a181e951376d49a82eef17920c8ebedec80b4823"

S = "${WORKDIR}/git"

DEPENDS = "libaio util-linux"

inherit setuptools3 useradd

SETUPTOOLS_SETUP_PATH = "${S}/python"
PIP_INSTALL_DIST_PATH = "${B}/python/dist"
PIP_INSTALL_PACKAGE = "sanlock_python"
PYPA_WHEEL = "${PIP_INSTALL_DIST_PATH}/${PIP_INSTALL_PACKAGE}-3.8.4-*.whl"

do_compile:prepend () {
    oe_runmake -C ${S}/wdmd CMD_LDFLAGS="${LDFLAGS}" LIB_LDFLAGS="${LDFLAGS}"
    oe_runmake -C ${S}/src CMD_LDFLAGS="${LDFLAGS}" LIB_ENTIRE_LDFLAGS="${LDFLAGS}" LIB_CLIENT_LDFLAGS="${LDFLAGS}"
}

do_install:prepend () {
    oe_runmake -C ${S}/wdmd DESTDIR=${D} LIBDIR=${libdir} install
    oe_runmake -C ${S}/src DESTDIR=${D} LIBDIR=${libdir} install
}

SANLOCKGROUP ?= "sanlock"
SANLOCKUSER ?= "sanlock"
USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system ${SANLOCKGROUP}"
USERADD_PARAM:${PN} = "--system -g ${SANLOCKGROUP} -G disk \
                       --home-dir /run/${SANLOCKUSER} --no-create-home \
                       --shell /sbin/nologin ${SANLOCKUSER}"
