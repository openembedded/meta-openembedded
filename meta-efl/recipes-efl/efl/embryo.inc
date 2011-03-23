DESCRIPTION = "The Enlightenment C-like scripting language for Edje"
LICENSE = "MIT BSD"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

RREPLACES_${PN} = "libembryo-ver-pre-svn-00-0 libembryo-ver-pre-svn-01-0"

RREPLACES_${PN}-tests = "libembryo-ver-pre-svn-00-tests libembryo-ver-pre-svn-01-tests"

