SUMMARY ?= "Tree-sitter grammar for ${TS_LANG}"
HOMEPAGE ?= "https://tree-sitter.github.io/tree-sitter/"
SECTION = "devel"

TS_LANG ?= "${@d.getVar('BPN').replace('tree-sitter-', '', 1)}"
TS_PARSERS ?= "${TS_LANG}:."

DEPENDS = "libtreesitter"

do_configure[noexec] = "1"

do_compile() {
    for entry in ${TS_PARSERS}; do
        lang=${entry%%:*}
        dir=${S}/${entry#*:}
        ${CC} ${CFLAGS} -std=gnu11 -fPIC -I${dir}/src ${LDFLAGS} -shared \
            -Wl,-soname,libtree-sitter-${lang}.so \
            -o ${B}/libtree-sitter-${lang}.so ${dir}/src/*.c
    done
}

do_install() {
    install -d ${D}${libdir}/nvim/parser
    for entry in ${TS_PARSERS}; do
        lang=${entry%%:*}
        dir=${S}/${entry#*:}
        install -m 0755 ${B}/libtree-sitter-${lang}.so ${D}${libdir}/
        ln -sf ../../libtree-sitter-${lang}.so ${D}${libdir}/nvim/parser/${lang}.so
        queries=${dir}/queries
        [ ! -d ${queries}/${lang} ] || queries=${queries}/${lang}
        if [ -d ${queries} ]; then
            install -d ${D}${datadir}/tree-sitter/queries/${lang}
            install -m 0644 ${queries}/*.scm ${D}${datadir}/tree-sitter/queries/${lang}/
        fi
    done
}

FILES_SOLIBSDEV = ""
FILES:${PN} = " \
    ${libdir}/libtree-sitter-*.so \
    ${libdir}/nvim/parser \
    ${datadir}/tree-sitter \
"
INSANE_SKIP:${PN} = "dev-so"
