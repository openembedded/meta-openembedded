PRINC = "1"

# Build against external libav instead of using the builtin one.
# On architectures like ARM the libav recipe enables a lot more optimizations
DEPENDS += "libav orc"
EXTRA_OECONF += " --with-system-ffmpeg "
