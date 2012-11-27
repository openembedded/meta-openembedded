require xl2tpd.inc
PR = "${INC_PR}.0"

# 95445fc9aaeaf709d54c1cd934d4bed4467e910d corresponds to 1.3.1 tag
SRCREV = "95445fc9aaeaf709d54c1cd934d4bed4467e910d"

SRC_URI += " file://cflags.patch"

