DESCRIPTION = "Eeze is a library to simplify the use of devices"
LICENSE = "MIT BSD"
DEPENDS = "ecore udev"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""
