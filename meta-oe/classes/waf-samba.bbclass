# waf is a build system which is used by samba related project.
# Obtain details from https://wiki.samba.org/index.php/Waf
# 
inherit qemu pythonnative

DEPENDS += "qemu-native libxslt-native docbook-xsl-stylesheets-native python"

CONFIGUREOPTS = " --prefix=${prefix} \
                  --bindir=${bindir} \
                  --sbindir=${sbindir} \
                  --libexecdir=${libexecdir} \
                  --datadir=${datadir} \
                  --sysconfdir=${sysconfdir} \
                  --sharedstatedir=${sharedstatedir} \
                  --localstatedir=${localstatedir} \
                  --libdir=${libdir} \
                  --includedir=${includedir} \
                  --oldincludedir=${oldincludedir} \
                  --infodir=${infodir} \
                  --mandir=${mandir} \
                "

do_configure() {
    qemu_binary="${@qemu_target_binary(d)}"
    if [ "${qemu_binary}" = "qemu-allarch" ]; then
        qemu_binary="qemuwrapper"
    fi

    libdir_qemu="${STAGING_DIR_HOST}/${libdir}"
    base_libdir_qemu="${STAGING_DIR_HOST}/${base_libdir}"

    CROSS_EXEC="${qemu_binary} \
                ${QEMU_OPTIONS} \
                -L ${STAGING_DIR_HOST} \
                -E LD_LIBRARY_PATH=${libdir_qemu}:${base_libdir_qemu}"

    export BUILD_SYS=${BUILD_SYS}
    export HOST_SYS=${HOST_SYS}
    export BUILD_ARCH=${BUILD_ARCH}
    export HOST_ARCH=${HOST_ARCH}
    export STAGING_LIBDIR=${STAGING_LIBDIR}
    export STAGING_INCDIR=${STAGING_INCDIR}
    export PYTHONPATH=${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}

    ./configure ${CONFIGUREOPTS} ${EXTRA_OECONF} --cross-compile --cross-execute="${CROSS_EXEC}"
}

do_compile () {
    python ./buildtools/bin/waf ${PARALLEL_MAKE}
}

do_install() {
    oe_runmake install DESTDIR=${D}
}
