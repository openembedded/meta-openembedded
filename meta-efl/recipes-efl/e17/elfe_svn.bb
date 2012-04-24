LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=344895f253c32f38e182dcaf30fe8a35"
PV = "0.0.1+svnr${SRCPV}"
PR = "${INC_PR}.0"

require e-module.inc

SRCREV = "${EFL_SRCREV}"

DEPENDS += "elementary"
