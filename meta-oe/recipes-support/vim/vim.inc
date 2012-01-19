DESCRIPTION = "Vi IMproved - enhanced vi editor"
SECTION = "console/utils"
DEPENDS = "ncurses"
# vimdiff doesn't like busybox diff
RSUGGESTS_${PN} = "diffutils"
LICENSE = "vim"
LIC_FILES_CHKSUM = "file://README.txt;md5=72c4840d07b65659b60b3fa405c7da36"

PV_MAJOR = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"
VIMDIR = "${@bb.data.getVar('PV',d,1).split('.')[0]}${@bb.data.getVar('PV',d,1).split('.')[1]}"

INC_PR = "r11"

SRC_URI = " \
  ftp://ftp.vim.org/pub/vim/unix/vim-${PV_MAJOR}.tar.bz2;name=vim${PV_MAJOR}tarbz2 \
  ftp://ftp.vim.org/pub/vim/extra/vim-${PV_MAJOR}-extra.tar.gz;name=vim${PV_MAJOR}extratargz \
  ftp://ftp.vim.org/pub/vim/extra/vim-${PV_MAJOR}-lang.tar.gz;name=vim${PV_MAJOR}langtargz \
"

S = "${WORKDIR}/vim${VIMDIR}/src"

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



VIMFEATURES ?= "big"
VIMX ?= "--without-x"
VIMGUI ?= "no"

EXTRA_OECONF = " \
  --enable-gui=${VIMGUI} \
  --with-features=${VIMFEATURES} \
  ${VIMX} \
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
	chmod -x ${D}${datadir}/vim/vim72/tools/vim132
}

PACKAGES =+ "vim-common vim-syntax vim-help vim-tutor"
FILES_vim-syntax = "${datadir}/vim/vim${VIMVER}/syntax"
FILES_vim-help = "${datadir}/vim/vim${VIMVER}/doc"
FILES_vim-tutor = "${datadir}/vim/vim${VIMVER}/tutor ${bindir}/vimtutor"

ALTERNATIVE_NAME = "vi"
ALTERNATIVE_PATH = "${bindir}/vim"
ALTERNATIVE_LINK = "${base_bindir}/vi"
ALTERNATIVE_PRIORITY = "100"

SRC_URI[vim7.2tarbz2.md5sum] = "f0901284b338e448bfd79ccca0041254"
SRC_URI[vim7.2tarbz2.sha256sum] = "914db0f2205ebd6f02878295ec2506036ea7500399db112c61a01491cd9a1d86"
SRC_URI[vim7.2langtargz.md5sum] = "d8884786979e0e520c112faf2e176f05"
SRC_URI[vim7.2langtargz.sha256sum] = "11607f539a4518b550bf1606b7d3a6f36c1ffdf566c058e7d94bced78034cd5b"
SRC_URI[vim7.2extratargz.md5sum] = "35e04482f07c57221c9a751aaa3b8dac"
SRC_URI[vim7.2extratargz.sha256sum] = "20894ac79672160bfc4f1d2f9f9775b34d944762d655307a91ca96d358faa04d"

PARALLEL_MAKE = ""
