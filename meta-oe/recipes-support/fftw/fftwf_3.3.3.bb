require fftw.inc

EXTRA_OECONF = "--disable-fortran --enable-single --enable-shared \
    --enable-threads \
    ${@base_contains('TUNE_FEATURES', 'neon', '--enable-neon', '', d)} \
"

SRC_URI[md5sum] = "0a05ca9c7b3bfddc8278e7c40791a1c2"
SRC_URI[sha256sum] = "85cdfc0a0ba10d8fa4f0f8e733aac1a5936c859832a9e3d5c0731fb5c54a97f3"

