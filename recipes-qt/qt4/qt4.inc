inherit qmake_base

DEPENDS += "qt4-tools-native freetype jpeg libpng zlib dbus openssl glib-2.0 gstreamer gst-plugins-base mysql5 postgresql sqlite sqlite3"

require qt4_arch.inc
QT_ARCH := "${@qt_arch(d)}"
QT_ENDIAN = "${@qt_endian(d)}"

QT_CONFIG_FLAGS += "-release -no-cups -no-accessibility -reduce-relocations \
                    -shared -no-nas-sound -no-sm -no-nis \
                    -qt-gif -system-libjpeg -system-libpng -system-zlib \
                    -no-sql-ibase -plugin-sql-mysql -no-sql-odbc -plugin-sql-psql -plugin-sql-sqlite -plugin-sql-sqlite2 \
                    -no-pch -qdbus -stl -glib -phonon -webkit"

EXTRA_OEMAKE = "-e"

EXTRA_ENV = 'QMAKE="${STAGING_BINDIR_NATIVE}/qmake2 -after \
             INCPATH+=${STAGING_INCDIR}/freetype2 LIBS+=-L${STAGING_LIBDIR}" \
             QMAKESPEC="${QMAKESPEC}" LINK="${CXX} -Wl,-rpath-link,${STAGING_LIBDIR}" \
             AR="${TARGET_PREFIX}ar cqs" \
             MOC="${STAGING_BINDIR_NATIVE}/moc4" UIC="${STAGING_BINDIR_NATIVE}/uic4" MAKE="make -e"'

export QT_CONF_PATH="${WORKDIR}/qt.conf"

# Library packages
QT_LIB_NAMES = "Qt3Support QtAssistantClient QtCLucene QtCore QtDBus QtDesigner QtDesignerComponents QtGui QtHelp QtNetwork QtOpenGL QtScript QtScriptTools QtSql QtSvg QtTest QtUiTools QtWebKit QtXml QtXmlPatterns phonon QtMultimedia QtOpenVG QtMediaServices QtDeclarative"

QT_EXTRA_LIBS = "pvrQWSWSEGL"

python __anonymous () {
    import bb

    lib_packages = []
    dev_packages = []
    dbg_packages = []
    for name in bb.data.getVar("QT_LIB_NAMES", d, 1).split():
        pkg = "${QT_BASE_LIB}" + name.lower().replace("qt", "").replace("_", "-") + "4"
        # NOTE: the headers for QtAssistantClient are different
        incname = name.replace("QtAssistantClient", "QtAssistant")
        bb.data.setVar("FILES_%s" % pkg, "${libdir}/lib%(name)s${QT_LIBINFIX}.so.*" % locals(), d)
        bb.data.setVar("FILES_%s-dev" % pkg, """${libdir}/lib%(name)s${QT_LIBINFIX}.prl
                  ${libdir}/lib%(name)s${QT_LIBINFIX}.a
                  ${libdir}/lib%(name)s${QT_LIBINFIX}.la
                  ${libdir}/lib%(name)s${QT_LIBINFIX}.so
                  ${includedir}/${QT_DIR_NAME}/%(incname)s
                  ${libdir}/pkgconfig/%(name)s${QT_LIBINFIX}.pc""" % locals(), d)
        bb.data.setVar("FILES_%s-dbg" % pkg, "${libdir}/.debug/lib%(name)s${QT_LIBINFIX}.so.*" % locals(), d)
        lib_packages.append(pkg)
        dev_packages.append("%s-dev" % pkg)
        dbg_packages.append("%s-dbg" % pkg)
    for name in bb.data.getVar("OTHER_PACKAGES", d, 1).split():
        dbg_packages.append("%s-dbg" % name)

    for name in bb.data.getVar("QT_EXTRA_LIBS", d, 1).split():
        pkg = "${QT_BASE_LIB}" + name.lower().replace("qt", "").replace("_", "-") + "4"
        bb.data.setVar("FILES_%s" % pkg, "${libdir}/lib%(name)s.so.*" % locals(), d)
        bb.data.setVar("FILES_%s-dev" % pkg, """${libdir}/lib%(name)s.prl
                  ${libdir}/lib%(name)s.a
                  ${libdir}/lib%(name)s.la
                  ${libdir}/lib%(name)s.so
                  ${includedir}/${QT_DIR_NAME}/%(incname)s
                  ${libdir}/pkgconfig/%(name)s.pc""" % locals(), d)
        bb.data.setVar("FILES_%s-dbg" % pkg, "${libdir}/.debug/lib%(name)s.so.*" % locals(), d)
        lib_packages.append(pkg)
        dev_packages.append("%s-dev" % pkg)
        dbg_packages.append("%s-dbg" % pkg)

    bb.data.setVar("LIB_PACKAGES", " ".join(lib_packages), d)
    bb.data.setVar("DEV_PACKAGES", " ".join(dev_packages), d)
    bb.data.setVar("DBG_PACKAGES", " ".join(dbg_packages), d)
}

