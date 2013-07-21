require ruby.inc
PR = "${INC_PR}.0"

SRC_URI += "\
    file://0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch \
    file://ruby-1.9.3-always-use-i386.patch \
    file://ruby-1.9.3-disable-versioned-paths.patch \
    file://ruby-1.9.3-fix-s390x-build.patch \
    file://ruby-1.9.3-rubygems-1.8.11-uninstaller.patch \
    file://ruby-1.9.3-webrick-test-fix.patch \
    file://ruby-1.9.3-bignum-test-fix.patch \
    file://ruby-1.9.3-custom-rubygems-location.patch \
    file://rubygems-1.8.11-binary-extensions.patch \
    file://ruby-1.9.3-mkmf-verbose.patch \
    file://ruby-1.9.3-install-cross.patch \
"

SRC_URI[md5sum] = "bc0c715c69da4d1d8bd57069c19f6c0e"
SRC_URI[sha256sum] = "46e2fa80be7efed51bd9cdc529d1fe22ebc7567ee0f91db4ab855438cf4bd8bb"

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
