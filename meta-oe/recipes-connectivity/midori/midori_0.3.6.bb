require midori.inc

PR = "r1"

SRC_URI = "http://archive.xfce.org/src/apps/midori/0.3/midori-${PV}.tar.bz2;name=midori \
           file://speeddial.patch \
          "
SRC_URI[midori.md5sum] = "c4cb0686601b1c470c317de3d3f8e8fd"
SRC_URI[midori.sha256sum] = "5fb290ffde81e5e6b39a54d286f5732496bfda10ff65019189cc6bf8408f2410


