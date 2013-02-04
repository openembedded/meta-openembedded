
update_fonts_postinst() {
    if [ -z "$D" ]; then
        update-fonts
    fi
}

# the same but keep it separate for now
update_fonts_postrm() {
    if [ -z "$D" ]; then
        update-fonts
    fi
}

python populate_packages_append () {
    update_fonts_pkgs = d.getVar('UPDATE_FONTS_PACKAGES', True).split()

    for pkg in update_fonts_pkgs:
        postinst = d.getVar('pkg_postinst_%s' % pkg, True) or d.getVar('pkg_postinst', True)
        if not postinst:
            postinst = '#!/bin/sh\n'
        postinst += d.getVar('update_fonts_postinst', True)
        d.setVar('pkg_postinst_%s' % pkg, postinst)

        postrm = d.getVar('pkg_postrm_%s' % pkg, True) or d.getVar('pkg_postrm', True)
        if not postrm:
            postrm = '#!/bin/sh\n'
        postrm += d.getVar('update_fonts_postrm', True)
        d.setVar('pkg_postrm_%s' % pkg, postrm)

        d.appendVar('RDEPENDS_%s' % pkg, ' font-update-common')
}

python __anonymous() {
    if not bb.data.inherits_class('native', d) and not bb.data.inherits_class('cross', d):
        updatefonts_check = d.getVar('UPDATE_FONTS_PACKAGES')
        if not updatefonts_check:
            bb_filename = d.getVar('FILE')
            raise bb.build.FuncFailed, "\n\n\nERROR: %s inherits update-fonts but doesn't set UPDATE_FONTS_PACKAGES" % bb_filename
}