OTHER_PACKAGES = "\
             ${QT_BASE_NAME}-tools \
             ${QT_BASE_NAME}-assistant \
             ${QT_BASE_NAME}-common \
             ${QT_BASE_NAME}-dbus \
             ${QT_BASE_NAME}-demos \
             ${QT_BASE_NAME}-designer \
             ${QT_BASE_NAME}-examples \
             ${QT_BASE_NAME}-fonts \
             ${QT_BASE_NAME}-fonts-ttf-vera \
             ${QT_BASE_NAME}-fonts-ttf-dejavu \
             ${QT_BASE_NAME}-fonts-pfa \
             ${QT_BASE_NAME}-fonts-pfb \
             ${QT_BASE_NAME}-fonts-qpf \
             ${QT_BASE_NAME}-linguist \
             ${QT_BASE_NAME}-makeqpf \
             ${QT_BASE_NAME}-mkspecs \
             ${QT_BASE_NAME}-pixeltool \
             ${QT_BASE_NAME}-qmlviewer \
             ${QT_BASE_NAME}-xmlpatterns \
             ${QT_BASE_NAME}-qt3to4"

PACKAGES += "${LIB_PACKAGES} ${DEV_PACKAGES} ${DBG_PACKAGES} ${OTHER_PACKAGES}"
PACKAGES_DYNAMIC = "${QT_BASE_NAME}-plugin-* ${QT_BASE_NAME}-translation-* ${QT_BASE_NAME}-fonts-*"

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${QT_BASE_NAME}-fonts = "1"
FILES_${PN}     = ""
FILES_${PN}-dev = "${includedir}/${QT_DIR_NAME}/Qt/*"
FILES_${PN}-dbg = ""
RRECOMMENDS_${PN} = "${LIB_PACKAGES} ${OTHER_PACKAGES}"
RRECOMMENDS_${PN}-dev = "${DEV_PACKAGES}"
RRECOMMENDS_${PN}-dbg = "${DBG_PACKAGES}"
RRECOMMENDS_${QT_BASE_NAME}-fonts = " \
             ${QT_BASE_NAME}-fonts-ttf-vera \
             ${QT_BASE_NAME}-fonts-ttf-dejavu \
             ${QT_BASE_NAME}-fonts-pfa \
             ${QT_BASE_NAME}-fonts-pfb \
             ${QT_BASE_NAME}-fonts-qpf"

