SUMMARY = "Vi IMproved - enhanced vi editor"
SECTION = "console/utils"
DEPENDS = "ncurses gettext-native"
# vimdiff doesn't like busybox diff
RSUGGESTS_${PN} = "diffutils"
LICENSE = "vim"
LIC_FILES_CHKSUM = "file://../runtime/doc/uganda.txt;md5=b779e18be6ed77facc770691c967b8f8"

SRC_URI = "hg://vim.googlecode.com/hg/;protocol=https;module=vim \
           file://disable_acl_header_check.patch;patchdir=.. \
           file://vim-add-knob-whether-elf.h-are-checked.patch;patchdir=.. \
"
SRCREV = "v7-4-258"

S = "${WORKDIR}/${PN}/src"

VIMDIR = "${PN}${@d.getVar('PV',1).split('.')[0]}${@d.getVar('PV',1).split('.')[1]}"

inherit autotools update-alternatives
inherit autotools-brokensep

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

#Available PACKAGECONFIG options are gtkgui, acl, x11, tiny
PACKAGECONFIG ??= ""
PACKAGECONFIG += "${@base_contains('DISTRO_FEATURES', 'acl', 'acl', '', d)}"
PACKAGECONFIG += "${@base_contains('DISTRO_FEATURES', 'selinux', 'selinux', '', d)}"

PACKAGECONFIG[gtkgui] = "--enable-gtk2-test --enable-gui=gtk2,--enable-gui=no,gtk+,"
PACKAGECONFIG[acl] = "--enable-acl,--disable-acl,acl,"
PACKAGECONFIG[x11] = "--with-x,--without-x,xt,"
PACKAGECONFIG[tiny] = "--with-features=tiny,--with-features=big,,"
PACKAGECONFIG[selinux] = "--enable-selinux,--disable-selinux,libselinux,"
PACKAGECONFIG[elfutils] = "--enable-elf-check,,elfutils,"

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
    STRIP=/bin/true \
"

do_install_append() {
    # Work around rpm picking up csh or awk as a dep
    chmod -x ${D}${datadir}/${PN}/${VIMDIR}/tools/vim132
    chmod -x ${D}${datadir}/${PN}/${VIMDIR}/tools/mve.awk

    # Install example vimrc from runtime files
    install -m 0644 ../runtime/vimrc_example.vim ${D}/${datadir}/${PN}/vimrc
}

PARALLEL_MAKEINST = ""

PACKAGES =+ "${PN}-common ${PN}-syntax ${PN}-help ${PN}-tutor ${PN}-vimrc ${PN}-data"
FILES_${PN}-syntax = "${datadir}/${PN}/${VIMDIR}/syntax"
FILES_${PN}-help = "${datadir}/${PN}/${VIMDIR}/doc"
FILES_${PN}-tutor = "${datadir}/${PN}/${VIMDIR}/tutor ${bindir}/${PN}tutor"
FILES_${PN}-vimrc = "${datadir}/${PN}/vimrc"
FILES_${PN}-data = "${datadir}/${PN}"
FILES_${PN}-common = " \
    ${datadir}/${PN}/${VIMDIR}/*.vim \
    ${datadir}/${PN}/${VIMDIR}/autoload \
    ${datadir}/${PN}/${VIMDIR}/colors \
    ${datadir}/${PN}/${VIMDIR}/compiler \
    ${datadir}/${PN}/${VIMDIR}/ftplugin \
    ${datadir}/${PN}/${VIMDIR}/indent \
    ${datadir}/${PN}/${VIMDIR}/keymap \
    ${datadir}/${PN}/${VIMDIR}/lang \
    ${datadir}/${PN}/${VIMDIR}/macros \
    ${datadir}/${PN}/${VIMDIR}/plugin \
    ${datadir}/${PN}/${VIMDIR}/print \
    ${datadir}/${PN}/${VIMDIR}/spell \
    ${datadir}/${PN}/${VIMDIR}/tools \
"

# Recommend that runtime data is installed along with vim
RRECOMMENDS_${PN} = "${PN}-syntax ${PN}-help ${PN}-tutor ${PN}-vimrc ${PN}-data"

ALTERNATIVE_${PN} = "vi"
ALTERNATIVE_TARGET[vi] = "${bindir}/${PN}"
ALTERNATIVE_LINK_NAME[vi] = "${base_bindir}/vi"
ALTERNATIVE_PRIORITY[vi] = "100"
