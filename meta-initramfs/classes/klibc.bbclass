# klcc-cross depends on klibc
DEPENDS =+ "klcc-cross"

# Default for klcc is to build static binaries.
# Set CC = "${TARGET_PREFIX}klcc -shared" to build the dynamic version.
CC_forcevariable = "${TARGET_PREFIX}klcc ${TOOLCHAIN_OPTIONS}"
CC_forcevariable_armv4_linux-gnueabi = "${TARGET_PREFIX}klcc ${TOOLCHAIN_OPTIONS} -march=armv4 -mthumb-interwork"

CPP_forcevariable = "${CC} -E"

# klcc uses own optimizations by default. See klcc(1) man file.
export CFLAGS=""
export CPPFLAGS=""
export LDFLAGS=""
