require gnuradio.inc

PR = "${INC_PR}.1"
PV = "3.3.0-${PR}+gitr${SRCREV}"

SRCREV = "cdca1c917626f7c63f820da921a17187efc92cd5"

# Make it easy to test against developer repos and branches
GIT_REPO = "gnuradio.git"
GIT_BRANCH = "next"

SRC_URI = "git://gnuradio.org/git/${GIT_REPO};branch=${GIT_BRANCH};protocol=http \
"

S="${WORKDIR}/git"

