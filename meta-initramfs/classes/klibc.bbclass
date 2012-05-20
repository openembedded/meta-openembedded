# klcc-cross depends on klibc
DEPENDS =+ "klcc-cross"

export CC = "${TARGET_PREFIX}klcc"
export CPP = "${CC} -E"

# klcc uses own optimizations by default. See klcc(1) man file.
export CFLAGS=""
export CPPFLAGS=""
export LDFLAGS=""
