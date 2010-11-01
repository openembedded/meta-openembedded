require xorg-proto-common.inc
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "a914ccc1de66ddeb4b611c6b0686e274"
SRC_URI[archive.sha256sum] = "06735a5b92b20759204e4751ecd6064a2ad8a6246bb65b3078b862a00def2537"

BBCLASSEXTEND = "native nativesdk"

CONFLICTS = "renderext"
