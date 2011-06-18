require gnuradio.inc

PR = "${INC_PR}.0"
PV = "3.4.0-${PR}+gitr${SRCREV}"

SRCREV = "f53fef3d2bc39b2020b8bce3d88b43569dd16605"

# Make it easy to test against developer repos and branches
GIT_REPO = "gnuradio.git"
GIT_BRANCH = "next"

SRC_URI = "git://gnuradio.org/git/${GIT_REPO};branch=${GIT_BRANCH};protocol=http \
           file://0001-volk-Remove-all-traces-of-volk-from-configure-for-OE.patch \
           file://0001-buildsys-don-t-add-usr-include-and-usr-lib-to-config.patch \
          "

S="${WORKDIR}/git"

