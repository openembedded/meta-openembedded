DESCRIPTION = "Vi IMproved - enhanced vi editor"
SECTION = "console/utils"
DEPENDS = "ncurses"
# vimdiff doesn't like busybox diff
RSUGGESTS_${PN} = "diffutils"
LICENSE = "vim"
LIC_FILES_CHKSUM = "file://README.txt;md5=72c4840d07b65659b60b3fa405c7da36"

PV_MAJOR = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"
VIMDIR = "${PN}${@d.getVar('PV',1).split('.')[0]}${@d.getVar('PV',1).split('.')[1]}"

INC_PR = "r13"

SRC_URI = " \
    ftp://ftp.${PN}.org/pub/${PN}/unix/${PN}-${PV_MAJOR}.tar.bz2;name=${VIMDIR} \
    ftp://ftp.${PN}.org/pub/${PN}/extra/${PN}-${PV_MAJOR}-extra.tar.gz;name=${VIMDIR}-extra \
    ftp://ftp.${PN}.org/pub/${PN}/extra/${PN}-${PV_MAJOR}-lang.tar.gz;name=${VIMDIR}-lang \
    file://vimrc \
"

S = "${WORKDIR}/${VIMDIR}/src"

inherit autotools update-alternatives

# vim configure.in contains functions which got 'dropped' by autotools.bbclass
do_configure () {
    rm -f auto/*
    touch auto/config.mk
    aclocal
    autoconf
    oe_runconf
    touch auto/configure
    touch auto/config.mk auto/config.h
}

#Available PACKAGECONFIG options are gtkgui, x11, tiny
PACKAGECONFIG ??= ""

PACKAGECONFIG[gtkgui] = "--enable-gtk2-test --enable-gui=gtk2,--enable-gui=no,gtk+,"
PACKAGECONFIG[x11] = "--with-x,--without-x,xt,"
PACKAGECONFIG[tiny] = "--with-features=tiny,--with-features=big,,"

EXTRA_OECONF = " \
    --disable-gpm \
    --disable-gtktest \
    --disable-xim \
    --disable-netbeans \
    --with-tlib=ncurses \
    ac_cv_small_wchar_t=no \
    vim_cv_getcwd_broken=no \
    vim_cv_memmove_handles_overlap=yes \
    vim_cv_stat_ignores_slash=no \
    vim_cv_terminfo=yes \
    vim_cv_tgent=non-zero \
    vim_cv_toupper_broken=no \
    vim_cv_tty_group=world \
"

# Work around rpm picking up csh as a dep
do_install_append() {
    chmod -x ${D}${datadir}/${PN}/${VIMDIR}/tools/vim132
    install -m 0644 ${WORKDIR}/vimrc ${D}/${datadir}/${PN}
}

PACKAGES =+ "${PN}-common ${PN}-syntax ${PN}-help ${PN}-tutor ${PN}-vimrc ${PN}-data"
FILES_${PN}-syntax = "${datadir}/${PN}/${VIMDIR}/syntax"
FILES_${PN}-help = "${datadir}/${PN}/${VIMDIR}/doc"
FILES_${PN}-tutor = "${datadir}/${PN}/${VIMDIR}/tutor ${bindir}/${PN}tutor"
FILES_${PN}-vimrc = "${datadir}/${PN}/vimrc"
FILES_${PN}-data = "${datadir}/${PN}"

ALTERNATIVE_${PN} = "vi"
ALTERNATIVE_TARGET[vi] = "${bindir}/${PN}"
ALTERNATIVE_LINK_NAME[vi] = "${base_bindir}/vi"
ALTERNATIVE_PRIORITY[vi] = "100"

PARALLEL_MAKE = ""