FILES_${QT_BASE_NAME}-tools                = "${bindir}/uic* ${bindir}/moc ${bindir}/rcc ${bindir}/qttracereplay ${bindir}/qdoc*"
FILES_${QT_BASE_NAME}-tools-dbg            = "${bindir}/.debug/uic* ${bindir}/.debug/moc ${bindir}/.debug/rcc ${bindir}/.debug/qttracereplay ${bindir}/.debug/qdoc*"
FILES_${QT_BASE_NAME}-assistant            = "${bindir}/*assistant* ${bindir}/qcollectiongenerator ${bindir}/qhelpconverter ${bindir}/qhelpgenerator"
FILES_${QT_BASE_NAME}-assistant-dbg        = "${bindir}/.debug/*assistant* ${bindir}/.debug/qcollectiongenerator ${bindir}/.debug/qhelpconverter ${bindir}/.debug/qhelpgenerator"
FILES_${QT_BASE_NAME}-common               = "${bindir}/qtconfig"
FILES_${QT_BASE_NAME}-common-dbg           = "${bindir}/.debug/qtconfig"
FILES_${QT_BASE_NAME}-dbus                 = "${bindir}/qdbus ${bindir}/qdbusxml2cpp ${bindir}/qdbuscpp2xml ${bindir}/qdbusviewer"
FILES_${QT_BASE_NAME}-dbus-dbg             = "${bindir}/.debug/qdbus ${bindir}/.debug/qdbusxml2cpp ${bindir}/.debug/qdbuscpp2xml ${bindir}/.debug/qdbusviewer"
FILES_${QT_BASE_NAME}-demos                = "${bindir}/qtdemo* ${bindir}/${QT_DIR_NAME}/demos/*"
FILES_${QT_BASE_NAME}-demos-dbg            = "${bindir}/.debug/qtdemo* ${bindir}/${QT_DIR_NAME}/demos/.debug/*  ${bindir}/${QT_DIR_NAME}/demos/*/.debug  ${bindir}/${QT_DIR_NAME}/demos/*/*/.debug  ${bindir}/${QT_DIR_NAME}/demos/*/*/*/.debug"
FILES_${QT_BASE_NAME}-designer             = "${bindir}/*designer*"
FILES_${QT_BASE_NAME}-designer-dbg         = "${bindir}/.debug/*designer*"
FILES_${QT_BASE_NAME}-examples             = "${bindir}/${QT_DIR_NAME}/examples/*"
FILES_${QT_BASE_NAME}-examples-dbg         = "${bindir}/${QT_DIR_NAME}/examples/.debug ${bindir}/${QT_DIR_NAME}/examples/*/.debug ${bindir}/${QT_DIR_NAME}/examples/*/*/.debug ${bindir}/${QT_DIR_NAME}/examples/*/*/*/.debug ${bindir}/${QT_DIR_NAME}/examples/*/*/*/*/.debug ${bindir}/${QT_DIR_NAME}/examples/declarative/*/*/*/*/*/.debug/* ${bindir}/${QT_DIR_NAME}/examples/declarative/*/*/*/*/.debug/*"
FILES_${QT_BASE_NAME}-fonts-ttf-vera       = "${libdir}/fonts/Vera*.ttf"
FILES_${QT_BASE_NAME}-fonts-ttf-dejavu     = "${libdir}/fonts/DejaVu*.ttf"
FILES_${QT_BASE_NAME}-fonts-pfa            = "${libdir}/fonts/*.pfa"
FILES_${QT_BASE_NAME}-fonts-pfb            = "${libdir}/fonts/*.pfb"
FILES_${QT_BASE_NAME}-fonts-qpf            = "${libdir}/fonts/*.qpf"
FILES_${QT_BASE_NAME}-linguist             = "${bindir}/*linguist* ${bindir}/lrelease ${bindir}/lupdate ${bindir}/lconvert ${bindir}/qm2ts"
FILES_${QT_BASE_NAME}-linguist-dbg         = "${bindir}/.debug/*linguist* ${bindir}/.debug/lrelease ${bindir}/.debug/lupdate ${bindir}/.debug/lconvert ${bindir}/.debug/qm2ts"
FILES_${QT_BASE_NAME}-pixeltool            = "${bindir}/pixeltool"
FILES_${QT_BASE_NAME}-pixeltool-dbg        = "${bindir}/.debug/pixeltool"
FILES_${QT_BASE_NAME}-qt3to4               = "${bindir}/qt3to4 ${datadir}/${QT_DIR_NAME}/q3porting.xml"
FILES_${QT_BASE_NAME}-qt3to4-dbg           = "${bindir}/.debug/qt3to4"
FILES_${QT_BASE_NAME}-qmlviewer            = "${bindir}/qmlviewer"
FILES_${QT_BASE_NAME}-qmlviewer-dbg        = "${bindir}/.debug/qmlviewer"
FILES_${QT_BASE_NAME}-makeqpf              = "${bindir}/makeqpf"
FILES_${QT_BASE_NAME}-makeqpf-dbg          = "${bindir}/.debug/makeqpf"
FILES_${QT_BASE_NAME}-mkspecs              = "${datadir}/${QT_DIR_NAME}/mkspecs/*"
FILES_${QT_BASE_NAME}-xmlpatterns          = "${bindir}/xmlpatterns*"
FILES_${QT_BASE_NAME}-xmlpatterns-dbg      = "${bindir}/.debug/xmlpatterns*"


