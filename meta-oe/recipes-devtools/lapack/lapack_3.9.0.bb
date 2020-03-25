SUMMARY = "Linear Algebra PACKage"
URL = "http://www.netlib.org/lapack"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=930f8aa500a47c7dab0f8efb5a1c9a40"

# Recipe needs FORTRAN support (copied from conf/local.conf.sample.extended)
# Enabling FORTRAN
# Note this is not officially supported and is just illustrated here to
# show an example of how it can be done
# You'll also need your fortran recipe to depend on libgfortran
#FORTRAN_forcevariable = ",fortran"
#RUNTIMETARGET_append_pn-gcc-runtime = " libquadmath"

DEPENDS = "libgfortran"

SRC_URI = "https://github.com/Reference-LAPACK/lapack/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "0b251e2a8d5f949f99b50dd5e2200ee2"
SRC_URI[sha256sum] = "106087f1bb5f46afdfba7f569d0cbe23dacb9a07cd24733765a0e89dbe1ad573"

EXTRA_OECMAKE = " -DBUILD_SHARED_LIBS=ON "
OECMAKE_GENERATOR = "Unix Makefiles"

inherit cmake pkgconfig
EXCLUDE_FROM_WORLD = "1"
