require glibmm.inc

PR = "r1"

SRC_URI[archive.md5sum] = "cf33d1861d09fb2952a6a1d69e0502e3"
SRC_URI[archive.sha256sum] = "7b67178363f8494c94f8b3dd704a4c8db7ad75a253fc84a4ad229e5e179ec192"

SRC_URI += " file://remove-examples.patch"


