DESCRIPTION = "A generic touchscreen calibration program for X.Org"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/xinput_calibrator"
LICENSE = "MIT/X11"
DEPENDS = "virtual/libx11 libxi"
RDEPENDS_${PN} = "xinput pointercal-xinput"
INC_PR = "r7"

inherit autotools
