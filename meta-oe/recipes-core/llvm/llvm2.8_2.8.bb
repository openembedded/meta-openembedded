require llvm.inc
require llvm2.inc

PR = "r3"

SRC_URI += " \
            file://30may-llvm2.8-pr399-ppc-arm.patch \
            file://0019-issue6065.patch \
            file://add-unistd.patch \
            file://llvm-mc_disable.patch \
"

EXTRA_OECMAKE += " -DBUILD_SHARED_LIBS:BOOL=OFF "

SRC_URI[md5sum] = "220d361b4d17051ff4bb21c64abe05ba"
SRC_URI[sha256sum] = "25addb742f1c6cc12877ed0ee924dda962d848368ee095be8e48342ae613d43b"
