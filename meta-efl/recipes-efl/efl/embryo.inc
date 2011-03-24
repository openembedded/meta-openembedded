DESCRIPTION = "The Enlightenment C-like scripting language for Edje"
LICENSE = "MIT BSD & CompuPhase"
LIC_FILES_CHKSUM = "file://COPYING;md5=6aa45e26c1ed178da910fd76b306a875"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

RREPLACES_${PN} = "libembryo-ver-pre-svn-00-0 libembryo-ver-pre-svn-01-0"

RREPLACES_${PN}-tests = "libembryo-ver-pre-svn-00-tests libembryo-ver-pre-svn-01-tests"

