inherit cross

DEPENDS = "virtual/${TARGET_PREFIX}binutils virtual/${TARGET_PREFIX}libc-for-gcc ${NATIVEDEPS}"
PROVIDES = "virtual/${TARGET_PREFIX}gcc virtual/${TARGET_PREFIX}g++"

require gcc-configure-cross.inc
require gcc-package-cross.inc

do_compile () {
	oe_runmake all-host all-target-libgcc
}

