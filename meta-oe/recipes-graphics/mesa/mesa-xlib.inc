#not supported in oe-core base.bbclass
#FILESPATHPKG =. "mesa-${PV}:mesa:"
FILESPATH =. "${FILE_DIRNAME}/mesa:${FILE_DIRNAME}/mesa-${PV}:"

EXTRA_OECONF += " --with-driver=xlib"
