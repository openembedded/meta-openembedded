VIMGUI = "gtk2"
VIMX = "--with-x"

require vim_${PV}.bb

PR = "${INC_PR}.4"

DEPENDS += "gtk+ xt"

FILES_${PN} += "${datadir}/vim"

EXTRA_OECONF += "--enable-gtk2-test"

RREPLACES_${PN} = "vim vim-tiny"
