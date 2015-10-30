# Class to inherit when you want to build with autotools after running bootstrap
inherit autotools

DEPENDS += "gnulib"

do_configure_prepend() {
    currdir=`pwd`
    cd ${S}

    # avoid bootstrap cloning gnulib on every configure
    cat >.gitmodules <<EOF
[submodule "gnulib"]
	path = gnulib
	url = git://git.sv.gnu.org/gnulib
EOF
    cp -rf ${STAGING_DATADIR}/gnulib ${S}

    # --force to avoid errors on reconfigure e.g if recipes changed we depend on
    # | bootstrap: running: libtoolize --quiet
    # | libtoolize:   error: 'libltdl/COPYING.LIB' exists: use '--force' to overwrite
    # | ...
    ./bootstrap --force
    cd $currdir
}
