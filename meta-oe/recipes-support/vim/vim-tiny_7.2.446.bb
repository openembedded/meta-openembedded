VIMFEATURES = "tiny"

require vim_${PV}.bb

PR = "${INC_PR}.4"

RCONFLICTS_${PN} = "gvim vim"

PACKAGES =+ "${PN}-data"

FILES_${PN}-data = "${datadir}/vim"
