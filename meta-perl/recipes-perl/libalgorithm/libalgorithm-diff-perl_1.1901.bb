SUMMARY = "Algorithm::Diff - Compute 'intelligent' differences between two \
files/lists"
DESCRIPTION = "This is a module for computing the difference between two files, \
two strings, or any other two lists of things.  It uses an  intelligent \
algorithm similar to (or identical to) the one used by the Unix `diff' \
program.   It is guaranteed to find the *smallest possible* set of \
differences. \
"
SECTION = "libs"
HOMEPAGE = "https://metacpan.org/release/TYEMQ/Algorithm-Diff-1.1901"

LICENSE = "Artistic-1.0 | GPL-1.0-or-later"
LIC_FILES_CHKSUM = "file://lib/Algorithm/Diff.pm;beginline=1676;endline=1680;md5=f6b2fe8ca06ca6faefa4f265fc494c2c"

SRC_URI = "${CPAN_MIRROR}/authors/id/T/TY/TYEMQ/Algorithm-Diff-${PV}.zip"
SRC_URI[sha256sum] = "229a5708b0791675a8cd50ace77e79a323e52bfb4d34799dddfed644a2036662"

S = "${UNPACKDIR}/Algorithm-Diff-${PV}"

inherit cpan

BBCLASSEXTEND = "native"
