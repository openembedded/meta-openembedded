require fftw.inc

SRC_URI = "http://www.fftw.org/fftw-${PV}.tar.gz"

EXTRA_OECONF = "--disable-fortran --enable-shared --enable-threads"

SRC_URI[md5sum] = "b616e5c91218cc778b5aa735fefb61ae"
SRC_URI[sha256sum] = "6aa7ae65ee49eb99004f15899f9bb77f54759122f1a350041e81e096157d768f"