do_configure() {
    unset QMAKESPEC
    unset QTDIR

    if [ ! -e bin/qmake ]; then
        ln -sf ${STAGING_BINDIR_NATIVE}/qmake2 bin/qmake
    fi

    if [ ! -e mkspecs/${TARGET_OS}-oe-g++ ]; then
        ln -sf linux-g++ mkspecs/${TARGET_OS}-oe-g++
    fi

    cp -f ${WORKDIR}/g++.conf ${WORKDIR}/linux.conf mkspecs/common/

    echo "[Paths]"                                 > $QT_CONF_PATH
    echo "Prefix=${prefix}/"                      >> $QT_CONF_PATH
    echo "Documentation=${docdir}/${QT_DIR_NAME}" >> $QT_CONF_PATH
    echo "Headers=${includedir}/${QT_DIR_NAME}"   >> $QT_CONF_PATH
    echo "Libraries=${libdir}"                    >> $QT_CONF_PATH
    echo "Binaries=${bindir}"                     >> $QT_CONF_PATH
    echo "Plugins=${libdir}/${QT_DIR_NAME}/plugins" >> $QT_CONF_PATH
    echo "Data=${datadir}/${QT_DIR_NAME}"         >> $QT_CONF_PATH
    echo "Translations=${datadir}/${QT_DIR_NAME}/translations" >> $QT_CONF_PATH
    echo "Settings=${sysconfdir}/${QT_DIR_NAME}"  >> $QT_CONF_PATH
    echo "Examples=${bindir}/${QT_DIR_NAME}/examples" >> $QT_CONF_PATH
    echo "Demos=${bindir}/${QT_DIR_NAME}/demos"   >> $QT_CONF_PATH

    ${EXTRA_QMAKE_MUNGE}|| true

    (echo o; echo yes) | ./configure -v \
            -prefix ${prefix}/ \
            -bindir ${bindir} \
            -libdir ${libdir} \
            -datadir ${datadir}/${QT_DIR_NAME} \
            -sysconfdir ${sysconfdir}/${QT_DIR_NAME} \
            -docdir ${docdir}/${QT_DIR_NAME} \
            -headerdir ${includedir}/${QT_DIR_NAME} \
            -plugindir ${libdir}/${QT_DIR_NAME}/plugins \
            -translationdir ${datadir}/${QT_DIR_NAME}/translations \
            -examplesdir ${bindir}/${QT_DIR_NAME}/examples \
            -demosdir ${bindir}/${QT_DIR_NAME}/demos \
            -platform ${TARGET_OS}-oe-g++ \
            -xplatform ${TARGET_OS}-oe-g++ \
            -embedded ${QT_ARCH} ${QT_ENDIAN} \
            -crossarch ${QT_ARCH} \
            ${QT_CONFIG_FLAGS} -no-fast \
            -L${STAGING_LIBDIR} -I${STAGING_INCDIR} \
            -I${STAGING_INCDIR}/freetype2 \
            -I${STAGING_INCDIR}/mysql \
            -I${STAGING_INCDIR}/postgresql
}

do_compile() {
    unset CFLAGS CXXFLAGS
    install -m 0755 ${STAGING_BINDIR_NATIVE}/rcc4 ${S}/bin/rcc
    install -m 0755 ${STAGING_BINDIR_NATIVE}/moc4 ${S}/bin/moc
    install -m 0755 ${STAGING_BINDIR_NATIVE}/uic4 ${S}/bin/uic

    oe_runmake ${EXTRA_ENV}
}

