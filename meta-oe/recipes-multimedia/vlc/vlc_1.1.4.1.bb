# This recipe packages vlc as a library as well, so qt4 dependencies
# can be avoided when ony the library is installed.
# Would be cool if when newer vlc is added to OE and older ones are phased
# out that could be made the default.

require vlc.inc

PR = "${INC_PR}.1"

SRC_URI[md5sum] = "ce17c335b38b322949694313173fcd49"
SRC_URI[sha256sum] = "61c9ea30a17ea40c6ccbfd507026e5c83ad9e0691f221d3667c8e49696d7c2aa"

