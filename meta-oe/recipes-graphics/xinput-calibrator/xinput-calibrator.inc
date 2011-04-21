DESCRIPTION = "A generic touchscreen calibration program for X.Org"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/xinput_calibrator"
LICENSE = "MIT/X11"
DEPENDS = "virtual/libx11 libxi"

INC_PR = "r8"

inherit autotools

RDEPENDS_${PN} = "xinput"
RRECOMMENDS_${PN} = "pointercal-xinput"