python populate_packages_prepend() {
        translation_dir = bb.data.expand('${datadir}/${QT_DIR_NAME}/translations/', d)
        translation_name = bb.data.expand('${QT_BASE_NAME}-translation-%s', d)
        do_split_packages(d, translation_dir, '^(assistant|designer|linguist|qt|qtconfig|qvfb)_(.*)\.qm$', translation_name, '${PN} translation for %s', extra_depends='' )
 
        phrasebook_dir = bb.data.expand('${datadir}/${QT_DIR_NAME}/phrasebooks/', d)
        phrasebook_name = bb.data.expand('${QT_BASE_NAME}-phrasebook-%s', d)
        do_split_packages(d, phrasebook_dir, '^(.*)\.qph$', phrasebook_name, '${PN} phrasebook for %s', extra_depends='' )
 
        # Package all the plugins and their -dbg version and create a meta package
        import os
        def qtopia_split(path, name, glob):
                """
                Split the package into a normal and -dbg package and then add the
                new packages to the meta package.
                """
                plugin_dir = bb.data.expand('${libdir}/${QT_DIR_NAME}/plugins/%s/' % path, d)
                if not os.path.exists("%s%s" % (bb.data.expand('${D}',d), plugin_dir)):
                        bb.note("The path does not exist:", bb.data.expand('${D}', d), plugin_dir)
                        return
 
                plugin_name = bb.data.expand('${QT_BASE_NAME}-plugin-%s-%%s' % name, d)
                dev_packages = []
                dev_hook = lambda file,pkg,b,c,d:dev_packages.append((file,pkg))
                do_split_packages(d, plugin_dir, glob, plugin_name, '${PN} %s for %%s' % name, extra_depends='', hook=dev_hook)
                # Create a -dbg package as well
                plugin_dir_dbg = bb.data.expand('${libdir}/${QT_DIR_NAME}/plugins/%s/.debug' % path, d)
                packages = bb.data.getVar('PACKAGES',d)
                for (file,package) in dev_packages:
                        packages = "%s %s-dbg" % (packages, package)
                        file_name = os.path.join(plugin_dir_dbg, os.path.basename(file))
                        bb.data.setVar("FILES_%s-dbg" % package, file_name, d)
                        bb.data.setVar("DESCRIPTION_%s-dbg" % package, "${PN} %s for %s" % (name, package), d)

                bb.data.setVar('PACKAGES', packages, d)
 
        qtopia_split('accessible',    'accessible',    '^libq(.*)\.so$')
        qtopia_split('codecs',        'codec',         '^libq(.*)\.so$')
        qtopia_split('decorations',   'decoration',    '^libqdecoration(.*)\.so$')
        qtopia_split('designer',      'designer',      '^lib(.*)\.so$')
        qtopia_split('gfxdrivers',    'gfxdriver',     '^libq(.*)\.so$')
        qtopia_split('graphicssystems','graphicssystems', '^libq(.*)\.so$')
        qtopia_split('mousedrivers',  'mousedriver',   '^libq(.*)mousedriver\.so$')
        qtopia_split('iconengines',   'iconengine',    '^libq(.*)\.so$')
        qtopia_split('imageformats',  'imageformat',   '^libq(.*)\.so$')
        qtopia_split('inputmethods',  'inputmethod',   '^libq(.*)\.so$')
        qtopia_split('sqldrivers',    'sqldriver',     '^libq(.*)\.so$')
        qtopia_split('script',        'script',        '^libqtscript(.*)\.so$')
        qtopia_split('styles',        'style',         '^libq(.*)\.so$')
        qtopia_split('phonon_backend','phonon-backend','^libphonon_(.*)\.so$')
        qtopia_split('bearer',        'bearer',        '^libq(.*)bearer\.so$')
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
	
    # These are host binaries, we should only use them in staging
    rm -rf ${D}/${bindir}/qmake

    # fix pkgconfig, libtool and prl files
    sed -i -e s#-L${S}/lib##g \
           -e s#-L${STAGING_LIBDIR}##g \
           -e 's#STAGING_LIBDIR}#libdir}'#g \ 
           -e s#-L${libdir}##g \
           -e s#'$(OE_QMAKE_LIBS_X11)'#"${OE_QMAKE_LIBS_X11}"#g \
           -e s#" -Wl,-rpath-link,${S}/lib"##g \
           -e s#" -Wl,-rpath-link,${libdir}"##g \
           -e 's#I/usr/include#Iincludedir}#g' \ 
           -e 's#Iin#I${in#g' \
           ${D}${libdir}/*.la ${D}${libdir}/*.prl ${D}${libdir}/pkgconfig/*.pc

    sed -i -e s#" -Wl,-rpath-link,${S}/lib"##g \
            ${D}${datadir}/${QT_DIR_NAME}/mkspecs/common/linux.conf

    # fix pkgconfig files
    sed -i -e s#"moc_location=.*$"#"moc_location=${bindir}/moc4"# \
           -e s#"uic_location=.*$"#"uic_location=${bindir}/uic4"# \
           ${D}${libdir}/pkgconfig/*.pc
    for name in ${QT_LIB_NAMES}; do
           sed -i -e /Requires/s#"${name}"#"${name}${QT_LIBINFIX}"#g ${D}${libdir}/pkgconfig/*.pc
    done

	# QT abuses $includedir to point to its headers, which breaks pkgconfig sysroot, so manually fix it up here:
	for pc in ${D}${libdir}/pkgconfig/*.pc ; do
		sed -i -e "s:prefix}include/${QT_BASE_NAME}/$(basename $pc .pc):prefix}/include:" \
		       -e "s,Cflags: ,Cflags: -IP{includedir}/${QT_BASE_NAME}/$(basename $pc .pc) ," \
		       -e 's:IP{:I${:g' $pc
	done

    install -d ${D}/${libdir}/fonts
    touch ${D}/${libdir}/fonts/fontdir
}


