require ruby.inc
PR = "${INC_PR}.0"

SRC_URI += "\
    file://0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch \
    file://ruby-1.9.3-always-use-i386.patch \
    file://ruby-1.9.3-disable-versioned-paths.patch \
    file://ruby-1.9.3-fix-s390x-build.patch \
    file://ruby-1.9.3-rubygems-1.8.11-uninstaller.patch \
    file://ruby-1.9.3-custom-rubygems-location.patch \
    file://rubygems-1.8.11-binary-extensions.patch \
    file://ruby-1.9.3-mkmf-verbose.patch \
    file://ruby-1.9.3-install-cross.patch \
"
SRC_URI[md5sum] = "993c72f7f805a9eb453f90b0b7fe0d2b"
SRC_URI[sha256sum] = "d192d1afc46a7ef27b9d0a3c7a67b509048984db2c38907aa82641bdf980acf4"

EXTRA_OECONF = "\
    --enable-wide-getaddrinfo \
    --with-rubygemsdir=${datadir}/rubygems \
    --disable-versioned-paths \
    --disable-rpath \
    --enable-shared \
"

EXTRA_OEMAKE = " \
    LIBRUBYARG='-lruby-static' \
"

do_install() {
    if [ ${PN} = "ruby" ]; then
        oe_runmake 'DESTDIR=${D}' install install-cross
    else
        oe_runmake 'DESTDIR=${D}' install
    fi
}

FILES_${PN} += "${datadir}/rubygems \
                ${datadir}/ri"

FILES_${PN}-dbg += "${libdir}/ruby/*/.debug \
                    ${libdir}/ruby/*/*/.debug \
                    ${libdir}/ruby/*/*/*/.debug"

BBCLASSEXTEND = "native"
