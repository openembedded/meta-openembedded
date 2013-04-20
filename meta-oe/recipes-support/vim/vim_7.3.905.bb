require ${PN}.inc

PR = "${INC_PR}.0"

SRCREV = "v7-3-905"

SRC_URI += "file://configure.in_remove_CC_quotes.patch;patchdir=.."
